package com.solstice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class MentorTreeRestControllerTest {

    @Autowired
    MockMvc mvc;
    @Before


    @Test
    public void getEmployeeById_returnEmployee() throws Exception {
        mvc.perform(get("/employees/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(content().json("{ id: 3, firstName: \"Jeff\",lastName: \"\", employeeNumber: 3, office: \"\", title: \"\", email: \"\", imageUrl: \"\"}"));
    }

    @Test
    public void getEmployeesByMentorId_returnEmployees() throws Exception{
        mvc.perform(get("/mentors/{id}/employees", 1))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jeff")))
                .andExpect(content().string(containsString("Sher")));
    }

    @Test
    public void getEmployeesByTreeLeadId_returnsEmployees() throws Exception{
        mvc.perform(get("/treeleads/{id}/employees" ,2))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jeff")))
                .andExpect(content().string(containsString("Sher")));
    }
    @Test
    public void updateEmployeeMentorId_returnsSuccessMessage() throws Exception{
        long id = 5;
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.patch("/employees/{id}/mentors/", id)
                        .content("{\"mentorId\":1}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON);
        this.mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("Set new mentor for employee with id: " + id)));
    }

    @Test
    public void updateEmployeeTreeLeadId_returnsSuccessMessage() throws Exception{
        long id = 5;
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.patch("/employees/{id}/treeleads/", id)
                        .content("{\"treeLeadId\":1}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON);
        this.mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("Set new tree lead for employee with id: " + id)));
    }

    @Test
    public void deleteEmployeeById_returns200() throws Exception {
        mvc.perform(delete("/employees/{id}" ,6))
                .andExpect(status().isOk());
    }

}

