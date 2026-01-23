package com.uo.price_comparator.repository;

import com.uo.price_comparator.model.GroceryList;
import com.uo.price_comparator.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface GroceryListRepository extends JpaRepository<GroceryList, Long> {
    Optional<GroceryList> findByUser(User user);
    Optional<GroceryList> findByUser_Email(String email);

    @Transactional
    void deleteByUser(User user);
}
