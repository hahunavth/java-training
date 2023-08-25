package org.hahunavth.hibernate;

import org.hahunavth.hibernate.entities.Account;
import org.hahunavth.hibernate.entities.Review;
import org.hahunavth.hibernate.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TransactionTest {

    static SessionFactory sessionFactory;
    @org.junit.jupiter.api.BeforeAll
    public static void beforeAll() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @org.junit.jupiter.api.BeforeEach
    public void beforeEach() {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        session.beginTransaction();
        Integer id;
        Account account = new Account("account_1@gmail.com", "12345678");
        id = (Integer) session.save(account);

        for(int i = 1; i < 1000; i++) {
            Review review = new Review(id, "review_" + i, i);
            session.save(review);
        }

        Account account2 = new Account("account_2@gmail.com", "12345678");
        id = (Integer) session.save(account2);

        Account account3 = new Account("account_3@gmail.com", "12345678");
        id = (Integer) session.save(account3);

        session.getTransaction().commit();
    }

    public void setRatingN(int n) {
        sessionFactory.getCurrentSession().beginTransaction();
        Criteria cri = sessionFactory.getCurrentSession().createCriteria(Review.class);
        List<Review> reviews = cri.add(Restrictions.eq("account.id", 1)).list();
        reviews.forEach(System.out::println);
        // loop reviews with random order
        List<Integer> reviewIds = IntStream.rangeClosed(0, reviews.size()-1).boxed().collect(Collectors.toList());
        while(!reviewIds.isEmpty()) {
            int index = (int) (Math.random() * reviewIds.size());
            Review review = reviews.get(reviewIds.get(index));
            review.setRating(n);
            reviewIds.remove(index);
        }
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @org.junit.jupiter.api.Test
    public void testTransaction() {
        for (int i = 0; i < 10; i++) {
            setRatingN(i);
        }
    }
}
