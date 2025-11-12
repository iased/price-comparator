package com.uo.price_comparator.controller;

import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.service.ProductPriceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductPriceController {
    private final ProductPriceService productPriceService;

    public ProductPriceController(ProductPriceService productPriceService){
        this.productPriceService = productPriceService;
    }

    @GetMapping("/supermarkets/{name}/products")
    public List<ProductPrice> getProductPricesBySupermarket(@PathVariable String name) {
        LocalDate today = LocalDate.now();

        return productPriceService.getProductPricesBySupermarket(name)
                .stream()
                .filter(pp -> !pp.getPriceDate().isAfter(today))
                .collect(Collectors.toMap(
                        pp -> pp.getProduct().getId(),
                        productPriceService::applyActiveDiscount,
                        (pp1, pp2) -> pp1.getPriceDate().isAfter(pp2.getPriceDate()) ? pp1 : pp2))
                .values()
                .stream()
                .collect(Collectors.toList());
    }
}
