package com.uo.price_comparator.service;

import com.uo.price_comparator.dto.ProductSearchDto;
import com.uo.price_comparator.model.Product;
import com.uo.price_comparator.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<ProductSearchDto> searchProducts(String q) {
        String query = (q == null) ? "" : q.trim();
        if (query.length() < 2) return List.of();

        return productRepository
                .searchDiacriticsInsensitive(query)
                .stream()
                .map(p -> new ProductSearchDto(
                        p.getId(),
                        p.getName(),
                        p.getBrand(),
                        p.getQuantity(),
                        p.getUnit(),
                        p.getImageUrl()
                ))
                .toList();
    }
}
