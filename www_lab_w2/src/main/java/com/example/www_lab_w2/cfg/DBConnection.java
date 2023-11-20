package com.example.www_lab_w2.cfg;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DBConnection {
    private static DBConnection instance = null;
    private EntityManager entityManager;

    private DBConnection() {
        this.entityManager = Persistence.createEntityManagerFactory("www_lab02_tuan02").createEntityManager();
    }

    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
