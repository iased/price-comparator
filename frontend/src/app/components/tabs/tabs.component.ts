import { Component, AfterViewInit } from '@angular/core';
import { Router, RouterLink, RouterModule, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-tabs',
  imports: [RouterLink, RouterModule],
  templateUrl: './tabs.component.html',
  styleUrl: './tabs.component.scss'
})
export class TabsComponent implements AfterViewInit{
  constructor(private router: Router) {}

  navigate(path: string) {
    this.router.navigate([path]);
  }

  ngAfterViewInit(): void {
    this.updateHighlight();

    this.router.events
      .pipe(filter((e): e is NavigationEnd => e instanceof NavigationEnd))
      .subscribe(() => this.updateHighlight());
  }

  private updateHighlight() {
    setTimeout(() => {
      const activeTab = document.querySelector('.tab.active') as HTMLElement | null;
      const bg = document.querySelector('.tabs-bg') as HTMLElement | null;

      if (!activeTab || !bg) return;

      bg.style.width = activeTab.offsetWidth + 'px';
      bg.style.transform = `translateX(${activeTab.offsetLeft}px)`;
    }, 0);
  }
}
