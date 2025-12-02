import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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
}
