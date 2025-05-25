package com.accesa.price_comparator.service;

import com.accesa.price_comparator.domain.BasketItem;
import com.accesa.price_comparator.dto.ProductBestDiscount;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasketOptimizerService {
    private final ProductDiscountService productDiscountService;

    public BasketOptimizerService(ProductDiscountService productDiscountService) {
        this.productDiscountService = productDiscountService;
    }

    public Map<String, List<BasketItem>> splitBasketByStore(List<BasketItem> basketItems) {
        Map<String, List<BasketItem>> storeToItemsMap = new HashMap<>();
        List<ProductBestDiscount> bestDiscounts = productDiscountService.getProductsWithBestDiscounts();
        for(BasketItem item : basketItems) {
            ProductBestDiscount best = null;
            for (ProductBestDiscount discount : bestDiscounts) {
                if (discount.getProduct().getId().equals(item.getProductId())) {
                    best = discount;
                    break;
                }
            }

            String store = (best != null) ? best.getProduct().getStore() : "unknownStore";
            storeToItemsMap.computeIfAbsent(store, k -> new ArrayList<>()).add(item);
        }

        return storeToItemsMap;
    }
}
