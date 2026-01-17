import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductComparison } from '../models/product-comparison.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private base = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getProductsByStore(store: string) {
    return this.http.get<any[]>(`${this.base}/supermarkets/${store}/products`);
  }

  getDiscountsToday() {
    return this.http.get<any[]>(`${this.base}/discounts/today`);
  }

  getDiscountsThisWeek() {
    return this.http.get<any[]>(`${this.base}/discounts/this-week`);
  }

  getProductComparisons(): Observable<ProductComparison[]> {
    return this.http.get<ProductComparison[]>(`${this.base}/products/comparison`);
  }

  getGroceryList() {
    return this.http.get<any[]>(`${this.base}/grocery-list/items`);
  }

  getGroceryListComparison(maxStores: number) {
    return this.http.get<any>(`${this.base}/grocery-list/comparison`, { 
      params: { maxStores }
    });
  }

  addToGroceryList(productId: number, quantity = 1) {
    return this.http.post<void>(`${this.base}/grocery-list/items`, { productId, quantity });
  }

  updateGroceryListItemQty(itemId: number, quantity: number) {
    return this.http.patch<any>(`${this.base}/grocery-list/items/${itemId}`, { quantity });
  }

  deleteGroceryListItem(itemId: number) {
    return this.http.delete<void>(`${this.base}/grocery-list/items/${itemId}`);
  }

}
