package org.hahunavth.hibernate;

import org.hahunavth.hibernate.entities.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Date;

class RepositoryTest {
    private EntityManagerFactory sessionFactory;

    @org.junit.jupiter.api.BeforeEach
    protected void setUp() throws Exception {
        sessionFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
    }

    @org.junit.jupiter.api.Test
    void getId() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist( new Event( "Our very first event!", new Date() ) );
        entityManager.persist( new Event( "A follow up event", new Date() ) );
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}