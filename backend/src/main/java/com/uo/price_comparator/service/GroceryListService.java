package com.uo.price_comparator.service;

import com.uo.price_comparator.dto.GroceryListComparisonDto;
import com.uo.price_comparator.dto.GroceryListItemDto;
import com.uo.price_comparator.dto.ItemChoiceDto;
import com.uo.price_comparator.dto.ProductComparisonDto;
import com.uo.price_comparator.model.GroceryListItem;
import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.repository.GroceryListItemRepository;
import com.uo.price_comparator.repository.ProductPriceRepository;
import com.uo.price_comparator.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroceryListService {

    private final GroceryListItemRepository groceryRepo;
    private final ProductRepository productRepo;
    private final ProductComparisonService comparisonService;
    private final ProductPriceRepository priceRepo;

    public GroceryListService(GroceryListItemRepository groceryRepo,
                              ProductRepository productRepo,
                              ProductComparisonService comparisonService,
                              ProductPriceRepository priceRepo) {
        this.groceryRepo = groceryRepo;
        this.productRepo = productRepo;
        this.comparisonService = comparisonService;
        this.priceRepo = priceRepo;
    }

    @Transactional
    public GroceryListItemDto addItem(Long productId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantity must be > 0");
        }

        GroceryListItem item = groceryRepo.findByProduct_Id(productId)
                .orElseGet(() -> {
                    Product p = productRepo.findById(productId)
                            .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
                    GroceryListItem newItem = new GroceryListItem();
                    newItem.setProduct(p);
                    newItem.setQuantity(0);
                    return newItem;
                });

        item.setQuantity(item.getQuantity() + quantity);
        GroceryListItem saved = groceryRepo.save(item);

        return toDto(saved);
    }

    @Transactional(readOnly = false)
    public List<GroceryListItemDto> getItems() {
        return groceryRepo.findAll().stream()
                .sorted(Comparator.comparing(i -> i.getProduct().getName(), String.CASE_INSENSITIVE_ORDER))
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public GroceryListItemDto updateQuantity(Long itemId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantity must be > 0");
        }

        GroceryListItem item = groceryRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Grocery item not found: " + itemId));

        item.setQuantity(quantity);
        return toDto(groceryRepo.save(item));
    }

    public void delete(Long itemId) {
        groceryRepo.deleteById(itemId);
    }

    private GroceryListItemDto toDto(GroceryListItem item) {
        Product p = item.getProduct();

        ProductComparisonDto comparison = comparisonService.getComparisonForProduct(p.getId());

        GroceryListItemDto dto = new GroceryListItemDto();
        dto.setId(item.getId());
        dto.setProductId(p.getId());
        dto.setName(p.getName());
        dto.setBrand(p.getBrand());
        dto.setQuantityValue(p.getQuantity());
        dto.setUnit(p.getUnit());
        dto.setImageUrl(p.getImageUrl());
        dto.setListQuantity(item.getQuantity());

        dto.setBestOffer(comparison.getBestOffer());

        if (comparison.getBestOffer() != null) {
            BigDecimal bestPrice =
                    comparison.getBestOffer().getDiscountedPrice() != null
                            ? comparison.getBestOffer().getDiscountedPrice()
                            : comparison.getBestOffer().getPrice();

            dto.setLineTotal(
                    bestPrice.multiply(BigDecimal.valueOf(item.getQuantity()))
            );
        }

        return dto;
    }

    @Transactional(readOnly = true)
    public GroceryListComparisonDto getBestSplitPlan(int maxStores) {
        if (maxStores < 1) maxStores = 1;

        List<GroceryListItem> items = groceryRepo.findAll(); // or user’s list
        if (items.isEmpty()) {
            GroceryListComparisonDto empty = new GroceryListComparisonDto();
            empty.setMaxStores(maxStores);
            empty.setFeasible(true);
            empty.setMinStoresNeeded(0);
            empty.setStoresUsed(List.of());
            empty.setTotal(BigDecimal.ZERO);
            empty.setItems(List.of());
            return empty;
        }

        List<Long> productIds = items.stream()
                .map(i -> i.getProduct().getId())
                .distinct()
                .toList();

        List<ProductPrice> latest = priceRepo.findLatestPricesForProducts(productIds);

        Map<Long, Map<String, BigDecimal>> pricesByProduct = new HashMap<>();
        Set<String> allStores = new HashSet<>();

        for (ProductPrice pp : latest) {
            Long pid = pp.getProduct().getId();
            String store = pp.getSupermarket().getName();
            BigDecimal unit = pp.getPrice();

            pricesByProduct.computeIfAbsent(pid, k -> new HashMap<>()).put(store, unit);
            allStores.add(store);
        }

        List<String> stores = new ArrayList<>(allStores);
        Collections.sort(stores);

        int cap = Math.min(maxStores, stores.size());

        BestPlan best = null;

        for (int k = 1; k <= cap; k++) {
            for (List<String> combo : combinations(stores, k)) {
                BestPlan candidatePlan = evaluateCombo(items, pricesByProduct, combo);
                if (candidatePlan == null) continue;

                if (best == null || candidatePlan.total.compareTo(best.total) < 0) {
                    best = candidatePlan;
                }
            }
        }

        if (best == null) {
            Integer minNeeded = null;

            for (int k2 = 1; k2 <= stores.size(); k2++) {
                boolean found = false;

                for (List<String> combo2 : combinations(stores, k2)) {
                    BestPlan candidatePlan = evaluateCombo(items, pricesByProduct, combo2);
                    if (candidatePlan == null) continue;

                    found = true;
                    break;
                }

                if (found) {
                    minNeeded = k2;
                    break;
                }
            }

            GroceryListComparisonDto dto = new GroceryListComparisonDto();
            dto.setMaxStores(maxStores);
            dto.setFeasible(false);
            dto.setStoresUsed(List.of());
            dto.setTotal(BigDecimal.ZERO);
            dto.setItems(List.of());
            dto.setMinStoresNeeded(minNeeded);

            return dto;
        }

        GroceryListComparisonDto dto = new GroceryListComparisonDto();
        dto.setMaxStores(maxStores);
        dto.setStoresUsed(best.storesUsed);
        dto.setTotal(best.total);
        dto.setItems(best.items);
        dto.setFeasible(true);
        dto.setMinStoresNeeded(best.storesUsed.size());
        return dto;
    }

    private BestPlan evaluateCombo(List<GroceryListItem> items,
                                   Map<Long, Map<String, BigDecimal>> pricesByProduct,
                                   List<String> allowedStores) {

        BigDecimal total = BigDecimal.ZERO;
        List<ItemChoiceDto> choices = new ArrayList<>();

        for (GroceryListItem it : items) {
            Long pid = it.getProduct().getId();
            int qty = it.getQuantity();

            Map<String, BigDecimal> byStore = pricesByProduct.get(pid);
            if (byStore == null) return null;

            String bestStore = null;
            BigDecimal bestPrice = null;

            for (String s : allowedStores) {
                BigDecimal price = byStore.get(s);
                if (price == null) continue;
                if (bestPrice == null || price.compareTo(bestPrice) < 0) {
                    bestPrice = price;
                    bestStore = s;
                }
            }

            if (bestStore == null) return null;

            BigDecimal lineTotal = bestPrice.multiply(BigDecimal.valueOf(qty));
            total = total.add(lineTotal);

            ItemChoiceDto c = new ItemChoiceDto();
            c.setItemId(it.getId());
            c.setProductId(pid);
            c.setName(it.getProduct().getName());
            c.setBrand(it.getProduct().getBrand());
            c.setQuantity(qty);
            c.setChosenStore(bestStore);
            c.setUnitPrice(bestPrice);
            c.setLineTotal(lineTotal);

            choices.add(c);
        }

        BestPlan plan = new BestPlan();
        plan.items = choices;
        plan.total = total;
        plan.storesUsed = choices.stream()
                .map(ItemChoiceDto::getChosenStore)
                .distinct()
                .sorted()
                .toList();
        return plan;
    }

    private static class BestPlan {
        List<String> storesUsed;
        BigDecimal total;
        List<ItemChoiceDto> items;
    }

    private static List<List<String>> combinations(List<String> stores, int k) {
        List<List<String>> res = new ArrayList<>();
        backtrack(stores, k, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(List<String> stores, int k, int idx, List<String> cur, List<List<String>> res) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = idx; i < stores.size(); i++) {
            cur.add(stores.get(i));
            backtrack(stores, k, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}
