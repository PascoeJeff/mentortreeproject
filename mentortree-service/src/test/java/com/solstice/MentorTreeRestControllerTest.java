package com.solstice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MentorTreeRestControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void getEmployeeById_returnEmployee() throws Exception {

        mvc.perform(get("/mentortree/employees/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json("{ id: 1, firstName: \"\",lastName: \"\", employeeNumber: 1, office: \"\", title: \"\", email: \"\", imageUrl: \"\"}"));
    }

    @Test
    public void getEmployeesByMentorId_returnEmployees() throws Exception{
        mvc.perform(get("/mentortree/mentor/{id}/employees", 11))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jeff")))
                .andExpect(content().string(containsString("sher")));
    }

}

