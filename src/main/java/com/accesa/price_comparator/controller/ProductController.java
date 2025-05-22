package com.accesa.price_comparator.controller;

import com.accesa.price_comparator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.accesa.price_comparator.domain.Product;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{name}/best-buy")
    public List<Product> getBestBuyProducts(
            @PathVariable String name,
            @RequestParam(required = false) String brand) {
        if (brand == null) {
            return productService.getBestBuyProducts(name);
        } else {
            return productService.getBestBuyProducts(name, brand);
        }
    }
}
