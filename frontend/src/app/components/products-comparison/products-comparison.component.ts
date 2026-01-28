import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ProductComparison, Offer } from '../../models/product-comparison.model';
import { ApiService } from '../../services/api.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-products-comparison',
  imports: [CommonModule],
  templateUrl: './products-comparison.component.html',
  styleUrl: './products-comparison.component.scss',
})
export class ProductsComparisonComponent {
  products: ProductComparison[] = [];
  addingId: number | null = null;
  justAddedId: number | null = null;

  loading = false;
  error: string | null = null;

  private q = '';
  private storeId: number | null = null;
  private category: string | null = null;

  constructor(
    private api: ApiService,
    public auth: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(pm => {
      const q = (pm.get('q') ?? '').trim();
      const storeIdParam = pm.get('storeId');
      const storeId = storeIdParam != null ? Number(storeIdParam) : null;

      const category = (pm.get('category') ?? '').trim();
      this.category = category.length ? category : null;

      this.q = q;
      this.storeId = Number.isFinite(storeId as any) ? storeId : null;

      this.loadProducts();
    });
  }

  loadProducts(): void {
    this.loading = true;
    this.error = null;

    const q = this.q.length >= 2 ? this.q : '';
    const storeId = this.storeId;

    this.api.getProductComparisons(q, storeId, this.category).subscribe({
      next: (products) => {
        this.products = products;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Nu s-au putut încărca produsele.';
        this.loading = false;
      },
    });
  }

  addToList(productId: number) {
    if (this.addingId === productId) return;

    this.addingId = productId;

    this.api.addToGroceryList(productId, 1).subscribe({
      next: () => {
        this.addingId = null;

        this.justAddedId = productId;
        window.setTimeout(() => {
          if (this.justAddedId === productId) this.justAddedId = null;
        }, 900);
      },
      error: (err) => {
        console.error(err);
        this.addingId = null;
      }
    });
  }

  getEffectivePrice(offer: Offer): number {
    return offer.discountedPrice ?? offer.price;
  }

  hasDiscount(offer: Offer): boolean {
    return offer.discountedPrice != null && offer.discountedPrice !== offer.price;
  }

  isBestOffer(product: ProductComparison, offer: Offer): boolean {
    const offers = product.offers ?? [];
    if (!offers.length) return false;

    const min = Math.min(...offers.map(o => this.getEffectivePrice(o)));

    const EPS = 0.01;
    return Math.abs(this.getEffectivePrice(offer) - min) < EPS;
  }

  getBestOffers(product: ProductComparison): Offer[] {
    const offers = product.offers ?? [];
    if (!offers.length) return [];

    const min = Math.min(...offers.map(o => this.getEffectivePrice(o)));
    const EPS = 0.01;

    return offers.filter(o => Math.abs(this.getEffectivePrice(o) - min) < EPS);
  }

  goToLogin() {
    const returnUrl = this.router.url;
    this.router.navigate(['/auth/login'], { queryParams: { returnUrl } });
  }

  getDisplayUnit(unit: string | null | undefined): string {
    const u = (unit ?? '').toLowerCase();
    if (u === 'g') return 'kg';
    if (u === 'ml') return 'l';
    return u || 'buc';
  }

  private toDisplayQuantity(quantity: number | null | undefined, unit: string | null | undefined): number | null {
    if (quantity == null) return null;

    const u = (unit ?? '').toLowerCase();
    if (u === 'g') return quantity / 1000; 
    if (u === 'ml') return quantity / 1000;
    if (u === 'kg' || u === 'l') return quantity;
    if (u === 'buc') return quantity;

    return quantity;
  }

  getDisplayPPU(product: ProductComparison, offer: Offer): number | null {
    const qty = this.toDisplayQuantity(Number(product.quantity), product.unit);
    if (!qty || qty === 0) return null;

    const effectivePrice = this.getEffectivePrice(offer);
    return effectivePrice / qty;
  }

}
