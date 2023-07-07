package com.example.exam.service;

import com.example.exam.dao.EmployeeDAO;
import com.example.exam.entity.Employee;
import com.example.exam.exception.IncompleteOperationException;
import com.example.exam.exception.IncorrectPositionException;
import com.example.exam.requestbody.PositionEditBody;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> getAllEmployee(){
        return employeeDAO.findAll();
    }

    public Employee getEmployeeById(Integer id){
        Employee employee = employeeDAO.findById(id);
        if (employee==null) {
            throw new RuntimeException("Not found employee ID: " + id.toString());
        }
        return employee;
    }

    @Transactional
    public Employee insertEmployee(Employee body){
        body.setStatus("current");
        employeeDAO.save(body);
        return body;
    }

    @Transactional
    public void removeEmployee(Integer id){
        Employee employee = employeeDAO.findById(id);
        employee.setStatus("deleted");
        employeeDAO.save(employee);
    }

    @Transactional
    public Employee editEmployee(Integer id, Employee body){
        Employee employee = employeeDAO.findById(id);
        if (body.getFirstName()!=null) employee.setFirstName(body.getFirstName());
        if (body.getLastName()!=null) employee.setLastName(body.getLastName());
        if (body.getNickName()!=null) employee.setNickName(body.getNickName());
        if (body.getAddress()!=null) employee.setAddress(body.getAddress());
        employeeDAO.save(employee);
        return employee;
    }

    @Transactional
    public Employee addSalary(Integer id, Integer percent){
        Employee employee = employeeDAO.findById(id);
        employee.setSalary(employee.getSalary() * (100+percent)/100);
        employeeDAO.save(employee);
        return employee;
    }

    @Transactional
    public  Employee editPosition(Integer id, PositionEditBody body){
        Employee employee = employeeDAO.findById(id);
        if (!employee.getPosition().equals(body.getCurrentPosition())){
            throw new IncorrectPositionException();
        }
        employee.setPosition(body.getNewPosition());
        employeeDAO.save(employee);
        return employee;
    }

    @Transactional
    public List<Employee> getEmployeeByName(String name){
        return employeeDAO.findByName(name);
    }

    @Transactional
    public void removeByIds(List<Integer> ids){
        List<Integer> noContentList = new ArrayList<>();
        for (Integer id : ids){
            Employee employee = employeeDAO.findById(id);
            if (employee==null) {
                noContentList.add(id);
                continue;
            }
            employeeDAO.delete(employee);
        }
        if (noContentList.size() > 0){ // noContentList
            throw new IncompleteOperationException("Incomplete", noContentList);
        }
    }
}
