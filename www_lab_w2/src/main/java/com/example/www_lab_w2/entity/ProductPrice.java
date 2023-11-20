package com.example.www_lab_w2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "product_price")
public class ProductPrice {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @Column(name = "price_date_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime priceDateTime;

    @Column(nullable = false)
    private double price;
    private String note;

    public ProductPrice() {
    }

    public ProductPrice(Product product, LocalDateTime priceDateTime, double price, String note) {
        this.product = product;
        this.priceDateTime = priceDateTime;
        this.price = price;
        this.note = note;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getPriceDateTime() {
        return priceDateTime;
    }

    public void setPriceDateTime(LocalDateTime priceDateTime) {
        this.priceDateTime = priceDateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "product=" + product +
                ", priceDateTime=" + priceDateTime.toDate() +
                ", price=" + price +
                ", note='" + note + '\'' +
                '}';
    }
}
