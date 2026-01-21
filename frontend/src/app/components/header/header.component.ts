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
  constructor(
    private filter: FilterService,
    private router: Router,
    public auth: AuthService
  ) {}

  onSearch(term: string) {
    this.filter.setSearch(term);
  }

  onStoreChange(store: string) {
    this.filter.setStore(store || null);
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
  }
}
