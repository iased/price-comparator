package com.accesa.price_comparator.service;

import com.accesa.price_comparator.domain.Product;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    public List<Product> getAllProducts() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("csv/lidl_2025-05-08.csv");
            if (inputStream == null)
                throw new RuntimeException("CSV file not found");
            InputStreamReader reader = new InputStreamReader(inputStream);

            return new CsvToBeanBuilder<Product>(reader)
                .withType(Product.class)
                .withSeparator(';')
                .build()
                .parse();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
}
