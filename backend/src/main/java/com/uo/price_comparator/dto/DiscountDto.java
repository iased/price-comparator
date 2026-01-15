package com.uo.price_comparator.dto;

import java.time.LocalDate;

public record DiscountDto(
        Long id,
        String productName,
        String productBrand,
        String productImageUrl,
        String supermarketName,

        Double originalPrice,
        Double discountedPrice,

        Integer percentageOfDiscount,
        LocalDate fromDate,
        LocalDate toDate
) {}
