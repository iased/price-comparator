package com.accesa.price_comparator.controller;

import com.accesa.price_comparator.model.PriceAlert;
import com.accesa.price_comparator.service.PriceAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/price-alerts")
public class PriceAlertController {
    private final PriceAlertService priceAlertService;

    @Autowired
    public PriceAlertController(PriceAlertService priceAlertService) {
        this.priceAlertService = priceAlertService;
    }

    @PostMapping
    public void createAlert(@RequestBody PriceAlert alert) {
        priceAlertService.createAlert(alert);
    }

    @GetMapping("/check")
    public List<PriceAlert> checkAlerts() {
        return priceAlertService.checkAlerts();
    }
}
