package com.uo.price_comparator.controller;

import com.uo.price_comparator.dto.DiscountDto;
import com.uo.price_comparator.model.Discount;
import com.uo.price_comparator.service.DiscountService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@CrossOrigin(origins = "*")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService){
        this.discountService = discountService;
    }

    @GetMapping("/today")
    public List<DiscountDto> getDiscountsToday() {
        return discountService.getDiscountsToday();
    }

    @GetMapping("/this-week")
    public List<DiscountDto> getDiscountsThisWeek() {
        return discountService.getDiscountsThisWeek();
    }
}
