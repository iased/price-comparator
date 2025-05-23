package com.accesa.price_comparator.service;

import com.accesa.price_comparator.domain.Discount;
import com.accesa.price_comparator.domain.Product;
import com.accesa.price_comparator.dto.ProductBestDiscount;
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

        List<ProductBestDiscount> bestDiscounts = productDiscountService.getProductsWithBestDiscounts();

        for (PriceAlert alert : repository.findAll()) {
            if (alert.isTriggered()) continue;

            ProductBestDiscount bestDiscount = null;

            for (ProductBestDiscount productBestDiscount : bestDiscounts) {
                if(productBestDiscount.getProduct().getId().equals(alert.getProductId())) {
                    bestDiscount = productBestDiscount;
                    break;
                }
            }

            if(bestDiscount != null) {
                Product product = bestDiscount.getProduct();
                Discount discount = bestDiscount.getDiscount();

                double discountedPrice = productDiscountService.calculateDiscountedPrice(product.getPrice(), discount.getDiscount());
                if (discountedPrice <= alert.getTargetPrice()) {
                    alert.setDiscountedPrice(discountedPrice);
                    triggeredAlerts.add(alert);
                    repository.saveAlert(alert);
                    alert.setTriggered(true);
                }
            } else{
                Product product = productDiscountService.getLatestProductSnapshot(alert.getProductId());
                if (product.getPrice() <= alert.getTargetPrice()) {
                    alert.setDiscountedPrice(product.getPrice());
                    triggeredAlerts.add(alert);
                    repository.saveAlert(alert);
                    alert.setTriggered(true);
                }
            }
        }
        return triggeredAlerts;
    }
}
