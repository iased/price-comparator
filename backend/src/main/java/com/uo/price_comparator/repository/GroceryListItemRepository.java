package com.uo.price_comparator.repository;

import com.uo.price_comparator.model.GroceryListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroceryListItemRepository extends JpaRepository<GroceryListItem, Long> {
    Optional<GroceryListItem> findByProduct_Id(Long productId);
    List<GroceryListItem> findByGroceryList_Id(Long id);
    Optional<GroceryListItem> findByGroceryList_IdAndProduct_Id(Long id, Long productId);
    Optional<GroceryListItem> findByIdAndGroceryList_User_Email(Long itemId, String email);
}
