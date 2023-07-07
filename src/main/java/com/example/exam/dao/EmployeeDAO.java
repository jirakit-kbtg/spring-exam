package com.example.exam.dao;

import com.example.exam.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    void save(Employee employee);
    List<Employee> findAll();

    List<Employee> findByName(String name);

    Employee findById(Integer id);

    void delete(Employee employee);

    void update(Employee employee);


}
