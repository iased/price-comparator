package com.uo.price_comparator.repository;

import com.uo.price_comparator.model.GroceryListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroceryListItemRepository extends JpaRepository<GroceryListItem, Long> {
    Optional<GroceryListItem> findByProduct_Id(Long productId);
}
