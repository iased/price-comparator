package com.uo.price_comparator.service;

import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.repository.ProductPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductPriceService {
    private final ProductPriceRepository productPriceRepository;

    public ProductPriceService(ProductPriceRepository productPriceRepository){
        this.productPriceRepository = productPriceRepository;
    }

    public List<Product> getProductsBySupermarket(String supermarket){
        return productPriceRepository.findBySupermarketName(supermarket)
                .stream()
                .map(ProductPrice::getProduct)
                .distinct()
                .collect(Collectors.toList());
    }
}
