package com.solstice;

import com.solstice.controller.MentorTreeRestController;
import com.solstice.domain.Employee;
import com.solstice.domain.EmployeeInfo;
import com.solstice.service.MentorTreeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MentorTreeRestControllerTest {

    @Mock
    MentorTreeService mentorTreeService = mock(MentorTreeService.class);
    MentorTreeRestController mentorTreeRestController = new MentorTreeRestController();

    @Test
    public void getEmployeeById_returnEmployee() throws Exception {
        EmployeeInfo testEmployee = new EmployeeInfo(3L, "Jeff", "", 3L, "", "", "","");

        when(mentorTreeService.getEmployeeFromEmployeeService(
                mentorTreeService.serviceUrl("employee-service")+"employees/{id}/",3L))
            .thenReturn(testEmployee);

        ReflectionTestUtils.setField(mentorTreeRestController, "mentorTreeService", mentorTreeService);
        assertEquals(testEmployee, mentorTreeRestController.getEmployeeById(3L));
    }

    @Test
    public void getEmployeesByMentorId_returnEmployees() throws Exception{
        List<Employee> testEmployeeList = Arrays.asList(
                new Employee(1L, "Sher", "", 1L, "", "", "",""),
                new Employee(2L, "Jeff", "", 2L, "", "", "",""));

        testEmployeeList.get(0).setLink(new Link("LinkToSher"));
        testEmployeeList.get(1).setLink(new Link("LinkToJeff"));

        when(mentorTreeService.getEmployeeIdsFromMentorId(3L))
                .thenReturn(Arrays.asList(1L, 2L));
        when(mentorTreeService.addLinkToEmployee(testEmployeeList)).thenReturn(new Resources<>(testEmployeeList));
        when(mentorTreeService.getEmployeesFromEmployeeService(
                mentorTreeService.serviceUrl("employee-service")+"employees/list/", Arrays.asList(1L, 2L)))
                .thenReturn(testEmployeeList);

        ReflectionTestUtils.setField(mentorTreeRestController, "mentorTreeService", mentorTreeService);
        assertEquals(new Resources<>(testEmployeeList), mentorTreeRestController.getEmployeesByMentorId(3L));
    }

    @Test
    public void getEmployeesByTreeLeadId_returnsEmployees() throws Exception{
        List<Employee> testEmployeeList = Arrays.asList(
                new Employee(1L, "Sher", "", 1L, "", "", "",""),
                new Employee(2L, "Jeff", "", 2L, "", "", "",""));

        testEmployeeList.get(0).setLink(new Link("LinkToSher"));
        testEmployeeList.get(1).setLink(new Link("LinkToJeff"));

        when(mentorTreeService.getEmployeeIdsFromTreeLeadId(3L))
                .thenReturn(Arrays.asList(1L, 2L));
        when(mentorTreeService.addLinkToEmployee(testEmployeeList)).thenReturn(new Resources<>(testEmployeeList));
        when(mentorTreeService.getEmployeesFromEmployeeService(
                mentorTreeService.serviceUrl("employee-service")+"employees/list/", Arrays.asList(1L, 2L)))
                .thenReturn(testEmployeeList);

        ReflectionTestUtils.setField(mentorTreeRestController, "mentorTreeService", mentorTreeService);
        assertEquals(new Resources<>(testEmployeeList), mentorTreeRestController.getEmployeesByTreeLeadId(3L));
    }
    @Test
    public void updateEmployeeMentorId_returnsSuccessMessage() throws Exception{
        Map<String, Long> testMap = new HashMap<>();
        testMap.put("mentorId", 3L);

        when(mentorTreeService.updateMentorIdForEmployee(1L, 3L))
                .thenReturn(true);
        when(mentorTreeService.getSuccessMessage(true, "Set new mentor for employee with id: 1")).thenReturn("Set new mentor for employee with id: 1");

        ReflectionTestUtils.setField(mentorTreeRestController, "mentorTreeService", mentorTreeService);
        assertEquals("Set new mentor for employee with id: 1", mentorTreeRestController.updateEmployeeMentorId(1L, testMap));
    }

    @Test
    public void updateEmployeeTreeLeadId_returnsSuccessMessage() throws Exception{
        Map<String, Long> testMap = new HashMap<>();
        testMap.put("treeLeadId", 3L);

        when(mentorTreeService.updateTreeLeadIdForEmployee(1L, 3L))
                .thenReturn(true);
        when(mentorTreeService.getSuccessMessage(true, "Set new tree lead for employee with id: 1")).thenReturn("Set new tree lead for employee with id: 1");

        ReflectionTestUtils.setField(mentorTreeRestController, "mentorTreeService", mentorTreeService);
        assertEquals("Set new tree lead for employee with id: 1", mentorTreeRestController.updateEmployeeTreeLeadId(1L, testMap));
    }
}

