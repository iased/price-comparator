package com.uo.price_comparator.dto;

import java.math.BigDecimal;
import java.util.List;

public class GroceryListComparisonDto {
    private int maxStores;
    private List<String> storesUsed;
    private BigDecimal total;
    private List<ItemChoiceDto> items;
    private boolean feasible;
    private Integer minStoresNeeded;

    public int getMaxStores() { return maxStores; }
    public void setMaxStores(int maxStores) { this.maxStores = maxStores; }

    public List<String> getStoresUsed() { return storesUsed; }
    public void setStoresUsed(List<String> storesUsed) { this.storesUsed = storesUsed; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public List<ItemChoiceDto> getItems() { return items; }
    public void setItems(List<ItemChoiceDto> items) { this.items = items; }

    public boolean isFeasible() { return feasible; }
    public void setFeasible(boolean feasible) { this.feasible = feasible; }

    public Integer getMinStoresNeeded() { return minStoresNeeded; }
    public void setMinStoresNeeded(Integer minStoresNeeded) { this.minStoresNeeded = minStoresNeeded; }
}
