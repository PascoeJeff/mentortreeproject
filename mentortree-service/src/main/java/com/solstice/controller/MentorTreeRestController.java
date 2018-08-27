package com.solstice.controller;

import com.solstice.entity.Employee;
import com.solstice.service.MentorTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MentorTreeRestController {

    @Autowired
    MentorTreeService mentorTreeService;

    @GetMapping(value = "/mentortree/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable Long id) {
        return mentorTreeService.getEmployeeFromEmployeeService("http://localhost:8080/employees/{id}/",id);
    }

    @GetMapping(value = "/mentortree/mentor/{id}/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByMentorId(@PathVariable Long id) {
        List<Long> employeeIdList = mentorTreeService.getEmployeeIdsFromMentorId(id);
        List<Employee> menteeList = mentorTreeService.getEmployeeFromEmployeeService("http://localhost:8080/employees/list/", employeeIdList);

        menteeList.forEach(Employee::getFirstName);
        return menteeList;
    }

}





