package com.uo.price_comparator.dto;

import java.math.BigDecimal;

public record CreatePriceAlertRequest(
        Long productId,
        BigDecimal targetPrice
) {}