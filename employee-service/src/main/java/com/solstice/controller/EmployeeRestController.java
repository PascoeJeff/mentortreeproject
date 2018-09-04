package com.solstice.controller;

import com.solstice.entity.Employee;
import com.solstice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/employees/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeesById(@PathVariable("id") Long id) {
        return employeeService.retrieveEmployeeById(id);
    }

    @GetMapping(value = "/employees/list" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByIdList(@RequestParam ("ids") List<Long> ids) {
        return employeeService.retrieveEmployeesByIdList(ids);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return employeeService.updateEmployee(employee, id);
    }
}
