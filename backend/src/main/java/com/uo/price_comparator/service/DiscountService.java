package com.uo.price_comparator.service;

import com.uo.price_comparator.model.Discount;
import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;;

    public DiscountService(DiscountRepository discountRepository){
        this.discountRepository = discountRepository;
    }

    public List<Discount> getAllDiscounts(){
        return discountRepository.findAll();
    }

    public List<Discount> getDiscountsToday() {
        LocalDate today = LocalDate.now();
        return discountRepository.findDiscountsForToday(today);
    }

    public List<Discount> getDiscountsThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1); // Monday
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        return discountRepository.findDiscountsForThisWeek(startOfWeek, endOfWeek);
    }

    public Double getActiveDiscountedPrice(ProductPrice pp){
        List<Discount> discounts = discountRepository.findActiveDiscounts(pp.getProduct(), pp.getSupermarket(), LocalDate.now());

        if (discounts.isEmpty()) {
            return pp.getPrice();
        }

        Discount best = discounts.stream()
                .max(Comparator.comparing(Discount::getPercentageOfDiscount))
                .get();

        return pp.getPrice() * (1 - best.getPercentageOfDiscount() / 100.0);
    }
}
