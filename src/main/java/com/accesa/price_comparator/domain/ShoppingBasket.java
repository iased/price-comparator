package com.accesa.price_comparator.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private String basketId;
    private List<BasketItem> items;

    public ShoppingBasket(String basketId, List<BasketItem> items) {
        this.basketId = basketId;
        this.items = items != null? items : new ArrayList<>();
    }

    public String getBasketId() {
        return basketId;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public void addItem(BasketItem item) {
        items.add(item);
    }

    public void removeItem(BasketItem item) {
        items.remove(item);
    }
}
