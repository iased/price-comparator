package com.uo.price_comparator.controller;

import com.uo.price_comparator.dto.ProductComparisonDto;
import com.uo.price_comparator.dto.ProductSearchDto;
import com.uo.price_comparator.service.ProductComparisonService;
import com.uo.price_comparator.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")

public class ProductComparisonController {
    private final ProductComparisonService productComparisonService;
    private final ProductService productService;

    public ProductComparisonController(ProductComparisonService productComparisonService,
                                       ProductService productService){
        this.productComparisonService = productComparisonService;
        this.productService = productService;
    }

    @GetMapping("/comparison")
    public List<ProductComparisonDto> getAll() {
        return productComparisonService.getComparison();
    }

    @GetMapping("/comparison/{productId}")
    public ProductComparisonDto getOne(@PathVariable Long productId) {
        return productComparisonService.getComparisonForProduct(productId);
    }

    @GetMapping("/search")
    public List<ProductSearchDto> search(@RequestParam String q) {
        return productService.searchProducts(q);
    }
}


