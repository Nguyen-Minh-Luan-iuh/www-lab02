package com.example.www_lab_w2.servies;

import com.example.www_lab_w2.entity.Product;
import com.example.www_lab_w2.entity.ProductPrice;
import com.example.www_lab_w2.repositories.ProductPriceRepository;
import com.example.www_lab_w2.repositories.ProductRepository;

import java.util.Optional;

public class ProductPriceService {
    private ProductPriceRepository productPriceRepository;
    private ProductRepository productRepository;

    public ProductPriceService() {
        this.productPriceRepository = new ProductPriceRepository();
        this.productRepository = new ProductRepository();
    }

    public boolean addProductPrice(long pId, ProductPrice productPrice){
        Optional<Product> rs = productRepository.findById(pId);
        if(rs.isEmpty()){
            return false;
        }
        productPrice.setProduct(rs.get());
        System.out.println(productPrice.getPriceDateTime().toDate());
        productPriceRepository.addProductPrice(productPrice);

        return true;
    }
}
