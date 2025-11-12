package com.uo.price_comparator.service;

import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.repository.DiscountRepository;
import com.uo.price_comparator.repository.ProductPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPriceService {
    private final ProductPriceRepository productPriceRepository;
    private final DiscountService discountService;

    public ProductPriceService(ProductPriceRepository productPriceRepository, DiscountService discountService){
        this.productPriceRepository = productPriceRepository;
        this.discountService = discountService;
    }

    public List<ProductPrice> getProductPricesBySupermarket(String supermarket){
        return productPriceRepository.findBySupermarketName(supermarket);
    }

    public ProductPrice applyActiveDiscount(ProductPrice pp){
        pp.setDiscountedPrice(discountService.getActiveDiscountedPrice(pp));
        return pp;
    }
}
