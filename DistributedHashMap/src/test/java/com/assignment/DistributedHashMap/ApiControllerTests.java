package com.assignment.DistributedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.DistributedHashMap.rest.ApiController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ApiController.class)
@AutoConfigureMockMvc
public class ApiControllerTests {

    @Autowired
    private MockMvc mockMvc, mockMvc2;

    @Test
    public void noParamShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/get")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(""));
    }

    @Test
    public void paramKeyShouldReturnCorrespondingValue() throws Exception {

        this.mockMvc.perform(get("/put").param("key", "k1").param("value","v1"))
                .andDo(print()).andExpect(status().isOk());
        
        this.mockMvc.perform(get("/get").param("key", "k1")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value("v1"));
    }
    
    @Test
    public void multipleParamKeysShouldReturnCorrespondingValues() throws Exception {

        this.mockMvc.perform(get("/put").param("key", "k1").param("value","v1"))
                .andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/put").param("key", "k2").param("value","v2"))
        .andDo(print()).andExpect(status().isOk());
        
        this.mockMvc.perform(get("/get").param("key", "k1")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value("v1"));
        this.mockMvc.perform(get("/get").param("key", "k2")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value("v2"));
    }

    @Test
    public void multipleMVCShouldReturnCorrespondingValues() throws Exception {

        this.mockMvc.perform(get("/put").param("key", "k1").param("value","v1"))
                .andDo(print()).andExpect(status().isOk());
        this.mockMvc2.perform(get("/put").param("key", "k2").param("value","v2"))
        .andDo(print()).andExpect(status().isOk());
        
        this.mockMvc2.perform(get("/get").param("key", "k1")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value("v1"));
        this.mockMvc.perform(get("/get").param("key", "k2")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value("v2"));
    }
}
