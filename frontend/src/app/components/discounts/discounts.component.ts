import { ChangeDetectionStrategy, Component, DestroyRef, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BehaviorSubject, combineLatest, map, shareReplay, startWith, switchMap, catchError, of } from 'rxjs';
import { Discount } from '../../models/discount.model';

import { ApiService } from '../../services/api.service';
import { FilterService } from '../../services/filter.service';

type DiscountType = 'Today' | 'This Week';

type DiscountView = Discount & {
  fromJs: Date;
  toJs: Date;
  daysLeft: number;
  endsLabel: string;
  initial: string;
};

@Component({
  selector: 'app-discounts',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './discounts.component.html',
  styleUrl: './discounts.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DiscountsComponent {
  private readonly api = inject(ApiService);
  private readonly filter = inject(FilterService);

  discountTypes: DiscountType[] = ['Today', 'This Week'];

  typeLabels: Record<DiscountType, string> = {
    'Today': 'Azi',
    'This Week': 'Săptămâna asta',
  };

  private readonly selectedType$ = new BehaviorSubject<DiscountType>('Today');

  private readonly searchTerm$ = this.filter.search$.pipe(
    startWith(''),
    map(term => (term ?? '').toLowerCase().trim()),
    shareReplay({ bufferSize: 1, refCount: true })
  );

  private readonly storeFilter$ = this.filter.store$.pipe(
    startWith<string | null>(null),
    map(store => (store ? store.toLowerCase().trim() : null)),
    shareReplay({ bufferSize: 1, refCount: true })
  );

  private readonly discountsState$ = this.selectedType$.pipe(
    switchMap(type => {
      const req = type === 'Today'
        ? this.api.getDiscountsToday()
        : this.api.getDiscountsThisWeek();

      return req.pipe(
        map((data: Discount[] | null) => ({ loading: false, error: null as string | null, data: data ?? [] })),
        startWith({ loading: true, error: null as string | null, data: [] as Discount[] }),
        catchError(err => {
          console.error(err);
          return of({ loading: false, error: 'Nu s-au putut încărca reducerile.', data: [] as Discount[] });
        })
      );
    }),
    shareReplay({ bufferSize: 1, refCount: true })
  );

  readonly vm$ = combineLatest([this.discountsState$, this.searchTerm$, this.storeFilter$]).pipe(
    map(([state, searchTerm, storeFilter]) => {
      const toJsDate = (s: string) => new Date(`${s}T00:00:00`);

      const filtered: DiscountView[] =
        this.filterAndSort(state.data, searchTerm, storeFilter).map((d): DiscountView => {
          const from = toJsDate(d.fromDate);
          const to = toJsDate(d.toDate);

          return {
            ...d,
            fromJs: from,
            toJs: to,
            daysLeft: this.getDaysLeft(to),
            endsLabel: this.endsInLabel(to),
            initial: ((d.productName ?? '?').trim()[0] ?? '?').toUpperCase(),
          };
        });

      return {
        loading: state.loading,
        error: state.error,
        total: filtered.length,
        discounts: filtered,
        selectedType: this.selectedType$.value,
      };
    }),
    shareReplay({ bufferSize: 1, refCount: true })
  );


  selectDiscountType(type: DiscountType) {
    if (type !== this.selectedType$.value) this.selectedType$.next(type);
  }

  trackByDiscount = (_: number, d: DiscountView) =>
    d.id ?? `${d.productName ?? ''}-${d.supermarketName ?? ''}-${d.toDate ?? ''}`;

  private filterAndSort(data: Discount[], searchTerm: string, storeFilter: string | null): Discount[] {
    let out = [...(data ?? [])];

    if (storeFilter) {
      out = out.filter(d => (d.supermarketName ?? '').toLowerCase() === storeFilter);
    }

    if (searchTerm) {
      out = out.filter(d => {
        const hay = `${d.productName ?? ''} ${d.productBrand ?? ''}`.toLowerCase();
        return hay.includes(searchTerm);
      });
    }

    out.sort((a, b) => {
      const pa = Number(a.percentageOfDiscount ?? 0);
      const pb = Number(b.percentageOfDiscount ?? 0);
      if (pb !== pa) return pb - pa;

      const ea = this.toTime(a.toDate);
      const eb = this.toTime(b.toDate);
      return ea - eb;
    });

    return out;
  }

  private toTime(value: any): number {
    const t = new Date(value).getTime();
    return Number.isFinite(t) ? t : Number.POSITIVE_INFINITY;
  }

  endsInLabel(toDate: any): string {
    const end = new Date(toDate);
    const now = new Date();

    end.setHours(23, 59, 59, 999);

    const ms = end.getTime() - now.getTime();
    const days = Math.ceil(ms / 86400000);

    if (days <= 0) return 'Expiră azi';
    if (days === 1) return 'Expiră mâine';
    return `Expiră în ${days} zile`;
  }

  getDaysLeft(toDate: any): number {
    const end = new Date(toDate);
    const now = new Date();
    return Math.max(0, Math.ceil((end.getTime() - now.getTime()) / 86400000));
  }

}
