package com.uo.price_comparator.repository;

import com.uo.price_comparator.PriceComparatorApplication;
import com.uo.price_comparator.model.Discount;
import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("""
        SELECT d FROM Discount d
        WHERE d.product = :product
          AND d.supermarket = :supermarket
          AND :priceDate BETWEEN d.fromDate AND d.toDate
    """)
    List<Discount> findActiveDiscounts(@Param("product") Product product,
                                       @Param("supermarket") Supermarket supermarket,
                                       @Param("priceDate") LocalDate priceDate);

    @Query("""
        SELECT d FROM Discount d
        WHERE :today BETWEEN d.fromDate AND d.toDate
    """)
    List<Discount> findDiscountsForToday(@Param("today") LocalDate today);

    @Query("""
        SELECT d FROM Discount d
        WHERE d.fromDate <= :endOfWeek AND d.toDate >= :startOfWeek
    """)
    List<Discount> findDiscountsForThisWeek(@Param("startOfWeek") LocalDate startOfWeek,
                                        @Param("endOfWeek") LocalDate endOfWeek);
}
