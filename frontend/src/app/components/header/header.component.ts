import { Component } from '@angular/core';
import { FilterService } from '../../services/filter.service';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  constructor(private filter: FilterService) {}

  onSearch(term: string) {
    this.filter.setSearch(term);
  }

  onStoreChange(store: string) {
    this.filter.setStore(store || null);
  }
}
