package com.uo.price_comparator.controller;

import com.uo.price_comparator.dto.AddGroceryItemRequest;
import com.uo.price_comparator.dto.GroceryListComparisonDto;
import com.uo.price_comparator.dto.GroceryListItemDto;
import com.uo.price_comparator.dto.UpdateGroceryItemRequest;
import com.uo.price_comparator.service.GroceryListService;
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
    public GroceryListItemDto addItem(@RequestBody AddGroceryItemRequest req) {
        return groceryListService.addItem(req.getProductId(), req.getQuantity());
    }

    @GetMapping("/items")
    public List<GroceryListItemDto> getItems() {
        return groceryListService.getItems();
    }

    @PatchMapping("/items/{itemId}")
    public GroceryListItemDto updateQuantity(@PathVariable Long itemId,
                                             @RequestBody UpdateGroceryItemRequest req) {
        return groceryListService.updateQuantity(itemId, req.getQuantity());
    }

    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        groceryListService.delete(itemId);
    }

    @GetMapping("/comparison")
    public GroceryListComparisonDto compare(@RequestParam(defaultValue = "1") int maxStores) {
        return groceryListService.getBestSplitPlan(maxStores);
    }
}
