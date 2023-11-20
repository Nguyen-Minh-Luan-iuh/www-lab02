package com.example.www_lab_w2.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product_image")
@NamedQueries({
        @NamedQuery(name = "ProductImage.DELETE", query = "DELETE ProductImage pi WHERE pi.id = :id AND pi.product.productId = :pId")
})
public class ProductImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long imageId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(nullable = false)
    private String path;
    @Column(nullable = false)
    private String alternative;

    public ProductImage() {
    }

    public ProductImage(Product product, String path, String alternative) {
        this.product = product;
        this.path = path;
        this.alternative = alternative;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "imageId=" + imageId +
                ", product=" + product +
                ", path='" + path + '\'' +
                ", alternative='" + alternative + '\'' +
                '}';
    }
}
