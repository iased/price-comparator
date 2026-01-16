package com.uo.price_comparator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DiscountDto(
        Long id,
        String productName,
        String productBrand,
        String productImageUrl,
        String supermarketName,

        BigDecimal originalPrice,
        BigDecimal discountedPrice,

        Integer percentageOfDiscount,
        LocalDate fromDate,
        LocalDate toDate
) {}
