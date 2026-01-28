package com.uo.price_comparator.service;

import com.uo.price_comparator.dto.OfferDto;
import com.uo.price_comparator.dto.ProductComparisonDto;
import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.repository.ProductPriceRepository;
import com.uo.price_comparator.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductComparisonService {
    private final ProductPriceRepository productPriceRepository;
    private final DiscountService discountService;
    private final ProductRepository productRepository;

    private static final Pattern DIACRITICS = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    public ProductComparisonService(ProductPriceRepository productPriceRepository,
                                    DiscountService discountService,
                                    ProductRepository productRepository) {
        this.productPriceRepository = productPriceRepository;
        this.discountService = discountService;
        this.productRepository = productRepository;
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

    public List<ProductComparisonDto> getComparison(String q, Long storeId, String category) {
        LocalDate today = LocalDate.now();

        String query = (q == null) ? null : q.trim();
        boolean hasQuery = query != null && query.length() >= 2;

        List<Product> products;

        if (hasQuery) {
            if (storeId == null) {
                products = productRepository.searchDiacriticsInsensitive(query, category);
            } else {
                products = productRepository.searchAllDiacriticsInsensitiveInStore(query, storeId, category);
            }
        } else {
            if (storeId == null) {
                products = productRepository.findAll();
            } else {
                products = productRepository.findAllAvailableInStore(storeId);
            }
        }

        String cat = (category == null || category.isBlank()) ? null : category.trim();

        if (cat != null) {
            String ncat = norm(cat);
            products = products.stream()
                    .filter(p -> p.getCategory() != null && norm(p.getCategory()).equals(ncat))
                    .toList();
        }

        if (products.isEmpty()) return List.of();

        List<Long> ids = products.stream().map(Product::getId).toList();

        List<ProductPrice> latestPrices = productPriceRepository.findLatestPricesForProducts(ids);

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
            throw new RuntimeException("Nu există prețuri pentru produsul " + productId);
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

    private static String norm(String s) {
        if (s == null) return null;
        String x = Normalizer.normalize(s.trim(), Normalizer.Form.NFD);
        x = DIACRITICS.matcher(x).replaceAll("");
        return x.toLowerCase(Locale.ROOT);
    }
}
