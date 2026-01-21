import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { GroceryListComparisonComponent } from './grocery-list-comparison/grocery-list-comparison.component';
import { AuthService } from '../../auth/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-grocery-list',
  standalone: true,
  imports: [CommonModule, GroceryListComparisonComponent],
  templateUrl: './grocery-list.component.html',
  styleUrls: ['./grocery-list.component.scss']
})
export class GroceryListComponent implements OnInit, OnDestroy {
  private sub?: Subscription;
  items: any[] = [];
  loading = false;
  error: string | null = null;
  trackById = (_: number, it: any) => it.id ?? it.itemId ?? it.productId;

  constructor(
    private api: ApiService, 
    private router: Router,
    public auth: AuthService
  ) {}

  ngOnInit(): void {
    this.sub = this.auth.loggedInChanges().subscribe(isIn => {
      if (!isIn) {
        this.items = [];
        this.error = null;
        this.loading = false;
        return;
      }

      this.reload();
    });
  }

  ngOnDestroy(): void {
    this.sub?.unsubscribe();
  }

  reload(): void {
    this.loading = true;
    this.api.getGroceryList().subscribe({
      next: (res) => {
        this.items = res ?? [];
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Nu pot încărca lista.';
        this.loading = false;
      }
    });
  }

  private getItemId(it: any): number {
    return Number(it.itemId ?? it.id);
  }

  private getQty(it: any): number {
    const q = it.listQuantity;
    if (typeof q === 'number') return q;
    if (typeof q === 'string') {
      const n = parseInt(q.replace(/[^\d]/g, ''), 10);
      return Number.isFinite(n) ? n : 0;
    }
    return 0;
  }

  inc(it: any) {
    const itemId = this.getItemId(it);
    const qty = this.getQty(it) + 1;
    this.api.updateGroceryListItemQty(itemId, qty).subscribe(() => this.reload());
  }

  dec(it: any) {
    const itemId = this.getItemId(it);
    const current = this.getQty(it);
    const qty = current - 1;

    if (qty <= 0) {
      this.api.deleteGroceryListItem(itemId).subscribe(() => this.reload());
      return;
    }

    this.api.updateGroceryListItemQty(itemId, qty).subscribe(() => this.reload());
  }

  remove(it: any) {
    const itemId = this.getItemId(it);
    this.api.deleteGroceryListItem(itemId).subscribe(() => this.reload());
  }

  goToProducts() {
    this.router.navigate(['/products']);
  }

  goToLogin() {
    const returnUrl = this.router.url;
    this.router.navigate(['/auth/login'], { queryParams: { returnUrl } });
  }

  goToRegister() {
    const returnUrl = this.router.url;
    this.router.navigate(['/auth/register'], { queryParams: { returnUrl } });
  }
}
