package com.accesa.price_comparator.service;

import com.accesa.price_comparator.domain.BasketItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    private List<BasketItem> basketItems;
    private ProductService productService;
    private ProductDiscountService productDiscountService;

    public List<BasketItem> getAllItems() {
        return new ArrayList<>(basketItems);
    }

    public void addItem(BasketItem item) {
        basketItems.add(item);
    }

    public void removeItem(String productId) {
        basketItems.removeIf(item -> item.getProductId().equals(productId));
    }

    public void clearBasket() {
        basketItems.clear();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (BasketItem item : basketItems) {
            totalPrice += item.getQuantity() * productService.getProduct(item.getProductId()).getPricePerUnit();
        }
        return totalPrice;
    }
}
