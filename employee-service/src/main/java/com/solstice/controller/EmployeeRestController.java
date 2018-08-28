package com.solstice.controller;

import com.solstice.dao.EmployeeRepository;
import com.solstice.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value ="/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).get();
    }

    @GetMapping(value = "/employees/list/{ids}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByIdList(@PathVariable("ids") List<Long> ids) {
        return employeeRepository.findEmployeesByIdList(ids);
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        
        return ;
    }

}
