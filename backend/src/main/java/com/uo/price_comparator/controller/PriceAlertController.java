package com.uo.price_comparator.controller;

import com.uo.price_comparator.dto.CreatePriceAlertRequest;
import com.uo.price_comparator.dto.PriceAlertDto;
import com.uo.price_comparator.service.PriceAlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price-alerts")
@CrossOrigin(origins = "*")
public class PriceAlertController {

    private final PriceAlertService priceAlertService;

    public PriceAlertController(PriceAlertService priceAlertService) {
        this.priceAlertService = priceAlertService;
    }

    @PostMapping
    public PriceAlertDto create(@RequestBody CreatePriceAlertRequest request) {
        return priceAlertService.createAlert(request);
    }

    @GetMapping
    public List<PriceAlertDto> getUserAlerts() {
        return priceAlertService.getUserAlerts();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        priceAlertService.deleteAlert(id);
    }
}
