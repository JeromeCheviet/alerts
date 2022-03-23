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
public class FireStationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testGetFireStation() throws Exception {
        mockMvc.perform(get("/firestations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].station", is(3)));
    }

    @Test
    @Order(2)
    public void testCreateFireStation_created() throws Exception {
        mockMvc.perform(
                        post("/firestation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"address\": \"1 rue du Puit\",\"station\": 20}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[13].station", is(20)));

    }

    @Test
    @Order(3)
    public void testCreateFireStation_Conflict() throws Exception {
        mockMvc.perform(
                        post("/firestation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"address\": \"1509 Culver St\",\"station\": 3}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(4)
    public void testUpdateFireStation_ok() throws Exception {
        mockMvc.perform(
                        put("/firestation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"address\": \"1509 Culver St\",\"station\": 40}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
                .andExpect(jsonPath("$[0].station", is(40)));
    }

    @Test
    @Order(5)
    public void testUpdateFireStation_Conflict() throws Exception {
        mockMvc.perform(
                        put("/firestation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"address\": \"10 rue de la Fontaine\",\"station\": 3}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(6)
    public void testDeleteFireStation_return1_Ok() throws Exception {
        mockMvc.perform(delete("/firestation")
                        .param("address", "748 Townings Dr")
                        .param("stationNumber", "3"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void testDeleteFireStation_return2_OK() throws Exception {
        mockMvc.perform(delete("/firestation")
                        .param("stationNumber", "4"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(8)
    public void testDeleteFireStation_return3_Ok() throws Exception {
        mockMvc.perform(delete("/firestation")
                        .param("address", "E. Rose Dr"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(9)
    public void testDeleteFireStation_Conflict() throws Exception {
        mockMvc.perform(delete("/firestation"))
                .andExpect(status().isConflict());
    }


}
