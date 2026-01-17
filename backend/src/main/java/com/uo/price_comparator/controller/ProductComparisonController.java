package com.uo.price_comparator.controller;

import com.uo.price_comparator.dto.ProductComparisonDto;
import com.uo.price_comparator.service.ProductComparisonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")

public class ProductComparisonController {
    private final ProductComparisonService productComparisonService;

    public ProductComparisonController(ProductComparisonService productComparisonService){
        this.productComparisonService = productComparisonService;
    }

    @GetMapping("/comparison")
    public List<ProductComparisonDto> getAll() {
        return productComparisonService.getComparison();
    }

    @GetMapping("/comparison/{productId}")
    public ProductComparisonDto getOne(@PathVariable Long productId) {
        return productComparisonService.getComparisonForProduct(productId);
    }
}


