package com.example.exam.controller;

import com.example.exam.entity.Employee;
import com.example.exam.requestbody.PercentBody;
import com.example.exam.requestbody.PositionEditBody;
import com.example.exam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/")
    public List<Employee> handleGetAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("{id}")
    public Employee handleGetEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/")
    public Employee handleInsertEmployee(@RequestBody Employee body){
        return employeeService.insertEmployee(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> handleRemoveEmployee(@PathVariable Integer id){
        employeeService.removeEmployee(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public Employee handleEditEmployee(@PathVariable Integer id, @RequestBody Employee body){
        return employeeService.editEmployee(id, body);
    }

    @PutMapping("/salary/{id}")
    public Employee addSalary(@PathVariable Integer id, @RequestBody PercentBody body){
        return employeeService.addSalary(id, body.getPercent());
    }

    @PutMapping("/position/{id}")
    public Employee editPosition(@PathVariable Integer id, @RequestBody PositionEditBody body){
        return employeeService.editPosition(id, body);
    }

    @GetMapping("/name")
    public List<Employee> getEmployeeByName(@RequestParam("q") String query){
        return employeeService.getEmployeeByName(query);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> removeEmployeeByList(@RequestParam("ids") List<Integer> ids){
        employeeService.removeByIds(ids);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

}