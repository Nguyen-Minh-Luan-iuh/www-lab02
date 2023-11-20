package com.example.www_lab_w2.repositories;

import com.example.www_lab_w2.cfg.DBConnection;
import com.example.www_lab_w2.entity.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class OrderDetailRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public OrderDetailRepository() {
        this.entityManager = DBConnection.getInstance().getEntityManager();
        this.entityTransaction = entityManager.getTransaction();
    }

    public void addOrderDetail(OrderDetail orderDetail){
        try {
            entityTransaction.begin();
            entityManager.persist(orderDetail);
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }
}
