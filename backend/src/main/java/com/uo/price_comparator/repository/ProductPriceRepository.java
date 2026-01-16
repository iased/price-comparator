package com.uo.price_comparator.repository;

import com.uo.price_comparator.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    List<ProductPrice> findBySupermarketName(String name);

    @Query("""
        SELECT pp FROM ProductPrice pp
        JOIN FETCH pp.product p
        JOIN FETCH pp.supermarket s
        WHERE pp.priceDate = (
          SELECT MAX(pp2.priceDate)
          FROM ProductPrice pp2
          WHERE pp2.product = pp.product
            AND pp2.supermarket = pp.supermarket
        )
    """)
    List<ProductPrice> findLatestPricesForAllProducts();

    @Query("""
    SELECT pp FROM ProductPrice pp
    JOIN FETCH pp.product p
    JOIN FETCH pp.supermarket s
    WHERE p.id = :productId
      AND pp.priceDate = (
        SELECT MAX(pp2.priceDate)
        FROM ProductPrice pp2
        WHERE pp2.product = pp.product
          AND pp2.supermarket = pp.supermarket
      )
""")
    List<ProductPrice> findLatestPricesForProduct(@Param("productId") Long productId);

    @Query("""
    SELECT pp FROM ProductPrice pp
    JOIN FETCH pp.product p
    JOIN FETCH pp.supermarket s
    WHERE p.id IN (:productIds)
      AND pp.priceDate = (
        SELECT MAX(pp2.priceDate)
        FROM ProductPrice pp2
        WHERE pp2.product = pp.product
          AND pp2.supermarket = pp.supermarket
      )
""")
    List<ProductPrice> findLatestPricesForProducts(@Param("productIds") List<Long> productIds);
}
