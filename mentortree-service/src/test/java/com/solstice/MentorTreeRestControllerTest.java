package com.solstice;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MentorTreeRestControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void getEmployeeById_returnEmployee() throws Exception {
        mvc.perform(get("/employees/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json("{ id: 1, firstName: \"\",lastName: \"\", employeeNumber: 1, office: \"\", title: \"\", email: \"\", imageUrl: \"\"}"));
    }

    @Test
    public void getEmployeesByMentorId_returnEmployees() throws Exception{
        mvc.perform(get("/mentors/{id}/employees", 11))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jeff")))
                .andExpect(content().string(containsString("sher")));
    }

    @Test
    public void getEmployeesByTreeLeadId_returnsEmployees() throws Exception{
        mvc.perform(get("/treeleads/{id}/employees" ,1))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jeff")))
                .andExpect(content().string(containsString("sher")));
    }
    @Test
    public void updateEmpoyeeById_returnsOk() throws Exception{
        long id = 2;
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/employees/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(updateMike());
        this.mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String updateMike() {
        return "{ firstname : \"Mike\" , lastname :\"Lexus\"}";
    }


}

