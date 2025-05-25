package com.accesa.price_comparator.domain;

import java.util.List;
import java.util.Map;

public class OptimizedBasket {
    private Map<String, List<BasketItem>> storeToItemsMap;
    private double totalPrice;

    public OptimizedBasket(Map<String, List<BasketItem>> storeToItemsMap, double totalPrice) {
        this.storeToItemsMap = storeToItemsMap;
        this.totalPrice = totalPrice;
    }

    public Map<String, List<BasketItem>> getStoreToItemsMap() {
        return storeToItemsMap;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setStoreToItemsMap(Map<String, List<BasketItem>> storeToItemsMap) {
        this.storeToItemsMap = storeToItemsMap;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
