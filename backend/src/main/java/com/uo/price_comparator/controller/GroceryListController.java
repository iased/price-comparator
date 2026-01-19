package com.uo.price_comparator.controller;

import com.uo.price_comparator.dto.AddGroceryItemRequest;
import com.uo.price_comparator.dto.GroceryListComparisonDto;
import com.uo.price_comparator.dto.GroceryListItemDto;
import com.uo.price_comparator.dto.UpdateGroceryItemRequest;
import com.uo.price_comparator.service.GroceryListService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grocery-list")
@CrossOrigin(origins = "*")
public class GroceryListController {

    private final GroceryListService groceryListService;

    public GroceryListController(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    @PostMapping("/items")
    public GroceryListItemDto addItem(Authentication auth,
                                      @RequestBody AddGroceryItemRequest req) {
        String email = auth.getName();
        return groceryListService.addItem(email, req.getProductId(), req.getQuantity());
    }

    @GetMapping("/items")
    public List<GroceryListItemDto> getItems(Authentication auth) {
        String email = auth.getName();
        return groceryListService.getItems(email);
    }

    @PatchMapping("/items/{itemId}")
    public GroceryListItemDto updateQuantity(Authentication auth,
                                             @PathVariable Long itemId,
                                             @RequestBody UpdateGroceryItemRequest req) {
        String email = auth.getName();
        return groceryListService.updateQuantity(email, itemId, req.getQuantity());
    }

    @DeleteMapping("/items/{itemId}")
    public void deleteItem(Authentication auth,
                           @PathVariable Long itemId) {
        String email = auth.getName();
        groceryListService.delete(email, itemId);
    }

    @GetMapping("/comparison")
    public GroceryListComparisonDto compare(Authentication auth,
                                            @RequestParam(defaultValue = "1") int maxStores) {
        String email = auth.getName();
        return groceryListService.getBestSplitPlan(email, maxStores);
    }
}
