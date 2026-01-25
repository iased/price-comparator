package com.uo.price_comparator.service;

import com.uo.price_comparator.auth.AuthService;
import com.uo.price_comparator.dto.CreatePriceAlertRequest;
import com.uo.price_comparator.dto.PriceAlertDto;
import com.uo.price_comparator.model.PriceAlert;
import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PriceAlertService {
    private final PriceAlertRepository priceAlertRepository;
    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;
    private final DiscountService discountService;
    private final AuthService authService;

    public PriceAlertService(PriceAlertRepository priceAlertRepository,
                             ProductRepository productRepository,
                             ProductPriceRepository productPriceRepository,
                             DiscountService discountService,
                             AuthService authService) {
        this.priceAlertRepository = priceAlertRepository;
        this.productRepository = productRepository;
        this.productPriceRepository = productPriceRepository;
        this.discountService = discountService;
        this.authService = authService;
    }

    public PriceAlertDto createAlert(CreatePriceAlertRequest request) {
        Long userId = authService.getCurrentUserId();

        if (priceAlertRepository.existsByUserIdAndProductId(userId, request.productId())){
            throw new IllegalArgumentException("Există deja o alertă pentru acest produs.");
        }

        Product p = productRepository.findById(request.productId())
                .orElseThrow(() -> new RuntimeException("Produsul nu a fost găsit."));


        PriceAlert alert = new PriceAlert();
        alert.setUserId(userId);
        alert.setProductId(p.getId());
        alert.setTargetPrice(request.targetPrice());

        PriceAlert saved = priceAlertRepository.save(alert);

        return toDto(saved, p);
    }

    public List<PriceAlertDto> getUserAlerts() {
        Long userId = authService.getCurrentUserId();

        return priceAlertRepository.findByUserIdOrderByIdDesc(userId)
                .stream()
                .map(a -> {
                    Product p = productRepository.findById(a.getProductId())
                            .orElseThrow(() -> new IllegalArgumentException("Produsul nu a fost găsit."));
                    return toDto(a, p);
                })
                .toList();
    }

    @Transactional
    public void deleteAlert(Long alertId) {
        Long userId = authService.getCurrentUserId();
        priceAlertRepository.deleteByIdAndUserId(alertId, userId);
    }

    private PriceAlertDto toDto(PriceAlert a, Product p) {
        LocalDate today = LocalDate.now();

        List<ProductPrice> latest = productPriceRepository.findLatestPricesForProduct(p.getId());

        Optional<ProductPrice> best = latest.stream()
                .min(Comparator.comparing(pp -> effectivePrice(pp, today)));

        BigDecimal currentBestPrice = null;
        Long currentBestStoreId = null;
        String currentBestStoreName = null;

        if (best.isPresent()) {
            ProductPrice pp = best.get();
            currentBestPrice = effectivePrice(pp, today);
            currentBestStoreId = pp.getSupermarket().getId();
            currentBestStoreName = pp.getSupermarket().getName();
        }

        boolean reached = currentBestPrice != null
                && currentBestPrice.compareTo(a.getTargetPrice()) <= 0;

        return new PriceAlertDto(
                a.getId(),
                p.getId(),
                p.getName(),
                p.getBrand(),
                p.getQuantity(),
                p.getUnit(),
                p.getImageUrl(),
                a.getTargetPrice(),
                currentBestPrice,
                currentBestStoreId,
                currentBestStoreName,
                reached
        );
    }

    private BigDecimal effectivePrice(ProductPrice pp, LocalDate today) {
        return discountService.getBestActiveDiscount(pp, today)
                .map(d -> discountService.applyDiscount(pp.getPrice(), d.getPercentageOfDiscount()))
                .orElse(pp.getPrice());
    }
}
