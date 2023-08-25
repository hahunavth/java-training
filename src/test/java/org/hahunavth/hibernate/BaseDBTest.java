package org.hahunavth.hibernate;

import org.hahunavth.hibernate.entities.Account;
import org.hahunavth.hibernate.entities.Category;
import org.hahunavth.hibernate.entities.Review;
import org.hahunavth.hibernate.entities.Tour;
import org.hahunavth.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

/**
 * Setup database for testing
 */
public class BaseDBTest {
    private SessionFactory sessionFactory;

    @org.junit.jupiter.api.BeforeEach
    protected void setUp() throws Exception {
        sessionFactory = HibernateUtil.getSessionFactory();

        // create data ...
        Session session = sessionFactory.openSession();
        Integer accId;

        session.beginTransaction();
        Account acc = new Account("hahunavth@gmail.com", "123456");
        accId = (Integer) session.save(acc);
        session.save(new Review(accId, "content 1", 5));
        session.save(new Review(accId, "content 2", 4));
        accId = (Integer) session.save(new Account("vuthanhha@gmail.com", "123456"));
        accId = (Integer) session.save(new Account("aaaaaaaaa@gmail.com", "123456"));

        Category category = new Category("Category 1");
        session.save(category);
        Tour tour = new Tour("Tour 1", "Tour 1", new ArrayList<>(), null);

        session.getTransaction().commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
