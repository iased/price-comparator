import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { GroceryListItem } from '../models/grocery-list.model';

const STORAGE_KEY = 'grocery_list_items_v1';

@Injectable({ providedIn: 'root' })
export class GroceryListService {
  private readonly itemsSubject = new BehaviorSubject<GroceryListItem[]>(this.read());
  readonly items$ = this.itemsSubject.asObservable();

  get snapshot(): GroceryListItem[] {
    return this.itemsSubject.value;
  }

  add(item: Omit<GroceryListItem, 'quantity'> & { quantity?: number }) {
    const qty = Math.max(1, Number(item.quantity ?? 1));
    const items = [...this.snapshot];

    const existing = items.find(x => x.productId === item.productId);
    if (existing) {
      existing.quantity += qty;
    } else {
      items.push({ ...item, quantity: qty });
    }

    this.commit(items);
  }

  setQuantity(productId: number, quantity: number) {
    const qty = Math.max(0, Number(quantity || 0));
    const items = this.snapshot
      .map(x => x.productId === productId ? { ...x, quantity: qty } : x)
      .filter(x => x.quantity > 0);

    this.commit(items);
  }

  inc(productId: number) {
    const item = this.snapshot.find(x => x.productId === productId);
    if (!item) return;
    this.setQuantity(productId, item.quantity + 1);
  }

  dec(productId: number) {
    const item = this.snapshot.find(x => x.productId === productId);
    if (!item) return;
    this.setQuantity(productId, item.quantity - 1);
  }

  remove(productId: number) {
    this.commit(this.snapshot.filter(x => x.productId !== productId));
  }

  clear() {
    this.commit([]);
  }

  private commit(items: GroceryListItem[]) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(items));
    this.itemsSubject.next(items);
  }

  private read(): GroceryListItem[] {
    try {
      const raw = localStorage.getItem(STORAGE_KEY);
      const parsed = raw ? JSON.parse(raw) : [];
      if (!Array.isArray(parsed)) return [];
      return parsed
        .map(x => ({
          productId: Number(x.productId),
          name: String(x.name ?? ''),
          brand: x.brand ? String(x.brand) : undefined,
          quantity: Math.max(1, Number(x.quantity ?? 1))
        }))
        .filter(x => Number.isFinite(x.productId) && x.productId > 0 && x.name.length > 0);
    } catch {
      return [];
    }
  }
}
