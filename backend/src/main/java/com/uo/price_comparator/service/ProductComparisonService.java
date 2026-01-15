package com.uo.price_comparator.service;

import com.uo.price_comparator.dto.OfferDto;
import com.uo.price_comparator.dto.ProductComparisonDto;
import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.repository.ProductPriceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductComparisonService {
    private final ProductPriceRepository productPriceRepository;
    private final DiscountService discountService;

    public ProductComparisonService(ProductPriceRepository productPriceRepository,
                                    DiscountService discountService) {
        this.productPriceRepository = productPriceRepository;
        this.discountService = discountService;
    }

    public List<ProductComparisonDto> getComparison() {
        LocalDate today = LocalDate.now();

        List<ProductPrice> latestPrices = productPriceRepository.findLatestPricesForAllProducts();

        // group by product
        Map<Long, List<ProductPrice>> byProduct = latestPrices.stream()
                .collect(Collectors.groupingBy(pp -> pp.getProduct().getId()));

        // build DTO per product
        List<ProductComparisonDto> result = new ArrayList<>();

        for (Map.Entry<Long, List<ProductPrice>> entry : byProduct.entrySet()) {
            List<ProductPrice> pricesForProduct = entry.getValue();
            if (pricesForProduct.isEmpty()) continue;

            Product product = pricesForProduct.get(0).getProduct();
            ProductComparisonDto dto = new ProductComparisonDto();
            dto.setProductId(product.getId());
            dto.setName(product.getName());
            dto.setBrand(product.getBrand());
            dto.setCategory(product.getCategory());
            dto.setQuantity(product.getQuantity());
            dto.setUnit(product.getUnit());
            dto.setImageUrl(product.getImageUrl());

            List<OfferDto> offers = new ArrayList<>();

            for (ProductPrice pp : pricesForProduct) {
                OfferDto offer = new OfferDto();
                offer.setStore(pp.getSupermarket().getName());
                offer.setPrice(pp.getPrice());
                offer.setDate(pp.getPriceDate());

                // price per unit: price / quantity
                if (product.getQuantity() != 0) {
                    double pricePerUnit = pp.getPrice() / product.getQuantity();
                    offer.setPricePerUnit(pricePerUnit);
                }

                if (pp.getDiscountedPrice() != null) {
                    offer.setDiscountedPrice(pp.getDiscountedPrice());

                    if (product.getQuantity() != 0) {
                        double discountedPPU = pp.getDiscountedPrice() / product.getQuantity();
                        offer.setDiscountedPricePerUnit(discountedPPU);
                    }
                }

                discountService.getBestActiveDiscount(pp, today).ifPresent(d -> {
                    offer.setDiscountPercent(d.getPercentageOfDiscount());

                    double discounted = discountService.applyDiscount(pp.getPrice(), d.getPercentageOfDiscount());
                    offer.setDiscountedPrice(discounted);

                    if (product.getQuantity() != 0) {
                        offer.setDiscountedPricePerUnit(discounted / product.getQuantity());
                    }
                });

                offers.add(offer);
            }

            dto.setOffers(offers);

            // best offer
            offers.stream()
                    .min(Comparator.comparingDouble(o ->
                            o.getDiscountedPrice() != null ? o.getDiscountedPrice() : o.getPrice()
                    ))
                    .ifPresent(dto::setBestOffer);


            result.add(dto);
        }

        // sort products by name
        result.sort(Comparator.comparing(ProductComparisonDto::getName,
                String.CASE_INSENSITIVE_ORDER));

        return result;
    }
}
