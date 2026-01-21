import { Routes } from '@angular/router';
import { AUTH_ROUTES } from './auth/auth.routes';
import { ProductsComparisonComponent } from './components/products-comparison/products-comparison.component';
import { DiscountsComponent } from './components/discounts/discounts.component';
import { GroceryListComponent } from './components/grocery-list/grocery-list.component';

export const routes: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: 'products', component: ProductsComparisonComponent },
  { path: 'discounts', component: DiscountsComponent },
  { path: 'grocery-list', component: GroceryListComponent },
  { path: 'auth', children: AUTH_ROUTES }
];
