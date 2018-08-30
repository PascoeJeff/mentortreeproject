package com.solstice.service;

import com.solstice.dao.EmployeeRepository;
import com.solstice.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee findById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public List<Employee> getEmployeesByIdList(List<Long> ids) {
        return employeeRepository.findEmployeeByIdIn(ids);
    }

    public Employee updateEmployee(Employee employee, Long id) {
        //todo validate method for null
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
