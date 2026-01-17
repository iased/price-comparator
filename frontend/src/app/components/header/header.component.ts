import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FilterService } from '../../services/filter.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  constructor(
    private filter: FilterService,
    private router: Router
  ) {}

  onSearch(term: string) {
    this.filter.setSearch(term);
  }

  onStoreChange(store: string) {
    this.filter.setStore(store || null);
  }

  goToList() {
    this.router.navigate(['/grocery-list']);
  }
}
