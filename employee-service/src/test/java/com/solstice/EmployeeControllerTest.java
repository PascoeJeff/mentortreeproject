package com.solstice;


import com.solstice.controller.EmployeeRestController;
import com.solstice.entity.Employee;
import com.solstice.service.EmployeeService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {



    @Mock
    private EmployeeService employeeService;

    private EmployeeRestController employeeRestController = new EmployeeRestController();

   @Test
    public void getWithEmployeeId_returnsEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Joe");
        employee.setLastName("");
        employee.setEmail("");
        employee.setEmployeeNumber(1L);
        employee.setTitle("");
        employee.setOffice("");
        employee.setImageUrl("");

       Mockito.when(employeeService.findById(Mockito.anyLong())).thenReturn(employee);
       ReflectionTestUtils.setField(employeeRestController, "employeeService", employeeService);
       Employee actual = employeeRestController.getEmployeesById(7L);

       Assert.assertEquals(employee, actual);

//       mvc.perform(get("/employees/{id}", 7))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{firstName: \"Joe\",lastName: \"\", employeeNumber: 1, office: \"\", title: \"\", email: \"\", imageUrl: \"\"}"));
   }

    @Test
    public void getWithEmployeeIds_returnsEmployees() throws Exception {
//        mvc.perform(get("/employees/list/3,4"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Jeff"))).andExpect(content().string(containsString("Sher")));
       //Mockito.when(employeeService.getEmployeesByIdList(any(List.class))).thenReturn(Arrays.asList(employee));
    }
    @Test
    public void getWithEmployeeIds_returnsEmployeesList() throws Exception {

//        mvc.perform(post("/employees/").content("[3,4]").contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Jeff"))).andExpect(content().string(containsString("Sher")));
    }

    @Test
    public void updateEmployee_returnsUpdateSucess() throws Exception {
//       mvc.perform(put("/employees/{id}", 5).content("{\"id\": 5, \"firstName\": \"John\",\"lastName\": \"Benz\", \"employeeNumber\": 5, \"office\": \"Chicago\", \"title\": \"Super Fast\", \"email\": \"\", \"imageUrl\": \"\"}").contentType("application/json;charset=UTF-8"))
//               .andExpect(status().isOk())
//               .andExpect(content().string(containsString("Benz")));
    }
}
