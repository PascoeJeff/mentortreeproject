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

    @GetMapping(value = "/employees/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeesById(@PathVariable("id") Long id) {
        return employeeRepository.findById(id).get();
    }

    @GetMapping(value = "/employees/list" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByIdListTest(@RequestParam ("ids") List<Long> ids) {
        return employeeRepository.findEmployeeByIdIn(ids);
    }

    @PutMapping("/employees/{id}")
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
