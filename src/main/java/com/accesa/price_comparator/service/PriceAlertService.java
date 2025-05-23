package com.accesa.price_comparator.service;

import com.accesa.price_comparator.domain.Product;
import com.accesa.price_comparator.model.PriceAlert;
import com.accesa.price_comparator.repository.PriceAlertRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceAlertService {
    private final PriceAlertRepository repository;
    private final ProductDiscountService productDiscountService;

    public PriceAlertService(PriceAlertRepository repository, ProductDiscountService productDiscountService) {
        this.repository = repository;
        this.productDiscountService = productDiscountService;
    }

    public void createAlert(PriceAlert alert) {
        repository.saveAlert(alert);
    }

    public List<PriceAlert> checkAlerts(){
        List<PriceAlert> triggeredAlerts = new ArrayList<>();

        for(PriceAlert alert : repository.findAll()){
            if(alert.isTriggered()) continue;

            Product product = productDiscountService.getProduct(alert.getProductId(), alert.getStore());
            double currentPrice = product.getPrice();
            if(currentPrice <= alert.getTargetPrice()){
                alert.setTriggered(true);
                triggeredAlerts.add(alert);
            }
        }

        return triggeredAlerts;
    }
}
