package com.uo.price_comparator.dto;

import java.math.BigDecimal;

public class GroceryListItemDto {
    private Long id;
    private Long productId;
    private String name;
    private String brand;
    private BigDecimal quantityValue;
    private String unit;
    private String imageUrl;
    private Integer listQuantity;
    private OfferDto bestOffer;
    private BigDecimal lineTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(BigDecimal quantityValue) {
        this.quantityValue = quantityValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getListQuantity() {
        return listQuantity;
    }

    public void setListQuantity(Integer listQuantity) {
        this.listQuantity = listQuantity;
    }

    public OfferDto getBestOffer() {
        return bestOffer;
    }

    public void setBestOffer(OfferDto bestOffer) {
        this.bestOffer = bestOffer;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
}
