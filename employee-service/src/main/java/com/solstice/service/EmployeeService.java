package com.solstice.service;

import com.solstice.dao.EmployeeRepository;
import com.solstice.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee retrieveEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public List<Employee> retrieveEmployeesByIdList(List<Long> ids) {
        return employeeRepository.findEmployeeByIdIn(ids);
    }

    public Employee updateEmployee(Employee employee, Long id) {
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
