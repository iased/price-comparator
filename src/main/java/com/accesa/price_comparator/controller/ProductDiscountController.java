package com.accesa.price_comparator.controller;

import com.accesa.price_comparator.domain.Discount;
import com.accesa.price_comparator.domain.Product;
import com.accesa.price_comparator.dto.ProductBestDiscount;
import com.accesa.price_comparator.service.ProductDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/market")
public class ProductDiscountController {
    private final ProductDiscountService productDiscountService;

    @Autowired
    public ProductDiscountController(ProductDiscountService productDiscountService) {
        this.productDiscountService = productDiscountService;
    }

    @GetMapping("/{store}/{productId}")
    public ResponseEntity<Product> getProduct(
            @PathVariable String store,
            @PathVariable String productId) {

        Product product = productDiscountService.getProduct(productId, store);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{store}/{productId}/discounts")
    public List<Discount> getDiscounts(
            @PathVariable String store,
            @PathVariable String productId) {
        return productDiscountService.getDiscounts(productId, store);
    }

    @GetMapping("/best-discounts")
    public ResponseEntity<List<ProductBestDiscount>> getBestDiscounts() {
        List<ProductBestDiscount> productBestDiscounts = productDiscountService.getProductsWithBestDiscounts();
        System.out.println("Best discounts count: " + productBestDiscounts.size());
        return ResponseEntity.ok(productBestDiscounts);
    }

    @GetMapping("/new-discounts")
    public List<Discount> getNewDiscounts() {
        return productDiscountService.getNewDiscounts();
    }
}
