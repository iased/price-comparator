package com.uo.price_comparator.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="discounts",
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "product_id", "supermarket_id", "from_date", "to_date"
        })
)
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supermarket_id", nullable = false)
    private Supermarket supermarket;

    @Column(name= "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @Column(name = "percentage_of_discount", nullable = false)
    private Integer percentageOfDiscount;

    public Discount() {

    }

    public Discount(Long id, Product product, Supermarket supermarket, LocalDate fromDate, LocalDate toDate, Integer percentageOfDiscount) {
        this.id = id;
        this.product = product;
        this.supermarket = supermarket;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.percentageOfDiscount = percentageOfDiscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supermarket getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Integer getPercentageOfDiscount() {
        return percentageOfDiscount;
    }

    public void setPercentageOfDiscount(Integer percentageOfDiscount) {
        this.percentageOfDiscount = percentageOfDiscount;
    }
}
