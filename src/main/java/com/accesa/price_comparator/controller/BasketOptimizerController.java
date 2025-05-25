package com.accesa.price_comparator.controller;

import com.accesa.price_comparator.domain.BasketItem;
import com.accesa.price_comparator.service.BasketOptimizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/basket")
public class BasketOptimizerController {
    private final BasketOptimizerService basketOptimizerService;

    @Autowired
    public BasketOptimizerController(BasketOptimizerService basketOptimizerService) {
        this.basketOptimizerService = basketOptimizerService;
    }

    @PostMapping("/optimize")
    public Map<String, List<BasketItem>> optimizeBasket(@RequestBody List<BasketItem> basketItems) {
        return basketOptimizerService.splitBasketByStore(basketItems);
    }
}
