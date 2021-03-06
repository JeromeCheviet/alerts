package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.application.AddressByFireStation;
import com.safetynet.alerts.model.application.CustomMessage;
import com.safetynet.alerts.model.application.PersonByFireStation;
import com.safetynet.alerts.model.application.PersonInfo;
import com.safetynet.alerts.model.core.FireStation;
import com.safetynet.alerts.service.FireStationService;
import com.safetynet.alerts.service.FireStationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @MockBean
    private CustomMessage customMessage;

    @MockBean
    private PersonByFireStation personByFireStation;

    @MockBean
    private PersonInfo personInfo;

    @MockBean
    private AddressByFireStation addressByFireStation;

    @MockBean
    private FireStation fireStation;

    private List<PersonInfo> personInfoList = new ArrayList<>();
    private List<String> phoneNumbers = new ArrayList<>();
    private List<Integer> stations = new ArrayList<>();
    private List<AddressByFireStation> addressByFireStationList = new ArrayList<>();
    private List<FireStation> fireStationList = new ArrayList<>();
    private int nbAdulte;
    private int nbMinor;

    @BeforeEach
    private void setUp() {

        List<String> medicament = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        String firstName = "Jerome";
        String lastName = "Cheviet";
        String address = "1 rue du Puit";
        String city = "Cluver";
        int zip = 97451;
        int age = 41;
        String email = "jcheviet@email.com";
        String phone = "123-456-7890";
        medicament.add("aznol:350mg");
        allergies.add("peanut");
        int station = 20;

        personInfo = new PersonInfo(firstName, lastName, address, city, zip, age, email, phone, medicament, allergies);
        fireStation = new FireStation(address, station);
    }

    @Test
    public void testGetFireStation() throws Exception {
        mockMvc.perform(get("/firestations"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPersonCoverageByAFireStation_Ok() throws Exception {
        personInfoList.add(personInfo);
        nbAdulte = 1;
        nbMinor = 1;
        personByFireStation = new PersonByFireStation(personInfoList, nbAdulte, nbMinor);

        when(fireStationService.findPersonCoverageByFireStation(1)).thenReturn(personByFireStation);

        mockMvc.perform(get("/firestation")
                        .param("stationNumber", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPersonCoverageByAFireStation_NoContent() throws Exception {
        personByFireStation = new PersonByFireStation(personInfoList, nbAdulte, nbMinor);

        when(fireStationService.findPersonCoverageByFireStation(1)).thenReturn(personByFireStation);

        mockMvc.perform(get("/firestation")
                        .param("stationNumber", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetPhoneNumberByFireStation_Ok() throws Exception {
        phoneNumbers.add("123-456-7890");

        when(fireStationService.findPhoneNumberByFireStation(1)).thenReturn(phoneNumbers);

        mockMvc.perform(get("/phoneAlert")
                        .param("firestation", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPhoneNumberByFireStation_NoContent() throws Exception {
        when(fireStationService.findPhoneNumberByFireStation(1)).thenReturn(phoneNumbers);

        mockMvc.perform(get("/phoneAlert")
                        .param("firestation", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAddressByFireStation_Ok() throws Exception {
        personInfoList.add(personInfo);
        addressByFireStation = new AddressByFireStation("MyAddress", personInfoList);
        addressByFireStationList.add(addressByFireStation);
        stations.add(1);
        stations.add(3);

        when(fireStationService.findAddressByFireStation(stations)).thenReturn(addressByFireStationList);

        mockMvc.perform(get("/flood/stations")
                        .param("stations", "1, 3"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAddressByFireStation_NoContent() throws Exception {
        stations.add(1);
        stations.add(3);

        when(fireStationService.findAddressByFireStation(stations)).thenReturn(addressByFireStationList);

        mockMvc.perform(get("/flood/stations")
                        .param("stations", "1, 3"))
                .andExpect(status().isNoContent());
    }

}
