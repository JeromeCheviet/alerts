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
public class MedicalRecordControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testGetMedicalRecords() throws Exception {
        mockMvc.perform(get("/medicalrecords"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].firstName", is("John")));
    }

    @Test
    @Order(2)
    public void testCreateMedicalRecord_Created() throws Exception {
        String contentJson = "{" +
                "\"firstName\":\"Ronane\"," +
                "\"lastName\":\"Cheviet\"," +
                "\"birthdate\":\"03/08/1984\"," +
                "\"medications\":[\"aznol:350mg\"]," +
                "\"allergies\":[\"peanut\", \"pollen\"]" +
                "}";

        mockMvc.perform(
                        post("/medicalRecord")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[23].firstName", is("Ronane")));
    }

    @Test
    @Order(3)
    public void testCreateMedicalRecord_Conflict() throws Exception {
        String contentJson = "{" +
                "\"firstName\":\"John\"," +
                "\"lastName\":\"Boyd\"," +
                "\"birthdate\":\"03/06/1984\"," +
                "\"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"]," +
                "\"allergies\":[\"nillacilan\"]" +
                "}";

        mockMvc.perform(
                        post("/medicalRecord")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(4)
    public void testUpdateMedicalRecord_Ok() throws Exception {
        String contentJson = "{" +
                "\"firstName\":\"John\"," +
                "\"lastName\":\"Boyd\"," +
                "\"birthdate\":\"02/06/1984\"," +
                "\"medications\":[\"hydrapermazol:100mg\"]," +
                "\"allergies\":[\"nillacilan\"]" +
                "}";

        mockMvc.perform(
                        put("/medicalRecord")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].birthdate", is("02/06/1984")));
    }

    @Test
    @Order(5)
    public void testUpdateMedicalRecord_Conflict() throws Exception {
        String contentJson = "{" +
                "\"firstName\":\"Jerome\"," +
                "\"lastName\":\"Cheviet\"," +
                "\"birthdate\":\"09/03/1984\"," +
                "\"medications\":[]," +
                "\"allergies\":[\"peanut\", \"pollen\"]" +
                "}";

        mockMvc.perform(
                        put("/medicalRecord")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(6)
    public void testDeleteMedicalRecord_Ok() throws Exception {
        mockMvc.perform(
                        delete("/medicalRecord")
                                .param("firstName", "Warren")
                                .param("lastName", "Zemicks"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void testDeleteMedicalRecord() throws Exception {
        mockMvc.perform(
                delete("/medicalRecord")
                        .param("firstName", "Julien")
                        .param("lastName", "Cheviet"))
                .andExpect(status().isConflict());
    }

}
