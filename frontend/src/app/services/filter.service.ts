import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FilterService {
  private storeIdSubject = new BehaviorSubject<number | null>(null);
  private searchSubject = new BehaviorSubject<string>('');

  readonly storeId$ = this.storeIdSubject.asObservable();
  readonly search$ = this.searchSubject.asObservable();

  setStoreId(storeId: number | null) {
    this.storeIdSubject.next(storeId);
  }

  setSearch(term: string) {
    this.searchSubject.next(term);
  }
}
