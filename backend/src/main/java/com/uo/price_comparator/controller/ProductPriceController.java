package com.uo.price_comparator.controller;

import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.service.ProductPriceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductPriceController {
    private final ProductPriceService productPriceService;

    public ProductPriceController(ProductPriceService productPriceService){
        this.productPriceService = productPriceService;
    }

    @GetMapping("/supermarkets/{name}/products")
    public List<Product> getProductsBySupermarket(@PathVariable String name) {
        return productPriceService.getProductsBySupermarket(name);
    }
}
