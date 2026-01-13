import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ProductComparison, Offer } from '../../models/product-comparison.model';
import { ApiService } from '../../services/api.service';
import { FilterService } from '../../services/filter.service';

@Component({
  selector: 'app-products-comparison',
  imports: [CommonModule],
  templateUrl: './products-comparison.component.html',
  styleUrl: './products-comparison.component.scss',
})
export class ProductsComparisonComponent {
  products: ProductComparison[] = [];
  filteredProducts: ProductComparison[] = [];

  loading = false;
  error: string | null = null;

  private searchTerm = '';
  private storeFilter: string | null = null;

  constructor(
    private api: ApiService,
    private filter: FilterService
  ) {}

  ngOnInit(): void {
    this.loadProducts();

    this.filter.search$.subscribe(term => {
      this.searchTerm = (term ?? '').toLowerCase();
      this.applyFilters();
    });

    this.filter.store$.subscribe(store => {
      this.storeFilter = store;
      this.applyFilters();
    });
  }

  loadProducts(): void {
    this.loading = true;
    this.error = null;

    this.api.getProductComparisons().subscribe({
      next: (products) => {
        this.products = products;
        this.applyFilters();
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Could not load products.';
        this.loading = false;
      },
    });
  }

  private applyFilters(): void {
  let data = [...this.products];

  if (this.searchTerm) {
    data = data.filter((p) => {
      const haystack = `${p.name} ${p.brand} ${p.category}`.toLowerCase();
      return haystack.includes(this.searchTerm);
    });
  }

  if (this.storeFilter) {
    const sf = this.storeFilter.toLowerCase();

    data = data
      .map((p) => {
        const offersForStore = p.offers.filter(
          (o) => o.store.toLowerCase() === sf
        );
        if (!offersForStore.length) return null;

        const bestOffer = offersForStore.reduce((best, current) => {
          return this.getEffectivePrice(current) < this.getEffectivePrice(best)
            ? current
            : best;
        });

        return {
          ...p,
          offers: offersForStore,
          bestOffer,
        } as ProductComparison;
      })
      .filter((p): p is ProductComparison => p !== null);
  } else {
    data = data.map((p) => {
      const bestOffer = p.offers.reduce((best, current) => {
        return this.getEffectivePrice(current) < this.getEffectivePrice(best)
          ? current
          : best;
      });

      return { ...p, bestOffer } as ProductComparison;
    });
  }

  this.filteredProducts = data;
}


  getEffectivePrice(offer: Offer): number {
    return offer.discountedPrice ?? offer.price;
  }

  getEffectivePricePerUnit(offer: Offer): number | null {
    if (offer.discountedPricePerUnit != null) {
      return offer.discountedPricePerUnit;
    }
    return offer.pricePerUnit ?? null;
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


}
