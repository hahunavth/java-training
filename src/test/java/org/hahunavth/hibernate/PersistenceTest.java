package org.hahunavth.hibernate;

import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.hahunavth.hibernate.entities.Account;
import org.hahunavth.hibernate.entities.Review;
import org.hahunavth.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * @see <a
 * href="https://www.baeldung.com/hibernate-detached-entity-passed-to-persist"
 * >
 *      hibernate-detached-entity-passed-to-persist
 * </a>
 */
public class PersistenceTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Account account;

    @org.junit.jupiter.api.BeforeEach
    protected void setUp() throws Exception {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @BeforeEach
    public void beforeEach() {
        session = sessionFactory.openSession();
        account = new Account("abc@123.com", "123456");
        session.beginTransaction();
    }

    @org.junit.jupiter.api.Test
    public void givenDetachedPost_whenTryingToPersist_thenThrowException() {
        session.save(account);           // Persistent
        session.evict(account);             // Detached
        account.setEmail("123@abc.com");    // Modified

        Assertions.assertThatThrownBy(() -> session.persist(account))
            .isInstanceOf(PersistenceException.class)
            .hasMessageContaining("detached entity passed to persist: org.hahunavth.hibernate.entities.Account");
        session.close();
    }

    @org.junit.jupiter.api.Test
    public void givenDetachedPost_whenTryingToMerge_thenNoExceptionIsThrown() {
        String newEmail = "a@b.c";
        account.setEmail(newEmail);

        session.merge(account);     // Detached -> Persistent

        session.getTransaction().commit();

        List<Account> posts = session.createQuery("Select p from Account p", Account.class).list();
        Assertions.assertThat(posts).hasSize(1);
        Assertions.assertThat(posts.get(0).getEmail())
            .isEqualTo(newEmail);
    }

    @org.junit.jupiter.api.Test
    public void givenDetachedPost_whenMergeAndPersistComment_thenNoExceptionIsThrown() {
        Review review = new Review("nice article!", 5);

        session.save(account);                                  // Persistent
        session.evict(account);                                 // Detached
        Account account1 = (Account) session.merge(account);    // Detached -> Persistent
        review.setAccount(account1);

        session.persist(review);
        session.getTransaction().commit();

        List<Review> reviews = session.createQuery("Select r from Review r", Review.class).list();
        Review savedReview = reviews.get(0);
        Assertions.assertThat(savedReview.getContent()).isEqualTo("nice article!");
        Assertions.assertThat(savedReview.getAccount().getEmail())
            .isEqualTo("abc@123.com");
    }
}
