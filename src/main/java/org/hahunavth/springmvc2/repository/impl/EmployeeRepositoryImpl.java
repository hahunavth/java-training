package org.hahunavth.springmvc2.repository.impl;

import org.hahunavth.springmvc2.entity.Employee;
import org.hahunavth.springmvc2.repository.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEmployee(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(employee);
        logger.info("Employee saved successfully, Employee Details="+employee);
    }
    @Override
    public void updateEmployee(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(employee);
        logger.info("Employee updated successfully, Employee Details="+employee);
    }
    @Override
    public List<Employee> listEmployees() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Employee> employeeList = session.createQuery("from Employee").list();
        for(Employee employee : employeeList){
            logger.info("Employee List::"+employee);
        }
        return employeeList;
    }
    @Override
    public Employee getEmployeeById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Employee employee = (Employee) session.load(Employee.class, id);
        logger.info("Employee loaded successfully, Employee details="+employee);
        return employee;
    }
    @Override
    public void removeEmployee(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Employee employee = (Employee) session.load(Employee.class, id);
        if(null != employee){
            session.delete(employee);
        }
        logger.info("Employee deleted successfully, employee details="+employee);
    }
}
