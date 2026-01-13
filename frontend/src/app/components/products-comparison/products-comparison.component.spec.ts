import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsComparisonComponent } from './products-comparison.component';

describe('ProductsComparisonComponent', () => {
  let component: ProductsComparisonComponent;
  let fixture: ComponentFixture<ProductsComparisonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductsComparisonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductsComparisonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
