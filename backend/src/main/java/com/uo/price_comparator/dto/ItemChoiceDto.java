package com.uo.price_comparator.dto;

import java.math.BigDecimal;

public class ItemChoiceDto {
    private Long itemId;
    private Long productId;
    private String name;
    private String brand;

    private int quantity;

    private String chosenStore;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getChosenStore() { return chosenStore; }
    public void setChosenStore(String chosenStore) { this.chosenStore = chosenStore; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public BigDecimal getLineTotal() { return lineTotal; }
    public void setLineTotal(BigDecimal lineTotal) { this.lineTotal = lineTotal; }
}
