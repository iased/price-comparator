import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BehaviorSubject, combineLatest, map, shareReplay, startWith, switchMap, catchError, of } from 'rxjs';
import { Discount } from '../../models/discount.model';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';

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
  private readonly route = inject(ActivatedRoute);

  discountTypes: DiscountType[] = ['Today', 'This Week'];

  typeLabels: Record<DiscountType, string> = {
    'Today': 'Azi',
    'This Week': 'Săptămâna asta',
  };

  private readonly selectedType$ = new BehaviorSubject<DiscountType>('Today');

  private readonly queryParams$ = this.route.queryParamMap.pipe(
    map(pm => {
      const sidRaw = pm.get('storeId');
      const storeId = sidRaw ? Number(sidRaw) : null;

      return {
        storeId: Number.isFinite(storeId as any) ? storeId : null,
      };
    }),
    shareReplay({ bufferSize: 1, refCount: true })
  );

  private readonly storeFilter$ = this.queryParams$.pipe(
    map(x => {
      const id = x.storeId;
      if (id === 1) return 'lidl';
      if (id === 2) return 'profi';
      if (id === 3) return 'kaufland';
      return null;
    }),
    startWith<string | null>(null),
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

  readonly vm$ = combineLatest([this.discountsState$, this.storeFilter$]).pipe(
    map(([state, storeFilter]) => {
      const toJsDate = (s: string) => new Date(`${s}T00:00:00`);

      const filtered: DiscountView[] =
        this.filterAndSort(state.data, storeFilter).map((d): DiscountView => {
          const from = toJsDate(d.fromDate);
          const to = toJsDate(d.toDate);

          return {
            ...d,
            fromJs: from,
            toJs: to,
            daysLeft: this.getDaysLeft(d.toDate),
            endsLabel: this.endsInLabel(d.toDate),
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

  private filterAndSort(data: Discount[], storeFilter: string | null): Discount[] {
    let out = [...(data ?? [])];

    if (storeFilter) {
      out = out.filter(d => (d.supermarketName ?? '').toLowerCase() === storeFilter);
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

  private parseDateOnly(toDate: any): Date {
    if (typeof toDate === 'string' && /^\d{4}-\d{2}-\d{2}$/.test(toDate)) {
      const [y, m, d] = toDate.split('-').map(Number);
      return new Date(y, m - 1, d);
    }
    return new Date(toDate);
  }

  private diffDaysCalendar(toDate: any): number {
    const end = this.parseDateOnly(toDate);
    const today = new Date();

    end.setHours(0, 0, 0, 0);
    today.setHours(0, 0, 0, 0);

    return Math.round((end.getTime() - today.getTime()) / 86400000);
  }

  endsInLabel(toDate: any): string {
    const days = this.diffDaysCalendar(toDate);

    if (days === 0) return 'Expiră azi';
    if (days === 1) return 'Expiră mâine';
    return `Expiră în ${days} zile`;
  }

  getDaysLeft(toDate: any): number {
    return Math.max(0, this.diffDaysCalendar(toDate));
  }

}
