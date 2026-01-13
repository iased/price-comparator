package com.uo.price_comparator.dto;

import java.util.List;

public class ProductComparisonDto {
    private Long productId;
    private String name;
    private String brand;
    private String category;
    private double quantity;
    private String unit;
    private String imageUrl;

    private List<OfferDto> offers;
    private OfferDto bestOffer;

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageURL) { this.imageUrl = imageURL; }

    public List<OfferDto> getOffers() { return offers; }
    public void setOffers(List<OfferDto> offers) { this.offers = offers; }

    public OfferDto getBestOffer() { return bestOffer; }
    public void setBestOffer(OfferDto bestOffer) { this.bestOffer = bestOffer; }
}
