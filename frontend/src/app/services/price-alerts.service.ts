import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ProductSearchDto {
  id: number;
  name: string;
  brand: string;
  quantity: number;
  unit: string;
  imageUrl: string | null;
}

export interface PriceAlertDto {
  id: number;
  productId: number;
  productName: string;
  brand: string;
  quantity: number;
  unit: string;
  imageUrl: string | null;
  targetPrice: number;
  currentBestPrice: number | null;
  currentBestStoreId: number | null;
  currentBestStoreName: string | null;
  reached: boolean;
}

export interface CreatePriceAlertRequest {
  productId: number;
  targetPrice: number;
}

@Injectable({ providedIn: 'root' })
export class PriceAlertsService {
  private http = inject(HttpClient);
  private base = 'http://localhost:8080/api';

  searchProducts(q: string): Observable<ProductSearchDto[]> {
    const params = new HttpParams().set('q', q);
    return this.http.get<ProductSearchDto[]>(`${this.base}/products/search`, { params });
  }

  getAlerts(): Observable<PriceAlertDto[]> {
    return this.http.get<PriceAlertDto[]>(`${this.base}/price-alerts`);
  }

  createAlert(req: CreatePriceAlertRequest): Observable<PriceAlertDto> {
    return this.http.post<PriceAlertDto>(`${this.base}/price-alerts`, req);
  }

  deleteAlert(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/price-alerts/${id}`);
  }
}
