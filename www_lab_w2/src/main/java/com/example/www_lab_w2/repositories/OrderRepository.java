package com.example.www_lab_w2.repositories;

import com.example.www_lab_w2.cfg.DBConnection;
import com.example.www_lab_w2.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public OrderRepository() {
        this.entityManager = DBConnection.getInstance().getEntityManager();
        this.entityTransaction = entityManager.getTransaction();
    }

    public void addOrder(Order order){
        try {
            entityTransaction.begin();
            entityManager.persist(order);
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }

    public Optional<Order> findById(long orderId){
        return Optional.ofNullable(entityManager.find(Order.class, orderId));
    }

    public List<Order> getAll(){
        return entityManager.createNamedQuery("Order.GET_ALL").getResultList();
    }
    public List<Order> statisticByDay(String date){
        try {
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("Order.STATISTIC_BY_DAY");
            query.setParameter("dateTime", date);
            List<Order> orders = query.getResultList();
            entityTransaction.commit();

            return orders;
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }

        return null;
    }

    public List<Order> statisticFromRange(String fromDate, String toDate){
        try {
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("Order.STATISTIC_FROM_RANGE");
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            List<Order> orders = query.getResultList();
            entityTransaction.commit();
            return orders;
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
        return null;
    }

    public List<Order> statisicFromRangeByEmployee(String fromDate, String toDate, long empId){
        try {
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("Order.STATISTIC_FROM_RANGE_BY_EMPLOYEE");
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            query.setParameter("empId", empId);
            List<Order> orders = query.getResultList();
            entityTransaction.commit();
            return orders;
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
        return null;
    }

    public List<Order> statisticByEmployee(long empId){
        try {
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("Order.STATISTIC_BY_EMPLOYEE");
            query.setParameter("empId", empId);
            List<Order> orders = query.getResultList();
            entityTransaction.commit();
            return orders;
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }

        return null;
    }

    public List<Order> statisticByDayByEmployee(String date, long empId){
        try {
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("Order.STATISTIC_BY_DAY_BY_EMPLOYEE");
            query.setParameter("date", date);
            query.setParameter("empId", empId);
            List<Order> orders = query.getResultList();
            entityTransaction.commit();
            return orders;
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }

        return null;
    }
}
