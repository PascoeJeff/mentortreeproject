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

    @GetMapping(value = "employee-service/employees/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeesById(@PathVariable("id") Long id) {
        return employeeRepository.findById(id).get();
    }

    @GetMapping(value = "employee-service/employees/list/{ids}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByIdList(@PathVariable("ids") List<String> ids) {
        return employeeRepository.findEmployeesByIdList(ids);
    }

    @PutMapping("employee-service/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        if(employee.toString().contains("null")) return null; // To do return some type of error response and error message
        Employee updatedEmployee = employeeRepository.findById(id).get();
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setEmployeeNumber(employee.getEmployeeNumber());
        updatedEmployee.setOffice(employee.getOffice());
        updatedEmployee.setTitle(employee.getTitle());
        updatedEmployee.setImageUrl(employee.getImageUrl());
        return employeeRepository.save(updatedEmployee);
    }



}
