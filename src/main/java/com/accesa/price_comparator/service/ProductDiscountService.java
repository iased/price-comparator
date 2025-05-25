package com.accesa.price_comparator.service;

import com.accesa.price_comparator.domain.Discount;
import com.accesa.price_comparator.domain.Product;
import com.accesa.price_comparator.dto.PriceHistoryPoint;
import com.accesa.price_comparator.dto.ProductBestDiscount;
import com.accesa.price_comparator.model.ProductKey;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@Service
public class ProductDiscountService {
    private final Map<ProductKey, List<Product>> productSnapshotsMap = new HashMap<>();
    private final Map<ProductKey, List<Discount>> discountMap = new HashMap<>();

    public ProductDiscountService(ProductService productService, DiscountService discountService) {
        List<Product> products = productService.getAllProducts();
        List<Discount> discounts = discountService.getAllDiscounts();

        for (Product product : products) {
            ProductKey productKey = new ProductKey(product.getId(), product.getStore());
            if (!productSnapshotsMap.containsKey(productKey)) {
                productSnapshotsMap.put(productKey, new ArrayList<>());
            }
            productSnapshotsMap.get(productKey).add(product);
        }

        for (Discount discount : discounts) {
            ProductKey discountKey = new ProductKey(discount.getProductId(), discount.getStore());
            // if there is no list of discounts for this product, create one and add the discount to it
            discountMap.computeIfAbsent(discountKey, k -> new ArrayList<>()).add(discount);
        }
    }

    public List<Product> getProducts(String productId, String store) {
        ProductKey key = new ProductKey(productId, store);
        return productSnapshotsMap.getOrDefault(key, Collections.emptyList());
    }

    public Product getLatestProductSnapshot(String productId, String store) {
        List<Product> snapshots = getProducts(productId, store);
        if (snapshots == null || snapshots.isEmpty()) {
            return null;
        }

        Product latestProduct = null;
        for (Product product : snapshots) {
            if (latestProduct == null || product.getDate().isAfter(latestProduct.getDate())) {
                latestProduct = product;
            }
        }
        return latestProduct;
    }

    public Product getLatestProductSnapshot(String productId) {
        Product latestProduct = null;

        for (Map.Entry<ProductKey, List<Product>> entry : productSnapshotsMap.entrySet()) {
            if (entry.getKey().productId().equals(productId)) {
                List<Product> snapshots = entry.getValue();
                for (Product product : snapshots) {
                    if (latestProduct == null || product.getDate().isAfter(latestProduct.getDate())) {
                        latestProduct = product;
                    }
                }
            }
        }

        return latestProduct;
    }

    public List<Product> getAllLatestProductSnapshots() {
        List<Product> latestSnapshots = new ArrayList<>();

        for (ProductKey key : productSnapshotsMap.keySet()) {
            Product latest = getLatestProductSnapshot(key.productId(), key.store());
            if (latest != null) {
                latestSnapshots.add(latest);
            }
        }

        return latestSnapshots;
    }

    public List<Discount> getDiscounts(String productId, String store) {
        ProductKey discountKey = new ProductKey(productId, store);
        return discountMap.getOrDefault(discountKey, List.of());
    }

    public List<ProductBestDiscount> getProductsWithBestDiscounts() {
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
                Product latestProduct = getLatestProductSnapshot(key.productId(), key.store());
                if (latestProduct != null) {
                    ProductBestDiscount alreadyRecordedDiscount = bestDiscountMap.get(key.productId());
                    if (alreadyRecordedDiscount == null || maxDiscount > alreadyRecordedDiscount.getDiscount().getDiscount()) {
                        bestDiscountMap.put(key.productId(), new ProductBestDiscount(latestProduct, bestDiscount));
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

    public double calculateDiscountedPrice(double price, int discount) {
        double discountedPrice = price - (price * discount / 100);
        return new BigDecimal(discountedPrice)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public List<PriceHistoryPoint> getPriceHistoryPoints(String productId){
        Set<PriceHistoryPoint> historyPoints = new LinkedHashSet<>();

        for(Map.Entry<ProductKey, List<Product>> entry : productSnapshotsMap.entrySet()) {
            ProductKey key = entry.getKey();
            List<Product> productSnapshots = entry.getValue();

            for(Product product : productSnapshots) {
                if (!product.getId().equals(productId)) {
                    continue;
                }

                List<Discount> discounts = discountMap.get(key);

                if (discounts == null || discounts.isEmpty()) {
                    historyPoints.add(new PriceHistoryPoint(
                            product.getId(),
                            product.getStore(),
                            product.getDate(),
                            product.getDate(),
                            product.getPrice(),
                            product.getPrice())
                    );
                } else {
                    for (Discount discount : discounts) {
                        LocalDate fromDate = LocalDate.parse(discount.getFromDate());
                        LocalDate toDate = LocalDate.parse(discount.getToDate());
                        double discountedPrice = calculateDiscountedPrice(product.getPrice(), discount.getDiscount());

                        PriceHistoryPoint point = new PriceHistoryPoint(
                                product.getId(),
                                product.getStore(),
                                fromDate,
                                toDate,
                                product.getPrice(),
                                discountedPrice
                        );

                        historyPoints.add(point);
                    }
                }
            }
        }

        return new ArrayList<>(historyPoints);
    }

    public List<PriceHistoryPoint> getPriceHistoryPointsByStore(String productId, String store){
        return getPriceHistoryPoints(productId).stream()
                .filter(p -> store == null || p.getStore().equalsIgnoreCase(store))
                .toList();
    }
}