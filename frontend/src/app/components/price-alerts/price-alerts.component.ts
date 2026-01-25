import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { Subject, of } from 'rxjs';
import { PriceAlertsService, PriceAlertDto, ProductSearchDto } from '../../services/price-alerts.service';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-price-alerts',
  imports: [CommonModule, FormsModule],
  templateUrl: './price-alerts.component.html',
  styleUrl: './price-alerts.component.scss'
})
export class PriceAlertsComponent implements OnInit {
  private api = inject(PriceAlertsService);
  private auth = inject(AuthService);
  private router = inject(Router);

  loading = false;
  alerts: PriceAlertDto[] = [];

  showCreate = false;
  q = '';
  results: ProductSearchDto[] = [];
  selected: ProductSearchDto | null = null;

  targetPrice: number | null = null;
  private q$ = new Subject<string>();

  ngOnInit(): void {
    if (!this.auth.isLoggedIn()) {
      this.router.navigate(['/login'], { queryParams: { returnUrl: '/price-alerts' } });
      return;
    }

    this.loadAlerts();

    this.q$
      .pipe(
        debounceTime(250),
        distinctUntilChanged(),
        switchMap((val) => {
          const query = (val || '').trim();
          if (query.length < 2) return of([] as ProductSearchDto[]);
          return this.api.searchProducts(query);
        })
      )
      .subscribe({
        next: (items) => (this.results = items),
        error: () => (this.results = [])
      });
  }

  get isLoggedIn(): boolean {
    return this.auth.isLoggedIn();
  }

  goToLogin() {
    const returnUrl = this.router.url;
    this.router.navigate(['/auth/login'], { queryParams: { returnUrl } });
  }

  goToRegister() {
    const returnUrl = this.router.url;
    this.router.navigate(['/auth/register'], { queryParams: { returnUrl } });
  }

  loadAlerts() {
    this.loading = true;
    this.api.getAlerts().subscribe({
      next: (data) => {
        this.alerts = data ?? [];
        this.loading = false;
      },
      error: () => {
        this.loading = false;
      }
    });
  }

  trackById(index: number, a: PriceAlertDto) {
    return a.id;
  }

  onQueryChange(val: string) {
    this.q = val;
    this.q$.next(val);
  }

  pickProduct(p: ProductSearchDto) {
    this.selected = p;
    this.results = [];
    this.q = `${p.name} (${p.brand})`;
  }

  openCreate() {
    this.showCreate = true;
    this.q = '';
    this.results = [];
    this.selected = null;
    this.targetPrice = null;
  }

  cancelCreate() {
    this.showCreate = false;
  }

  create() {
    if (!this.selected) return;
    if (this.targetPrice == null || this.targetPrice <= 0) return;

    this.loading = true;
    this.api.createAlert({
      productId: this.selected.id,
      targetPrice: this.targetPrice
    }).subscribe({
      next: (created) => {
        this.alerts = [created, ...this.alerts];
        this.loading = false;
        this.showCreate = false;
      },
      error: () => {
        this.loading = false;
      }
    });
  }

  remove(alert: PriceAlertDto) {
    this.loading = true;
    this.api.deleteAlert(alert.id).subscribe({
      next: () => {
        this.alerts = this.alerts.filter(a => a.id !== alert.id);
        this.loading = false;
      },
      error: () => {
        this.loading = false;
      }
    });
  }
  
}
