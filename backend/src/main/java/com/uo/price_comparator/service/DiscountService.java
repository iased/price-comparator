package com.uo.price_comparator.service;

import com.uo.price_comparator.dto.DiscountDto;
import com.uo.price_comparator.model.Discount;
import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    public List<DiscountDto> getDiscountsToday() {
        LocalDate today = LocalDate.now();
        return discountRepository.findDiscountsForToday(today);
    }

    public List<DiscountDto> getDiscountsThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1); // Monday
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        return discountRepository.findDiscountsForThisWeek(startOfWeek, endOfWeek, today);
    }

    public Optional<Discount> getBestActiveDiscount(ProductPrice pp, LocalDate date) {
        return discountRepository
                .findActiveDiscountsOrdered(pp.getProduct().getId(), pp.getSupermarket().getId(), date)
                .stream()
                .findFirst();
    }

    public double applyDiscount(double price, Integer percent) {
        if (percent == null) return price;
        return price * (1 - percent / 100.0);
    }
}
