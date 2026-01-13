import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductComparison } from '../models/product-comparison.model';

@Injectable({ providedIn: 'root' })
export class ProductComparisonService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getProductComparisons(): Observable<ProductComparison[]> {
    return this.http.get<ProductComparison[]>(`${this.baseUrl}/products/comparison`);
  }
}
