package org.hahunavth.hibernate;

import org.hahunavth.hibernate.entities.Account;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CriteriaQueryTest extends BaseDBTest {

    Session session;

    @org.junit.jupiter.api.BeforeEach
    public void beforeEach() {
        session = this.getSessionFactory().openSession();
    }

    @org.junit.jupiter.api.Test
    public void testCriteriaQuery() {
        Criteria crit = session.createCriteria(Account.class);
        crit.setMaxResults(50);
        List cats = crit.list();
        cats.forEach(System.out::println);
    }

    @org.junit.jupiter.api.Test
    public void testCriteriaQuery2() {
        Criteria crit = session.createCriteria(Account.class)                             // from
                .add(Restrictions.like("email", "%@gmail.com%"))        // where
                .addOrder(Order.desc("email"))                                // order by
                .setMaxResults(50)                                                        // limit
                .setFirstResult(0)                                                        // offset
                .createCriteria("reviews")                                   // join
                ;
        List cats = crit.list();
        cats.forEach(System.out::println);
    }

    @org.junit.jupiter.api.Test
    public void testAggregate() {
        Criteria crit = session.createCriteria(Account.class)
                .setProjection(org.hibernate.criterion.Projections.rowCount());
        List cats = crit.list();
        cats.forEach(System.out::println);
    }
}
