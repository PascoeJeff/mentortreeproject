package com.solstice;

import com.solstice.dao.EmployeeRepository;
import com.solstice.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public boolean updateEmployee(Employee employee) {
        employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    return null;
                });
    }


}
