package org.hahunavth.springmvc2.repository;

import org.hahunavth.springmvc2.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    List<Employee> listEmployees();
    Employee getEmployeeById(long id);
    void removeEmployee(long id);
}
