package com.solstice.controller;

import com.solstice.dao.EmployeeRepository;
import com.solstice.entity.Employee;
import com.solstice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/employees/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeesById(@PathVariable("id") Long id) {
        return employeeService.findById(id);
    }

    @GetMapping(value = "/employees/list/{ids}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByIdList(@PathVariable("ids") List<Long> ids) {
        return employeeService.getEmployeesByIdList(ids);
    }

    @PostMapping(value = "/employees/" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesListByIdList(@RequestBody List<Long> ids) {
        return employeeService.getEmployeesByIdList(ids);
    }

    @PutMapping(value = "/employees/{id}" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return employeeService.updateEmployee(employee,id);
    }



}
