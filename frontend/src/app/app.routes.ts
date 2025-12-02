import { Routes } from '@angular/router';
import { ProductsComponent } from './components/products/products.component';
import { DiscountsComponent } from './components/discounts/discounts.component';

export const routes: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: 'products', component: ProductsComponent },
  { path: 'discounts', component: DiscountsComponent }
];
