package org.hahunavth.hibernate.example;

import org.hahunavth.hibernate.entities.Event;
import org.hahunavth.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class ManageEvent {
    public static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = HibernateUtil.getSessionFactory();

        ManageEvent manageEvent = new ManageEvent();

        manageEvent.addEvent("Our very first event!");
        manageEvent.listEvents();

    }

    public Integer addEvent(String title) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer eventId = null;
        try {
            tx = session.beginTransaction();
            Event event = new Event(title, new Date());
            eventId = (Integer) session.save(event);
            tx.commit();
        } catch(HibernateException e) {
            if (tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
        return eventId;
    }

    public void listEvents() {
        try (Session session = sessionFactory.openSession()) {
            for (Event event : (Iterable<Event>) session.createQuery("FROM Event").list()) {
                System.out.println("Title: " + event.getTitle());
                System.out.println("Date: " + event.getDate());
            }
        }
    }

    public
}
