export interface Discount {
  id: number;

  productName: string;
  productBrand?: string | null;
  productImageUrl?: string | null;

  supermarketName: string;

  originalPrice: number;
  discountedPrice: number;

  percentageOfDiscount: number;
  fromDate: string;
  toDate: string;

  quantity: number;
  unit: string;

  unitPriceOriginal: number;
  unitPriceDiscounted: number;
  unitLabel: string;
}
