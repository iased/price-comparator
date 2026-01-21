package com.uo.price_comparator.service;

import com.uo.price_comparator.dto.GroceryListComparisonDto;
import com.uo.price_comparator.dto.GroceryListItemDto;
import com.uo.price_comparator.dto.ItemChoiceDto;
import com.uo.price_comparator.dto.ProductComparisonDto;
import com.uo.price_comparator.model.GroceryList;
import com.uo.price_comparator.model.GroceryListItem;
import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.model.ProductPrice;
import com.uo.price_comparator.repository.GroceryListItemRepository;
import com.uo.price_comparator.repository.GroceryListRepository;
import com.uo.price_comparator.repository.ProductPriceRepository;
import com.uo.price_comparator.repository.ProductRepository;
import com.uo.price_comparator.user.User;
import com.uo.price_comparator.user.UserRepository;
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
    private final UserRepository userRepo;
    private final GroceryListRepository groceryListRepo;

    public GroceryListService(GroceryListItemRepository groceryRepo,
                              ProductRepository productRepo,
                              ProductComparisonService comparisonService,
                              ProductPriceRepository priceRepo,
                              UserRepository userRepo,
                              GroceryListRepository groceryListRepo) {
        this.groceryRepo = groceryRepo;
        this.productRepo = productRepo;
        this.comparisonService = comparisonService;
        this.priceRepo = priceRepo;
        this.userRepo = userRepo;
        this.groceryListRepo = groceryListRepo;
    }

    @Transactional
    public GroceryListItemDto addItem(String email, Long productId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Cantitatea trebuie să fie mai mare decât 0.");
        }

        GroceryList list = getOrCreateForEmail(email);

        GroceryListItem item = groceryRepo.findByGroceryList_IdAndProduct_Id(list.getId(), productId)
                .orElseGet(() -> {
                    Product p = productRepo.findById(productId)
                            .orElseThrow(() -> new RuntimeException("Produsul " + productId + " nu a fost găsit."));
                    GroceryListItem newItem = new GroceryListItem();
                    newItem.setGroceryList(list);
                    newItem.setProduct(p);
                    newItem.setQuantity(0);
                    return newItem;
                });

        item.setQuantity(item.getQuantity() + quantity);
        GroceryListItem saved = groceryRepo.save(item);

        return toDto(saved);
    }

    @Transactional(readOnly = false)
    public List<GroceryListItemDto> getItems(String email) {
        GroceryList list = getOrCreateForEmail(email);

        return groceryRepo.findByGroceryList_Id(list.getId()).stream()
                .sorted(Comparator.comparing(
                        i -> i.getProduct().getName(),
                        String.CASE_INSENSITIVE_ORDER
                ))
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public GroceryListItemDto updateQuantity(String email, Long itemId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Cantitatea trebuie să fie mai mare decât 0.");
        }

        GroceryListItem item = groceryRepo.findByIdAndGroceryList_User_Email(itemId, email)
                .orElseThrow(() -> new RuntimeException("Produsul " + itemId + " din listă nu a fost găsit."));

        item.setQuantity(quantity);
        return toDto(groceryRepo.save(item));
    }

    public void delete(String email, Long itemId) {
        GroceryListItem item = groceryRepo
                .findByIdAndGroceryList_User_Email(itemId, email)
                .orElseThrow(() -> new RuntimeException("Produsul " + itemId + " din listă nu a fost găsit."));

        groceryRepo.delete(item);
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
    public GroceryListComparisonDto getBestSplitPlan(String email, int maxStores) {
        if (maxStores < 1) maxStores = 1;

        GroceryList list = getOrCreateForEmail(email);
        List<GroceryListItem> items = groceryRepo.findByGroceryList_Id(list.getId());

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

        Set<String> allStores = new HashSet<>();

        for (GroceryListItem it : items) {
            ProductComparisonDto cmp = comparisonService.getComparisonForProduct(it.getProduct().getId());
            for (var offer : cmp.getOffers()) {
                allStores.add(offer.getStore());
            }
        }

        List<String> stores = new ArrayList<>(allStores);
        Collections.sort(stores);

        int cap = Math.min(maxStores, stores.size());

        BestPlan best = null;

        for (int k = 1; k <= cap; k++) {
            for (List<String> combo : combinations(stores, k)) {
                BestPlan candidatePlan = evaluateCombo(items, combo);
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
                    BestPlan candidatePlan = evaluateCombo(items, combo2);
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

    private BestPlan evaluateCombo(List<GroceryListItem> items, List<String> allowedStores) {

        BigDecimal total = BigDecimal.ZERO;
        List<ItemChoiceDto> choices = new ArrayList<>();

        for (GroceryListItem it : items) {
            Long pid = it.getProduct().getId();
            int qty = it.getQuantity();

            ProductComparisonDto comparison =
                    comparisonService.getComparisonForProduct(pid);

            var bestOfferOpt = comparison.getOffers().stream()
                    .filter(o -> allowedStores.contains(o.getStore()))
                    .min(Comparator.comparing(
                            o -> o.getDiscountedPrice() != null
                                    ? o.getDiscountedPrice()
                                    : o.getPrice()
                    ));

            if (bestOfferOpt.isEmpty()) return null;

            var offer = bestOfferOpt.get();

            BigDecimal unitPrice =
                    offer.getDiscountedPrice() != null
                            ? offer.getDiscountedPrice()
                            : offer.getPrice();

            BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(qty));
            total = total.add(lineTotal);

            ItemChoiceDto c = new ItemChoiceDto();
            c.setItemId(it.getId());
            c.setProductId(pid);
            c.setName(it.getProduct().getName());
            c.setBrand(it.getProduct().getBrand());
            c.setPackageQuantityValue(it.getProduct().getQuantity());
            c.setPackageUnit(it.getProduct().getUnit());
            c.setQuantity(qty);
            c.setChosenStore(offer.getStore());
            c.setUnitPrice(unitPrice);
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

    public GroceryList getOrCreateForEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit."));

        return groceryListRepo.findByUser(user)
                .orElseGet(() -> {
                    GroceryList gl = new GroceryList();
                    gl.setUser(user);
                    return groceryListRepo.save(gl);
                });
    }
}
