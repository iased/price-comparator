package com.uo.price_comparator.dto;

import java.math.BigDecimal;

public record PriceAlertDto(
        Long id,
        Long productId,
        String productName,
        String brand,
        BigDecimal quantity,
        String unit,
        String imageUrl,
        BigDecimal targetPrice,
        BigDecimal currentBestPrice,
        Long currentBestStoreId,
        String currentBestStoreName,
        boolean reached
) {}
