package com.safetynet.alerts.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControlerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testGetPersons() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    @Order(2)
    public void testCreatePerson_Created() throws Exception {
        String contentJson = "{" +
                "\"firstName\":\"Jerome\"," +
                "\"lastName\":\"Cheviet\"," +
                "\"address\":\"1 rue du Puit\"," +
                "\"city\":\"Culver\"," +
                "\"zip\":\"97451\"," +
                "\"phone\":\"123-456-7890\"," +
                "\"email\":\"jcheviet@email.com\"" +
                "}";

        mockMvc.perform(
                        post("/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[23].firstName", is("Jerome")));
    }

    @Test
    @Order(3)
    public void testCreatePerson_Conflict() throws Exception {
        String contentJson = "{" +
                "\"firstName\":\"John\"," +
                "\"lastName\":\"Boyd\"," +
                "\"address\":\"1509 Culver St\"," +
                "\"city\":\"Culver\"," +
                "\"zip\":\"97451\"," +
                "\"phone\":\"841-874-6512\"," +
                "\"email\":\"jaboyd@email.com\"" +
                "}";

        mockMvc.perform(
                        post("/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(4)
    public void testUpdatePerson_Ok() throws Exception {
        String contentJson = "{" +
                "\"firstName\":\"John\"," +
                "\"lastName\":\"Boyd\"," +
                "\"address\":\"1 rue du Puit\"," +
                "\"city\":\"Culver\"," +
                "\"zip\":\"97451\"," +
                "\"phone\":\"841-874-6512\"," +
                "\"email\":\"jaboyd@email.com\"" +
                "}";

        mockMvc.perform(
                        put("/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address", is("1 rue du Puit")));
    }

    @Test
    @Order(5)
    public void testUpdatePerson_Conflict() throws Exception {
        String contentJson = "{" +
                "\"firstName\":\"Ronane\"," +
                "\"lastName\":\"Cheviet\"," +
                "\"address\":\"10 rue de la Fontaine\"," +
                "\"city\":\"Culver\"," +
                "\"zip\":\"97451\"," +
                "\"phone\":\"123-456-7890\"," +
                "\"email\":\"rcheviet@email.com\"" +
                "}";

        mockMvc.perform(
                        put("/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(6)
    public void testDeletePerson_Ok() throws Exception {
        mockMvc.perform(delete("/person")
                        .param("firstName", "Warren")
                        .param("lastName", "Zemicks"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void testDeletePerson_Conflict() throws Exception {
        mockMvc.perform(delete("/person")
                        .param("firstName", "Julien")
                        .param("lastName", "Cheviet"))
                .andExpect(status().isConflict());
    }

}
