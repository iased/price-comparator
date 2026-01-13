import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FilterService {
  private storeSubject = new BehaviorSubject<string | null>(null);
  private searchSubject = new BehaviorSubject<string>('');

  readonly store$ = this.storeSubject.asObservable();
  readonly search$ = this.searchSubject.asObservable();

  setStore(store: string | null) {
    this.storeSubject.next(store);
  }

  setSearch(term: string) {
    this.searchSubject.next(term);
  }
}
