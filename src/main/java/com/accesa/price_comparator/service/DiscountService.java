package com.accesa.price_comparator.service;

import com.accesa.price_comparator.domain.Discount;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService {
    public List<Discount> getAllDiscounts() {
        List<Discount> allDiscounts = new ArrayList<>();

        try{
            URL csvFolderURL = getClass().getClassLoader().getResource("csv");
            if (csvFolderURL == null){
                throw new RuntimeException("CSV folder not found");
            }

            File csvFolder = new File(csvFolderURL.toURI());
            File[] files = csvFolder.listFiles((dir, name) ->
                    name.endsWith(".csv") && name.contains("discount")
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

                    List<Discount> discounts = new CsvToBeanBuilder<Discount>(reader)
                            .withType(Discount.class)
                            .withSeparator(';')
                            .build()
                            .parse();

                    for(Discount discount: discounts){
                        discount.setStore(storeName);
                    }

                    allDiscounts.addAll(discounts);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allDiscounts;
    }
}
