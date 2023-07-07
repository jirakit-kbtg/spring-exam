package com.example.exam.dao;

import com.example.exam.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll(){
        TypedQuery typedQuery = entityManager.createQuery("FROM Employee", Employee.class);
        return typedQuery.getResultList();
    }

    @Override
    public void save(Employee employee){
        entityManager.persist(employee);
    }

    @Override
    public Employee findById(Integer id){
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void delete(Employee employee){
        entityManager.remove(employee);
    }

    @Override
    public void update(Employee employee){
        entityManager.merge(employee);
    }

    @Override
    public List<Employee> findByName(String name){
        TypedQuery<Employee> typedQuery = entityManager.createQuery("FROM Employee WHERE firstName LIKE :name", Employee.class);
        typedQuery.setParameter("name", "%"+name+"%");
        return typedQuery.getResultList();
    }

}
