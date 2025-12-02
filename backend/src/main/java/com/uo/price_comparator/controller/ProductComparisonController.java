package com.uo.price_comparator.controller;

import com.uo.price_comparator.dto.ProductComparisonDto;
import com.uo.price_comparator.service.ProductComparisonService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductComparisonController {
    private final ProductComparisonService productComparisonService;

    public ProductComparisonController(ProductComparisonService productComparisonService){
        this.productComparisonService = productComparisonService;
    }

    @GetMapping("/comparison")
    public List<ProductComparisonDto> getProductComparison() {
        return productComparisonService.getComparison();
    }
}
