import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule} from '@angular/router';
import { FilterService } from '../../services/filter.service';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  userMenuOpen = false;
  selectedStoreId: number | null = null;
  currentStoreId: number | null = null;

  constructor(
    private filter: FilterService,
    private router: Router,
    public auth: AuthService
  ) {}

  onSearch(term: string) {
    this.filter.setSearch(term);
  }

  pickStore(id: number | null) {
    this.selectedStoreId = id;
    this.filter.setStoreId(id);
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
    if (q.length < 2) return;

    const url = this.router.url.split('?')[0];
    if (url !== '/products' && url !== '/discounts') {
      const queryParams: any = { q };
      if (this.selectedStoreId != null) queryParams.storeId = this.selectedStoreId;

      this.router.navigate(['/products'], { queryParams });
    }
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

  closeUserMenu() {
    this.userMenuOpen = false;
  }
}
