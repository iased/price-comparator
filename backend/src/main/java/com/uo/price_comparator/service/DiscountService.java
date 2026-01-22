package com.uo.price_comparator.service;

import com.uo.price_comparator.dto.DiscountDto;
import com.uo.price_comparator.model.Discount;
import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository){
        this.discountRepository = discountRepository;
    }

    public List<DiscountDto> getDiscountsToday() {
        LocalDate today = LocalDate.now();
        return discountRepository.findDiscountsForToday(today)
                .stream()
                .map(this::addUnitPrice)
                .toList();
    }

    public List<DiscountDto> getDiscountsThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1); // Monday
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        return discountRepository
                .findDiscountsForThisWeek(startOfWeek, endOfWeek, today)
                .stream()
                .map(this::addUnitPrice)
                .toList();
    }

    public Optional<Discount> getBestActiveDiscount(ProductPrice pp, LocalDate date) {
        return discountRepository
                .findActiveDiscountsOrdered(pp.getProduct().getId(), pp.getSupermarket().getId(), date)
                .stream()
                .findFirst();
    }

    public BigDecimal applyDiscount(BigDecimal price, int percent) {
        BigDecimal multiplier = BigDecimal.ONE.subtract(
                BigDecimal.valueOf(percent)
                        .divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP)
        );

        return price.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
    }

    private DiscountDto addUnitPrice(DiscountDto dto) {

        if (dto == null || dto.quantity() == null) return dto;
        if (dto.quantity().compareTo(BigDecimal.ZERO) <= 0) return dto;

        BigDecimal normalizedQuantity = dto.quantity();
        String unitLabel = dto.unit();

        if ("g".equalsIgnoreCase(dto.unit())) {
            normalizedQuantity = dto.quantity().divide(BigDecimal.valueOf(1000), 6, RoundingMode.HALF_UP);
            unitLabel = "kg";
        } else if ("ml".equalsIgnoreCase(dto.unit())) {
            normalizedQuantity = dto.quantity().divide(BigDecimal.valueOf(1000), 6, RoundingMode.HALF_UP);
            unitLabel = "l";
        }

        if (normalizedQuantity.compareTo(BigDecimal.ZERO) <= 0) return dto;

        BigDecimal unitPriceOriginal = null;
        if (dto.originalPrice() != null) {
            unitPriceOriginal = dto.originalPrice().divide(normalizedQuantity, 2, RoundingMode.HALF_UP);
        }

        BigDecimal unitPriceDiscounted = null;
        if (dto.discountedPrice() != null) {
            unitPriceDiscounted = dto.discountedPrice().divide(normalizedQuantity, 2, RoundingMode.HALF_UP);
        }

        return new DiscountDto(
                dto.id(),
                dto.productName(),
                dto.productBrand(),
                dto.productImageUrl(),
                dto.supermarketName(),
                dto.originalPrice(),
                dto.discountedPrice(),
                dto.percentageOfDiscount(),
                dto.fromDate(),
                dto.toDate(),
                dto.quantity(),
                dto.unit(),
                unitPriceOriginal,
                unitPriceDiscounted,
                unitLabel
        );
    }
}
