import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-discounts',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './discounts.component.html',
  styleUrl: './discounts.component.scss'
})
export class DiscountsComponent {
  discountTypes = ['Today', 'This Week'];
  discounts: any[] = [];
  selectedDiscountsType = this.discountTypes[0];
  loading = false;
  error: string | null = null;

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.loadDiscounts(this.selectedDiscountsType);
  }

  selectDiscountType(discountType: string) {
    if (discountType === this.selectedDiscountsType) return;
    this.loadDiscounts(discountType);
  }

  loadDiscounts(discountType: string) {
    this.selectedDiscountsType = discountType;
    this.loading = true;
    this.error = null;

    const request =
      discountType === 'Today'
        ? this.api.getDiscountsToday()
        : this.api.getDiscountsThisWeek();

    request.subscribe({
      next: data => {
        this.discounts = data;
    },
    error: err => {
      console.error(err);
      this.error = 'Failed to load discounts.';
      this.discounts = [];
    },
    complete: () => {
      this.loading = false;
    }
    })
  }
}