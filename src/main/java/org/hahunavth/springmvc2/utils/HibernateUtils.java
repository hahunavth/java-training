package org.hahunavth.springmvc2.utils;

import org.hahunavth.springmvc2.entity.Employee;
import org.hahunavth.springmvc2.entity.Skill;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setPhysicalNamingStrategy(new SqlPhysicalNamingStrategy());
            sessionFactory = configuration
                    .configure()
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Skill.class)
                    .buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
