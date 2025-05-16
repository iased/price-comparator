package com.accesa.price_comparator.service;

import com.accesa.price_comparator.domain.Product;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    /*
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
    } */

    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();

        try{
            URL csvFolderURL = getClass().getClassLoader().getResource("csv");
            if (csvFolderURL == null){
                throw new RuntimeException("CSV folder not found");
            }

            File csvFolder = new File(csvFolderURL.toURI());
            File[] files = csvFolder.listFiles((dir, name) ->
                    name.endsWith(".csv") && !name.contains("discount")
            );

            if(files == null){
                throw new RuntimeException("No CSV files found");
            }

            for(File file: files) {
                try(InputStream inputStream = new FileInputStream(file);
                    InputStreamReader reader = new InputStreamReader(inputStream)) {

                    // extract store name from file name
                    String fileName = file.getName();
                    String storeName = fileName.split("_")[0];

                    //extract date from file name
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String date = fileName.split("_")[1].replace(".csv", "");
                    LocalDate productDate = LocalDate.parse(date, formatter);

                    List<Product> products = new CsvToBeanBuilder<Product>(reader)
                            .withType(Product.class)
                            .withSeparator(';')
                            .build()
                            .parse();

                    for(Product product: products){
                        product.setStore(storeName);
                        product.setDate(productDate);
                    }

                    allProducts.addAll(products);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allProducts;
    }
}
