package com.example.www_lab_w2.repositories;

import com.example.www_lab_w2.cfg.DBConnection;
import com.example.www_lab_w2.entity.ProductImage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class ProductImageRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public ProductImageRepository() {
        this.entityManager = DBConnection.getInstance().getEntityManager();
        this.entityTransaction = entityManager.getTransaction();
    }

    public void addProductImage(ProductImage productImage){
        try {
            entityTransaction.begin();
            entityManager.persist(productImage);
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }

    public void deleteProductImage(long id, long productId){
        try {
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("ProductImage.DELETE");
            query.setParameter("id", id);
            query.setParameter("pId", productId);
            query.executeUpdate();
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }
}