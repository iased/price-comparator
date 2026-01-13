export interface Offer {
  store: string;
  price: number;
  pricePerUnit: number;
  date: string;
  discountPercent?: number | null;
  discountedPrice?: number | null;
  discountedPricePerUnit?: number | null;
}

export interface ProductComparison {
  productId: number;
  name: string;
  brand: string;
  category: string;
  quantity: number;
  unit: string;
  offers: Offer[];
  bestOffer: Offer;
}
