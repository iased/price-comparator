import { Component, Input, OnChanges, SimpleChanges, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../services/api.service';

type StoreGroup = { store: string; items: any[]; subtotal: number };

@Component({
  selector: 'app-grocery-list-comparison',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './grocery-list-comparison.component.html',
  styleUrls: ['./grocery-list-comparison.component.scss']
})
export class GroceryListComparisonComponent implements OnChanges {
  @ViewChild('planSection') planSection!: ElementRef<HTMLElement>;

  scrollToPlan(): void {
    requestAnimationFrame(() => {
      this.planSection?.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
    });
  }

  @Input() items: any[] = [];

  maxStores = 2;

  loading = false;
  error: string | null = null;

  comparison: any | null = null;
  groups: StoreGroup[] = [];

  trackByStore = (_: number, g: any) => g.store;
  trackByItem = (_: number, it: any) =>
    it.productId ?? it.id ?? `${it.name}-${it.brand}`;


  constructor(private api: ApiService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['items']) {
      const hasItems = Array.isArray(this.items) && this.items.length > 0;

      if (!hasItems) {
        this.comparison = null;
        this.groups = [];
        this.error = null;
        this.loading = false;
        return;
      }

      this.load(this.maxStores, false);
    }
  }

  load(maxStores: number, scroll = true): void {
    this.maxStores = maxStores;

    if (scroll) this.scrollToPlan();

    this.loading = true;
    this.error = null;

    this.api.getGroceryListComparison(this.maxStores).subscribe({
      next: (res) => {
        try {
          this.comparison = res;
          this.groups = this.groupByStore(res?.items ?? []);
        } catch (e) {
          console.error('Grouping/render error', e);
          this.error = 'A apărut o eroare la afișare.';
        } finally {
          this.loading = false;
        }
      },
      error: (err) => {
        console.error('HTTP error', err);
        this.error = 'A apărut o eroare la încărcare.';
        this.loading = false;
      }
    });
  }

  private groupByStore(items: any[]): StoreGroup[] {
    if (!Array.isArray(items) || items.length === 0) return [];

    const map = new Map<string, any[]>();
    for (const it of items) {
      const store = it?.chosenStore ?? '—';
      map.set(store, [...(map.get(store) ?? []), it]);
    }

    return Array.from(map.entries())
      .sort((a, b) => a[0].localeCompare(b[0]))
      .map(([store, storeItems]) => ({
        store,
        items: storeItems,
        subtotal: storeItems.reduce((sum, x) => sum + Number(x?.lineTotal ?? 0), 0)
      }));
  }
}
