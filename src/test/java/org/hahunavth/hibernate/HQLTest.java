package org.hahunavth.hibernate;

import org.hahunavth.hibernate.entities.Account;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;


public class HQLTest extends BaseDBTest {

    Session session;

    @org.junit.jupiter.api.BeforeEach
    public void beforeEach() {
        session = this.getSessionFactory().openSession();
    }

    @org.junit.jupiter.api.Test
    void testFromClause() {
        Query query = session.createQuery("from Account"); // 1. Create query object
        List result = query.getResultList();                         // 2. Execute query
        result.forEach(System.out::println);                         // 3. Process result

        Assertions.assertTrue(result.size() > 0);
    }

    @org.junit.jupiter.api.Test
    void testJoinWhere() {
        // explicit join
        Query query = session.createQuery("from Account a inner join a.reviews r");
        List result = query.getResultList();
        result.forEach(System.out::println);
        // implicit join
        query = session.createQuery("from Account a where a.reviews is not empty");
        result = query.getResultList();
        result.forEach(System.out::println);
    }

    @org.junit.jupiter.api.Test
    void testSelect() {
        // select all, default Object[]
        Query query = session.createQuery("select a.email, a.fullName from Account a");
        List<Object[]> result = query.getResultList();
        result.forEach(r -> System.out.println(r[0] + " " + r[1]));
        // Map<>
        query = session.createQuery("select new map(a.email as email, a.fullName as fullName) from Account a");
        List<Map<String, Object>> result2 = query.getResultList();
        result2.forEach(r -> System.out.println(r.get("email") + " " + r.get("fullName")));
        // pagination
        query = session.createQuery("select a.email, a.fullName from Account a");
        query.setFirstResult(0);
        query.setMaxResults(2);
        result = query.getResultList();
        result.forEach(r -> System.out.println(r[0] + " " + r[1]));
        // new instance
        session.createQuery("select new org.hahunavth.hibernate.entities.Account(a.email, a.fullName) from Account a")
                .getResultList()
                .forEach(System.out::println);
    }

    @org.junit.jupiter.api.Test
    void testAggregate() {
        Query query = session.createQuery("select count(a) from Account a");
        Long count = (Long) query.getSingleResult();
        System.out.println(count);
    }

    @org.junit.jupiter.api.Test
    void tectOrderGroup() {
        ((List<Object[]>) session.createQuery("select r.account.email, sum(r.rating) from Review r group by r.account")
                .getResultList())
                .forEach(item -> System.out.println(item[0] + " " + item[1]));
    }
}
