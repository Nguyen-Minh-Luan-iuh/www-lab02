package com.example.www_lab_w2.servies;

import com.example.www_lab_w2.entity.Product;
import com.example.www_lab_w2.entity.ProductImage;
import com.example.www_lab_w2.repositories.ProductImageRepository;

public class ProductImageService {
    private ProductImageRepository productImageRepository;

    public ProductImageService() {
        this.productImageRepository = new ProductImageRepository();
    }

    public void addProductImage(ProductImage productImage, long productId){
        Product product = new Product();
        product.setProductId(productId);
        productImage.setProduct(product);
        productImageRepository.addProductImage(productImage);
    }

    public void deleteProductImage(long id, long productId){
        productImageRepository.deleteProductImage(id, productId);
    }
}
