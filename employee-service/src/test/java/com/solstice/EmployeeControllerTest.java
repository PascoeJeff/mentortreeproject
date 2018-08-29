package com.solstice;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    MockMvc mvc;

   @Test
    public void getWithEmployeeId_returnsEmployee() throws Exception {
        mvc.perform(get("/employees/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 1, firstName: \"Joe\",lastName: \"\", employeeNumber: 1, office: \"\", title: \"\", email: \"\", imageUrl: \"\"}"));
    }

    @Test
    public void getWithEmployeeIds_returnsEmployees() throws Exception {
        mvc.perform(get("/employees/list/3,4"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jeff"))).andExpect(content().string(containsString("Sher")));
    }

    @Test
    public void updateEmployee_returnsUpdateSucess() throws Exception {
       mvc.perform(put("/employees/{id}", 5).content("{\"id\": 5, \"firstName\": \"John\",\"lastName\": \"Benz\", \"employeeNumber\": 5, \"office\": \"Chicago\", \"title\": \"Super Fast\", \"email\": \"\", \"imageUrl\": \"\"}").contentType("application/json;charset=UTF-8"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("Benz")));
    }
}
