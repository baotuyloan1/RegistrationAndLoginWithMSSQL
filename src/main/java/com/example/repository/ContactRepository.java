package com.example.repository;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository
public class ContactRepository {

//    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save (User user){
        entityManager.persist(user);
    }

    @Transactional
    public User update (User user){
        return entityManager.merge(user);
    }
}
