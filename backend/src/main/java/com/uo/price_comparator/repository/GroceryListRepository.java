package com.uo.price_comparator.repository;

import com.uo.price_comparator.model.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryListRepository extends JpaRepository<GroceryList, Long> {
}
