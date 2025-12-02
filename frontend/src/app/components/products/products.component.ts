import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../api.service';

@Component({
  selector: 'app-products',
  imports: [CommonModule],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss'
})
export class ProductsComponent {
  stores = ['Lidl', 'Profi', 'Kaufland'];
  products: any[] = [];
  selectedStore = this.stores[0];
  loading = false;
  error: string | null = null;

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.loadStoreProducts(this.selectedStore);
  }

  selectStore(store: string) {
    if (store === this.selectedStore) return;
    this.loadStoreProducts(store);
  }

  loadStoreProducts(store: string) {
    this.selectedStore = store;
    this.loading = true;
    this.error = null;

    this.api.getProductsByStore(store).subscribe({
      next: data => {
        this.products = data;
      },
      error: err => {
        console.error(err);
        this.error = 'Failed to load products.';
        this.products = [];
      },
      complete: () => {
        this.loading = false;
      }
    });
  }
}