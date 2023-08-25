package org.hahunavth.hibernate.utils;

import org.hahunavth.hibernate.entities.*;
import org.hahunavth.hibernate.strategies.SQLPhysicalNamingStrategy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setPhysicalNamingStrategy(new SQLPhysicalNamingStrategy());
            sessionFactory = configuration
                    .configure()
                    .addAnnotatedClass(Event.class)
                    .addAnnotatedClass(Account.class)
                    .addAnnotatedClass(Review.class)
                    .addAnnotatedClass(Tour.class)
                    .addAnnotatedClass(Category.class)
                    .buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    /**
     * Try to create a session and save an account
     */
    public static void main(String [] args) {
        SessionFactory sessionFactory = getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            System.out.println("Session created");

            Account acc = new Account("uname1", "password");
            Integer accId = (Integer) session.save(acc);

            for (Account acc2 : (Iterable<Account>) session.createQuery("FROM Account").list()) {
                System.out.println("Email " + acc2.getEmail());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
