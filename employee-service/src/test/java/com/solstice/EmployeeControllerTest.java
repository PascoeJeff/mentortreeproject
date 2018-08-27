package com.solstice;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    Gson gson;

    @Autowired
    MockMvc mvc;

   @Test
    public void getWithEmployeeId_returnsEmployee() throws Exception {
        mvc.perform(get("/employees/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json("{ id: 1, firstName: \"\",lastName: \"\", employeeNumber: 1, office: \"\", title: \"\", email: \"\", imageUrl: \"\"}"));
    }

    @Test
    public void getWithEmployeeIds_returnsEmployees() throws Exception {
        mvc.perform(get("/employees/list/").param("ids", new String[] {"3","4"}))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jeff"))).andExpect(content().string(containsString("sher")));
    }
}
