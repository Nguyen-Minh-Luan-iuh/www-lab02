package com.example.www_lab_w2.repositories;

import com.example.www_lab_w2.cfg.DBConnection;
import com.example.www_lab_w2.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class CustomerRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public CustomerRepository() {
        this.entityManager = DBConnection.getInstance().getEntityManager();
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public void addCustomer(Customer customer){
        try {
            entityTransaction.begin();
            entityManager.persist(customer);
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }

    }
    public List<Customer> getAll(){
        List<Customer> customers = entityManager.createNamedQuery("Customer.FIND_ALL").getResultList();
        return customers;
    }

    public Optional<Customer> findById(long id){
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    public void updateCustomer(Customer customer){
        try {
            entityTransaction.begin();
            entityManager.merge(customer);
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }
}
