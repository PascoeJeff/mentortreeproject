package com.solstice;

import com.solstice.controller.EmployeeRestController;
import com.solstice.entity.Employee;
import com.solstice.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    private EmployeeRestController employeeRestController = new EmployeeRestController();

    @Test
    public void getWithEmployeeId_returnEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Joe");
        employee.setLastName("");
        employee.setEmail("");
        employee.setEmployeeNumber(1L);
        employee.setTitle("");
        employee.setOffice("");
        employee.setImageUrl("");

        when(employeeService.retrieveEmployeeById(Mockito.anyLong())).thenReturn(employee);
        ReflectionTestUtils.setField(employeeRestController, "employeeService", employeeService);
        Employee actual = employeeRestController.getEmployeesById(7L);
        assertEquals(employee, actual);
    }


    @Test
    public void getWithEmployeeIds_returnsEmployeesList() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Jordan");
        employee.setLastName("");
        employee.setEmail("");
        employee.setEmployeeNumber(1L);
        employee.setTitle("");
        employee.setOffice("");
        employee.setImageUrl("");
        Employee employee2 = new Employee();
        employee.setFirstName("Lebron");
        employee.setLastName("");
        employee.setEmail("");
        employee.setEmployeeNumber(2L);
        employee.setTitle("");
        employee.setOffice("");
        employee.setImageUrl("");
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee);
        employeeList.add(employee2);
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(3L);
        when(employeeService.retrieveEmployeesByIdList(any(List.class))).thenReturn(employeeList);
        ReflectionTestUtils.setField(employeeRestController, "employeeService", employeeService);
        List<Employee> actualEmployees = employeeRestController.getEmployeesByIdList(ids);
        assertEquals(employeeList, actualEmployees);
    }

    @Test
    public void updateEmployee_returnsUpdateSuccess() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Joe");
        employee.setLastName("");
        employee.setEmail("");
        employee.setEmployeeNumber(1L);
        employee.setTitle("");
        employee.setOffice("");
        employee.setImageUrl("");
        when(employeeService.updateEmployee(employee,1L)).thenReturn(employee);
        ReflectionTestUtils.setField(employeeRestController, "employeeService", employeeService);
        Employee actual = employeeRestController.updateEmployee(employee,1L);
        assertEquals(employee, actual);
    }
}