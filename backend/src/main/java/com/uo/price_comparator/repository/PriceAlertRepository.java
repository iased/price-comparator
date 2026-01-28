package com.uo.price_comparator.repository;

import com.uo.price_comparator.model.PriceAlert;
import com.uo.price_comparator.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PriceAlertRepository extends JpaRepository<PriceAlert, Long> {
    List<PriceAlert> findByUserIdOrderByIdDesc(Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
    boolean existsByUserIdAndProductId(Long userId, Long productId);

    @Transactional
    void deleteByUserId(Long userId);
}

