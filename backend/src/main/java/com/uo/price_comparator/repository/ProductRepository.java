package com.uo.price_comparator.repository;

import com.uo.price_comparator.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = """
        SELECT *
        FROM products p
        WHERE
            (
              unaccent(lower(p.name))  LIKE unaccent(lower(concat('%', :q, '%')))
              OR unaccent(lower(p.brand)) LIKE unaccent(lower(concat('%', :q, '%')))
            )
           AND (:category IS NULL OR unaccent(lower(p.category)) = unaccent(lower(:category)))
        ORDER BY p.name
        LIMIT 10
        """, nativeQuery = true)
    List<Product> searchDiacriticsInsensitiveLimited(@Param("q") String q,
                                                     @Param("category") String category);

    @Query(value = """
        SELECT *
        FROM products p
        WHERE
            (
              unaccent(lower(p.name))  LIKE unaccent(lower(concat('%', :q, '%')))
              OR unaccent(lower(p.brand)) LIKE unaccent(lower(concat('%', :q, '%')))
            )
           AND (:category IS NULL OR unaccent(lower(p.category)) = unaccent(lower(:category)))
        ORDER BY p.name
        """, nativeQuery = true)
    List<Product> searchDiacriticsInsensitive(@Param("q") String q,
                                              @Param("category") String category);

    @Query(value = """
        SELECT DISTINCT p.*
        FROM products p
        JOIN product_prices pp ON pp.product_id = p.id
        WHERE pp.supermarket_id = :storeId
          AND (
            unaccent(lower(p.name))  LIKE unaccent(lower(concat('%', :q, '%')))
            OR unaccent(lower(p.brand)) LIKE unaccent(lower(concat('%', :q, '%')))
          )
          AND (:category IS NULL OR unaccent(lower(p.category)) = unaccent(lower(:category)))
        ORDER BY p.name
        """, nativeQuery = true)
    List<Product> searchAllDiacriticsInsensitiveInStore(@Param("q") String q,
                                                        @Param("storeId") Long storeId,
                                                        @Param("category") String category);

    @Query(value = """
        SELECT DISTINCT p.*
        FROM products p
        JOIN product_prices pp ON pp.product_id = p.id
        WHERE pp.supermarket_id = :storeId
        ORDER BY p.name
        """, nativeQuery = true)
    List<Product> findAllAvailableInStore(@Param("storeId") Long storeId);
}
