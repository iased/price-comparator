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
}
