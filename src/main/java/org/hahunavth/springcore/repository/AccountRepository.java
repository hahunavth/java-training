package org.hahunavth.springcore.repository;

import org.hahunavth.hibernate.entities.Account;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AccountRepository() {
        System.out.println("AccountRepository no-args constructor called");
    }

    public Account getAccountFromEmail(String email) {
        System.out.println("AccountRepository.getAccountFromEmail() called");
        return (Account) sessionFactory.getCurrentSession()
                .createQuery("from Account where email = :email")
                .setParameter("email", email).uniqueResult();
    }

}
