package com.accesa.price_comparator.repository;

import com.accesa.price_comparator.model.PriceAlert;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PriceAlertRepository {
    private final Map<UUID, PriceAlert> alertMap = new HashMap<>();

    public void saveAlert(PriceAlert alert) {
        alertMap.put(alert.getId(), alert);
    }

    public List<PriceAlert> findAll(){
        return new ArrayList<>(alertMap.values());
    }

    public Optional<PriceAlert> findById(UUID id) {
        return Optional.ofNullable(alertMap.get(id));
    }

    public void deleteById(UUID id) {
        alertMap.remove(id);
    }
}
