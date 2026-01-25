import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceAlertsComponent } from './price-alerts.component';

describe('PriceAlertsComponent', () => {
  let component: PriceAlertsComponent;
  let fixture: ComponentFixture<PriceAlertsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PriceAlertsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PriceAlertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
