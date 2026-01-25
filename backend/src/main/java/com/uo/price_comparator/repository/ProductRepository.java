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
        WHERE unaccent(lower(p.name))  LIKE unaccent(lower(concat('%', :q, '%')))
           OR unaccent(lower(p.brand)) LIKE unaccent(lower(concat('%', :q, '%')))
        ORDER BY p.name
        LIMIT 10
        """, nativeQuery = true)
    List<Product> searchDiacriticsInsensitive(@Param("q") String q);
}
