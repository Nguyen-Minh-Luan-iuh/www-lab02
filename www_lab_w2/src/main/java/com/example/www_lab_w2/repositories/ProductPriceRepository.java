package com.example.www_lab_w2.repositories;

import com.example.www_lab_w2.cfg.DBConnection;
import com.example.www_lab_w2.entity.ProductPrice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProductPriceRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public ProductPriceRepository() {
        this.entityManager = DBConnection.getInstance().getEntityManager();
        this.entityTransaction = entityManager.getTransaction();
    }

    public void addProductPrice(ProductPrice productPrice){
        try {
            entityTransaction.begin();
            entityManager.persist(productPrice);
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }
}
