package com.accesa.price_comparator.dto;

import com.accesa.price_comparator.domain.Discount;
import com.accesa.price_comparator.domain.Product;

public class ProductBestDiscount {
    private Product product;
    private Discount discount;

    public ProductBestDiscount(Product product, Discount discount) {
        this.product = product;
        this.discount = discount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
