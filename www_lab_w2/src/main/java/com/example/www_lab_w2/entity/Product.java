package com.example.www_lab_w2.entity;

import com.example.www_lab_w2.enums.ProductStatus;
import jakarta.persistence.*;
import org.joda.time.LocalDateTime;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "product")
@NamedQueries(value = {
        @NamedQuery(name = "Product.FIND_ALL", query = "SELECT p FROM Product p WHERE p.status = 1"),
        @NamedQuery(name = "Product.FIND_BY_ID", query = "SELECT p FROM Product p WHERE  p.status = 1 AND p.productId = :pId"),
        @NamedQuery(name = "Product.DELETE", query = "UPDATE Product p SET p.status = -1 WHERE p.productId = :id"),
        @NamedQuery(name = "Product.DECREASE_QUANTITY", query = "UPDATE Product p SET p.unit = p.unit - :quantity WHERE p.productId = :id")
})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String unit;
    @Column(name = "manufacturer_name", nullable = false)
    private String manufacturerName;
    @Column(nullable = false)
//    @Convert(converter = )
    private ProductStatus status;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductPrice> prices;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> details;

    public Product() {
    }

    public Product(String name, String description, String unit, String manufacturerName, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturerName = manufacturerName;
        this.status = status;
    }

    public Product(String name, String description, String unit, String manufacturerName, ProductStatus status, List<ProductImage> images) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturerName = manufacturerName;
        this.status = status;
        this.images = images;
    }

    public Product(String name, String description, String unit, String manufacturerName, ProductStatus status, List<ProductImage> images, List<ProductPrice> prices) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturerName = manufacturerName;
        this.status = status;
        this.images = images;
        this.prices = prices;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public double getCurrentPrice(){
        LocalDateTime now = LocalDateTime.now();

        Optional<ProductPrice> currentPrice = prices.stream().sorted(Comparator.comparing(ProductPrice::getPriceDateTime))
                .filter(item -> item.getPriceDateTime().isBefore(now))
                .reduce((first, second) -> second);
        if(currentPrice.isEmpty()){
            return -1;
        }
        return currentPrice.get().getPrice();
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public List<ProductPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ProductPrice> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unit=" + unit +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", status=" + status +
                '}';
    }
}
