package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.application.*;
import com.safetynet.alerts.model.core.Person;
import com.safetynet.alerts.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private ChildrenByAddressConstructor childrenByAddressConstructor;

    @MockBean
    private PersonByAddress personByAddress;

    @MockBean
    private PersonInfo personInfo;

    @MockBean
    private ChildrenByAddress childrenByAddress;

    @MockBean
    private AdultByAddress adultByAddress;

    @MockBean
    private Person person;

    private List<PersonInfo> personInfoList = new ArrayList<>();
    private List<ChildrenByAddress> childrenByAddressList = new ArrayList<>();
    private List<AdultByAddress> adultByAddressList = new ArrayList<>();
    private List<Person> personList = new ArrayList<>();

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


        personInfo = new PersonInfo(firstName, lastName, address, city, zip, age, email, phone, medicament, allergies);

        childrenByAddress = new ChildrenByAddress(firstName, lastName, age);
        adultByAddress = new AdultByAddress(firstName, lastName);

        person = new Person(firstName, lastName, address, city, zip, phone, email);
    }

    @Test
    public void testGetPersons() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCommunityEmail_NoContent() throws Exception {
        mockMvc.perform(get("/communityEmail").param("city", "MyCity"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetCommunityEmailWithOk() throws Exception {
        when(personService.findEmailByCity("Culver")).thenReturn(Arrays.asList("jcheviet@email.me"));

        mockMvc.perform(get("/communityEmail").param("city", "Culver"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFire_Ok() throws Exception {
        personInfoList.add(personInfo);
        personByAddress = new PersonByAddress(personInfoList, 2);

        when(personService.findPersonByAddress("MyAddress")).thenReturn(personByAddress);

        mockMvc.perform(get("/fire").param("address", "MyAddress"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFire_NoContent() throws Exception {
        personByAddress = new PersonByAddress();

        when(personService.findPersonByAddress("MyAddress")).thenReturn(personByAddress);

        mockMvc.perform(get("/fire").param("address", "MyAddress"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetChildrenByAddress_Ok() throws Exception {
        childrenByAddressList.add(childrenByAddress);
        adultByAddressList.add(adultByAddress);
        childrenByAddressConstructor = new ChildrenByAddressConstructor(childrenByAddressList, adultByAddressList);

        when(personService.findChildrenByAddress("MyAddress")).thenReturn(childrenByAddressConstructor);

        mockMvc.perform(get("/childAlert").param("address", "MyAddress"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetChildrenByAddress_NoContent() throws Exception {
        childrenByAddressConstructor = new ChildrenByAddressConstructor();

        when(personService.findChildrenByAddress("MyAddress")).thenReturn(childrenByAddressConstructor);

        mockMvc.perform(get("/childAlert").param("address", "MyAddress"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetPersonInfo_Ok() throws Exception {
        personInfoList.add(personInfo);

        when(personService.findPersonInfo("Jerome", "Cheviet")).thenReturn(personInfoList);

        mockMvc.perform(get("/personInfo").param("firstName", "Jerome").param("lastName", "Cheviet"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPersonInfo_NoContent() throws Exception {
        when(personService.findPersonInfo("Jerome", "Cheviet")).thenReturn(personInfoList);

        mockMvc.perform(get("/personInfo").param("firstName", "Jerome").param("lastName", "Cheviet"))
                .andExpect(status().isNoContent());
    }

}
