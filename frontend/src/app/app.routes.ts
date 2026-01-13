import { Routes } from '@angular/router';
import { ProductsComparisonComponent } from './components/products-comparison/products-comparison.component';
import { DiscountsComponent } from './components/discounts/discounts.component';

export const routes: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: 'products', component: ProductsComparisonComponent },
  { path: 'discounts', component: DiscountsComponent }
];
