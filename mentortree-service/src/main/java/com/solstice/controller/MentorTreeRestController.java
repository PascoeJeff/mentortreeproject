package com.solstice.controller;

import com.solstice.domain.Employee;
import com.solstice.domain.EmployeeInfo;
import com.solstice.service.MentorTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MentorTreeRestController {

    @Autowired
    MentorTreeService mentorTreeService;

    @GetMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeInfo getEmployeeById(@PathVariable Long id) {
        return mentorTreeService.getEmployeeFromEmployeeService(mentorTreeService.serviceUrl()+"employees/{id}/", id);
    }

    @GetMapping(value = "/mentors/{id}/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Employee> getEmployeesByMentorId(@PathVariable Long id) {
        List<Employee> menteeList = mentorTreeService.getEmployeesFromEmployeeService(
                mentorTreeService.serviceUrl()+"employees/list/",
                    mentorTreeService.getEmployeeIdsFromMentorId(id));
        Resources<Employee> resources = mentorTreeService.addLinkToEmployee(menteeList);

        return resources;
    }

    @GetMapping(value = "/treeleads/{id}/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Employee> getEmployeesByTreeLeadId(@PathVariable Long id) {
        List<Employee> menteeList = mentorTreeService.getEmployeesFromEmployeeService(
                mentorTreeService.serviceUrl()+"employees/list/",
                    mentorTreeService.getEmployeeIdsFromTreeLeadId(id));
        Resources<Employee> resources = mentorTreeService.addLinkToEmployee(menteeList);

        return resources;
    }

    @PatchMapping(value = "/employees/{id}/mentors/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateEmployeeMentorId(@PathVariable Long id, @RequestBody Map<String, Long> body) {
        boolean updateSuccess = mentorTreeService.updateMentorIdForEmployee(id, body.get("mentorId"));
        return mentorTreeService.getSuccessMessage(updateSuccess, "Set new mentor for employee with id: " + id);
    }

    @PatchMapping(value = "/employees/{id}/treeleads/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateEmployeeTreeLeadId(@PathVariable Long id, @RequestBody Map<String, Long> body) {
        boolean updateSuccess = mentorTreeService.updateTreeLeadIdForEmployee(id, body.get("treeLeadId"));
        return mentorTreeService.getSuccessMessage(updateSuccess, "Set new tree lead for employee with id: " + id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        mentorTreeService.deleteEmployee(id);
    }
}







