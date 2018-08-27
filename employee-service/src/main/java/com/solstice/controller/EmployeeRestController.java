package com.solstice.controller;

import com.solstice.dao.EmployeeRepository;
import com.solstice.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value ="/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).get();
    }

    @GetMapping(value = "/employees/list/" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByIdList(@Param("ids") String[] ids) {
        List<Long> idList = Arrays.stream(ids)
                .map(Long::valueOf)
                .collect(toList());

        return employeeRepository.findEmployeesByIdList(idList);
    }
}
