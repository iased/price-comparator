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

        Map<Long, List<ProductPrice>> byProduct = latestPrices.stream()
                .collect(Collectors.groupingBy(pp -> pp.getProduct().getId()));

        List<ProductComparisonDto> result = new ArrayList<>();
        for (List<ProductPrice> prices : byProduct.values()) {
            result.add(buildDto(prices, today));
        }

        result.sort(Comparator.comparing(ProductComparisonDto::getName, String.CASE_INSENSITIVE_ORDER));
        return result;
    }

    public ProductComparisonDto getComparisonForProduct(Long productId) {
        LocalDate today = LocalDate.now();

        List<ProductPrice> pricesForProduct = productPriceRepository.findLatestPricesForProduct(productId);

        if (pricesForProduct.isEmpty()) {
            throw new RuntimeException("No prices for product " + productId);
        }

        return buildDto(pricesForProduct, today);
    }

    private ProductComparisonDto buildDto(List<ProductPrice> pricesForProduct, LocalDate today) {
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

            if (product.getQuantity().compareTo(BigDecimal.ZERO) != 0) {
                offer.setPricePerUnit(
                        pp.getPrice().divide(product.getQuantity(), 2, RoundingMode.HALF_UP)
                );
            }

            discountService.getBestActiveDiscount(pp, today).ifPresent(d -> {
                offer.setDiscountPercent(d.getPercentageOfDiscount());

                BigDecimal discounted = discountService.applyDiscount(
                        pp.getPrice(),
                        d.getPercentageOfDiscount()
                );

                offer.setDiscountedPrice(discounted);

                if (product.getQuantity().compareTo(BigDecimal.ZERO) != 0) {
                    offer.setDiscountedPricePerUnit(
                            discounted.divide(product.getQuantity(), 2, RoundingMode.HALF_UP)
                    );
                }
            });


            offers.add(offer);
        }

        dto.setOffers(offers);

        offers.stream()
                .min(Comparator.comparing(
                        (OfferDto o) ->
                                o.getDiscountedPrice() != null
                                        ? o.getDiscountedPrice()
                                        : o.getPrice()
                ))
                .ifPresent(dto::setBestOffer);

        return dto;
    }
}
