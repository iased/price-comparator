import { Component, OnInit, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, ActivatedRoute} from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  selectedStoreId: number | null = null;
  selectedCategory: string | null = null;
  selectedCategoryLabel = 'Toate categoriile';
  currentQ = '';

  categoryOpen = false;
  storeOpen = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    public auth: AuthService
  ) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(pm => {
      this.currentQ = (pm.get('q') ?? '').trim();

      const sid = pm.get('storeId');
      const storeId = sid != null ? Number(sid) : null;
      this.selectedStoreId = Number.isFinite(storeId as any) ? storeId : null;

      const cat = (pm.get('category') ?? '').trim();
      this.selectedCategory = cat.length ? cat : null;
      this.updateCategoryLabel();
    });
  }

  private navigateWithFilters(
    overrides: Partial<{ q: string | null; storeId: number | null; category: string | null }> = {},
    forcePath?: '/products' | '/discounts'
  ) {
    const currentPath = this.router.url.split('?')[0] as any;
    const path: '/products' | '/discounts' =
      forcePath ?? (currentPath === '/discounts' ? '/discounts' : '/products');

    const q = overrides.q !== undefined ? overrides.q : this.currentQ;
    const storeId = overrides.storeId !== undefined ? overrides.storeId : this.selectedStoreId;
    const category = overrides.category !== undefined ? overrides.category : this.selectedCategory;

    const queryParams: any = {};

    if (storeId != null) queryParams.storeId = storeId;

    if (path === '/products') {
      const cat = (category ?? '').trim();
      if (cat.length) queryParams.category = cat;

      const qq = (q ?? '').trim();
      if (qq.length >= 2) queryParams.q = qq;
    }

    this.router.navigate([path], { queryParams });
  }

  private updateCategoryLabel() {
    this.selectedCategoryLabel = this.selectedCategory ? this.selectedCategory : 'Toate categoriile';
  }

  toggleCategory(ev: MouseEvent) {
    ev.stopPropagation();
    this.categoryOpen = !this.categoryOpen;
    if (this.categoryOpen) this.storeOpen = false;
  }

  pickCategory(cat: string | null) {
    this.selectedCategory = cat;
    this.updateCategoryLabel();

    const path = this.router.url.split('?')[0];
    if (path === '/discounts') {
      this.router.navigate(['/products'], {
        queryParams: {
          ...(this.selectedStoreId != null ? { storeId: this.selectedStoreId } : {}),
          ...(cat ? { category: cat } : {}),
          ...(this.currentQ?.trim().length >= 2 ? { q: this.currentQ.trim() } : {}),
        }
      });
      return;
    }

    this.navigateWithFilters({ category: cat }, '/products');

    this.categoryOpen = false;
  }

  toggleStore(ev: MouseEvent) {
    ev.stopPropagation();
    this.storeOpen = !this.storeOpen;
    if (this.storeOpen) this.categoryOpen = false;
  }

  pickStore(id: number | null) {
    this.selectedStoreId = id;
    this.navigateWithFilters({ storeId: id }); 
    this.categoryOpen = false;
  }

  @HostListener('document:click')
  closeOnOutsideClick() {
    this.categoryOpen = false;
    this.storeOpen = false;
  }

  get selectedStoreLabel(): string {
    switch (this.selectedStoreId) {
      case 1: return 'Lidl';
      case 2: return 'Profi';
      case 3: return 'Kaufland';
      default: return 'Toate magazinele';
    }
  }

  onSearchEnter(term: string) {
    const q = (term ?? '').trim();
    this.currentQ = q;

    const finalQ = q.length >= 2 ? q : null;

    this.router.navigate(['/products'], {
      queryParams: {
        ...(this.selectedStoreId != null ? { storeId: this.selectedStoreId } : {}),
        ...(this.selectedCategory ? { category: this.selectedCategory } : {}),
        ...(finalQ ? { q: finalQ } : {}),
      }
    });
  }

  goToList() {
    this.router.navigate(['/grocery-list']);
  }

  goToLogin() {
    const returnUrl = this.router.url;
    this.router.navigate(['/auth/login'], { queryParams: { returnUrl } });
  }

  goToRegister() {
    const returnUrl = this.router.url;
    this.router.navigate(['/auth/register'], { queryParams: { returnUrl } });
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/products']);
  }
}
