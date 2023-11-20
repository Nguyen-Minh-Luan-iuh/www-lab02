package com.example.www_lab_w2.repositories;

import com.example.www_lab_w2.cfg.DBConnection;
import com.example.www_lab_w2.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public ProductRepository() {
        this.entityManager = DBConnection.getInstance().getEntityManager();
        this.entityTransaction = entityManager.getTransaction();
    }

    public void addProduct(Product product){
        try {
            entityTransaction.begin();
            entityManager.persist(product);
            entityTransaction.commit();
        }catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }

    public List<Product> getAll(){
        entityManager.clear();
        return entityManager.createNamedQuery("Product.FIND_ALL").getResultList();
    }

    public Optional<Product> findById(long id){
        entityManager.clear();
        Query query = entityManager.createNamedQuery("Product.FIND_BY_ID");
        query.setParameter("pId", id);
        List<Product> products = query.getResultList();
        if(products.size() == 0){
            return Optional.empty();
        }
        return Optional.of(products.get(0));
    }

    public void deleteProduct(long id){
        try {
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("Product.DELETE");
            query.setParameter("id", id);
            query.executeUpdate();
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }

    public void updateProduct(Product product){
        try {
            entityTransaction.begin();
            entityManager.merge(product);
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }

    public void decreaseQuantity(long pId, int quantity){
        try {
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("Product.DECREASE_QUANTITY");
            query.setParameter("id", pId);
            query.setParameter("quantity", quantity);
            query.executeUpdate();
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }
}
