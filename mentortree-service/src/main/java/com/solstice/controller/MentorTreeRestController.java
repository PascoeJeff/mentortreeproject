package com.solstice.controller;

import com.solstice.entity.Employee;
import com.solstice.service.MentorTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class MentorTreeRestController {

    @Autowired
    MentorTreeService mentorTreeService;

    @GetMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getEmployeeById(@PathVariable Long id) {
        return mentorTreeService.getEmployeeFromEmployeeService("http://localhost:8080/employees/{id}/",id);
    }

    @GetMapping(value = "/mentors/{id}/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Employee> getEmployeesByMentorId(@PathVariable Long id) {
        List<Long> employeeIdList = mentorTreeService.getEmployeeIdsFromMentorId(id);
        List<Object> menteeList = mentorTreeService.getEmployeesFromEmployeeService("http://localhost:8080/employees/list/{ids}", employeeIdList);
        List<Employee> employeeList = new ArrayList<>();

        for(Object o : menteeList) {
            employeeList.add(new Employee((LinkedHashMap<String, Object>)o));
        }

        Resources<Employee> resources = addLinkToEmployee(employeeList);

        return resources;
    }

    @GetMapping(value= "/treeleads/{id}/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Employee> getEmployeesByTreeLeadId(@PathVariable Long id) {
        List<Long> employeeIdList = mentorTreeService.getEmployeeIdsFromTreeLeadId(id);
        List<Object> menteeList = mentorTreeService.getEmployeesFromEmployeeService("http://localhost:8080/employees/list/{ids}", employeeIdList);
        List<Employee> employeeList = new ArrayList<>();

        for(Object o : menteeList) {
            employeeList.add(new Employee((LinkedHashMap<String, Object>)o));
        }

        Resources<Employee> resources = addLinkToEmployee(employeeList);

        return resources;
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee) {


        return null;
    }

    private Resources<Employee> addLinkToEmployee(List<Employee> employeeList) {
        Resources<Employee> resources = new Resources<Employee>(employeeList);
        for (final Employee resource : resources) {
            Link selfLink = linkTo(methodOn(MentorTreeRestController.class)
                    .getEmployeeById(resource.getId())).withSelfRel();
            resource.setLink(selfLink);
        }
        return resources;
    }

}







