package org.hahunavth.hibernate;

import org.hahunavth.hibernate.entities.Account;
import org.hahunavth.hibernate.entities.Review;
import org.hahunavth.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateTest {
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


        session.getTransaction().commit();
    }

    @org.junit.jupiter.api.Test
    void example() {
        Session session = sessionFactory.openSession();

        Account acc = session.get(Account.class, 1);
        System.out.println(acc);
    }
}
