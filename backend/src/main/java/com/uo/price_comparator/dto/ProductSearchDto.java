package com.uo.price_comparator.dto;

import java.math.BigDecimal;

public record ProductSearchDto(
        Long id,
        String name,
        String brand,
        BigDecimal quantity,
        String unit,
        String imageUrl
) {}
