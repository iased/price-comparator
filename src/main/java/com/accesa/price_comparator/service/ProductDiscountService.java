package com.accesa.price_comparator.service;

import com.accesa.price_comparator.domain.Discount;
import com.accesa.price_comparator.domain.Product;
import com.accesa.price_comparator.model.ProductKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductDiscountService {
    private final Map<ProductKey, Product> productMap = new HashMap<>();
    private final Map<ProductKey, List<Discount>> discountMap = new HashMap<>();

    public ProductDiscountService(ProductService productService, DiscountService discountService) {
        List<Product> products = productService.getAllProducts();
        List<Discount> discounts = discountService.getAllDiscounts();

        for (Product product : products) {
            ProductKey productKey = new ProductKey(product.getId(), product.getStore());
            productMap.put(productKey, product);
        }

        for (Discount discount : discounts) {
            ProductKey discountKey = new ProductKey(discount.getProductId(), discount.getStore());
            // if there is no list of discounts for this product, create one and add the discount to it
            discountMap.computeIfAbsent(discountKey, k -> new ArrayList<>()).add(discount);
        }
    }

    public Product getProduct(String productId, String store) {
        ProductKey productKey = new ProductKey(productId, store);
        return productMap.get(productKey);
    }

    public List<Discount> getDiscounts(String productId, String store) {
        ProductKey discountKey = new ProductKey(productId, store);
        return discountMap.getOrDefault(discountKey, List.of());
    }

}
