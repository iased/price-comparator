package com.accesa.price_comparator.service;

import com.accesa.price_comparator.domain.Discount;
import com.accesa.price_comparator.domain.Product;
import com.accesa.price_comparator.dto.ProductBestDiscount;
import com.accesa.price_comparator.model.ProductKey;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<ProductBestDiscount> getProductsWithBestDiscounts() {
        System.out.println("Discount map size: " + discountMap.size());
        System.out.println("Product map size: " + productMap.size());

        LocalDate today = LocalDate.now();

        // map key: productId
        // map value: product details + best discount across all stores
        Map<String, ProductBestDiscount> bestDiscountMap = new HashMap<>();

        // loop through all entries in discountMap (product + store -> list of discount)
        for(Map.Entry<ProductKey, List<Discount>> entry : discountMap.entrySet()) {
            ProductKey key = entry.getKey();
            List<Discount> discounts = entry.getValue();

            Discount bestDiscount = null;
            int maxDiscount = 0;

            // loop through all discounts for this product + store
            for (Discount discount : discounts) {
                LocalDate fromDate = LocalDate.parse(discount.getFromDate());
                LocalDate toDate = LocalDate.parse(discount.getToDate());

                // check if discount is currently active
                boolean discountActive = (today.isEqual(fromDate) || today.isAfter(fromDate)) &&
                        (today.isBefore(toDate) || today.isEqual(toDate));
                if (discountActive) {
                    int percentage = discount.getDiscount();
                    if (percentage > maxDiscount) {
                        maxDiscount = percentage;
                        bestDiscount = discount;
                    }
                }
            }

            if (bestDiscount != null) {
                // get the product for this product + store
                Product product = productMap.get(key);
                if (product != null) {
                    // check if there's already a discount recorded for this productId
                    // update if this discount is better
                    ProductBestDiscount alreadyRecordedDiscount = bestDiscountMap.get(key.productId());
                    if (alreadyRecordedDiscount == null || maxDiscount > alreadyRecordedDiscount.getDiscount().getDiscount()) {
                        bestDiscountMap.put(key.productId(), new ProductBestDiscount(product, bestDiscount));
                    }
                }
            }
        }

        return new ArrayList<>(bestDiscountMap.values());
    }

    public List<Discount> getNewDiscounts(){
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        List<Discount> newDiscounts = new ArrayList<>();

        for(List<Discount> discounts : discountMap.values()) {
            for (Discount discount : discounts) {
                LocalDate fromDate = LocalDate.parse(discount.getFromDate());

                if(fromDate.isEqual(yesterday) || fromDate.isEqual(today)){
                    newDiscounts.add(discount);
                }
            }
        }

        return newDiscounts;
    }
}
