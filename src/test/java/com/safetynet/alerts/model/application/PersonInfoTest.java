package com.safetynet.alerts.model.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonInfoTest {

    private static PersonInfo personInfo;

    private static List<String> actualMedicament = new ArrayList<>();
    private static List<String> actualAllergies = new ArrayList<>();

    private static String actualFirstName = "Jerome";
    private static String actualLastName = "Cheviet";
    private static String actualAddress = "1 rue du Puit";
    private static String actualCity = "Cluver";
    private static int actualZip = 97451;
    private static int actualAge = 41;
    private static String actualEmail = "jcheviet@email.com";
    private static String actualPhone = "123-456-7890";
    private static String actualToString;

    @BeforeEach
    private void init() {
        actualMedicament.add("aznol:350mg");
        actualAllergies.add("peanut");

        actualToString = "{" +
                "firstName='" + actualFirstName + '\'' +
                ", lastName='" + actualLastName + '\'' +
                ", address='" + actualAddress + '\'' +
                ", city='" + actualCity + '\'' +
                ", zip=" + actualZip +
                ", age=" + actualAge +
                ", email='" + actualEmail + '\'' +
                ", phone='" + actualPhone + '\'' +
                ", medicament=" + actualMedicament +
                ", allergies=" + actualAllergies +
                '}';

        personInfo = new PersonInfo();
    }


    @Test
    public void createAPersonInfo() {

        personInfo.setFirstName(actualFirstName);
        personInfo.setLastName(actualLastName);
        personInfo.setAddress(actualAddress);
        personInfo.setCity(actualCity);
        personInfo.setZip(actualZip);
        personInfo.setAge(actualAge);
        personInfo.setEmail(actualEmail);
        personInfo.setPhone(actualPhone);
        personInfo.setMedicament(actualMedicament);
        personInfo.setAllergies(actualAllergies);



        assertEquals(personInfo.getFirstName(), actualFirstName);
        assertEquals(personInfo.getLastName(), actualLastName);
        assertEquals(personInfo.getAddress(), actualAddress);
        assertEquals(personInfo.getCity(), actualCity);
        assertEquals(personInfo.getZip(), actualZip);
        assertEquals(personInfo.getAge(), actualAge);
        assertEquals(personInfo.getEmail(), actualEmail);
        assertEquals(personInfo.getPhone(), actualPhone);
        assertEquals(personInfo.getMedicament(), actualMedicament);
        assertEquals(personInfo.getAllergies(), actualAllergies);
        assertEquals(personInfo.toString(), actualToString);

    }

    @Test
    public void createAPersonInfoWithConstructor() {
        personInfo = new PersonInfo(
                actualFirstName,
                actualLastName,
                actualAddress,
                actualCity,
                actualZip,
                actualAge,
                actualEmail,
                actualPhone,
                actualMedicament,
                actualAllergies);


        assertEquals(personInfo.getFirstName(), actualFirstName);
        assertEquals(personInfo.getLastName(), actualLastName);
        assertEquals(personInfo.getAddress(), actualAddress);
        assertEquals(personInfo.getCity(), actualCity);
        assertEquals(personInfo.getZip(), actualZip);
        assertEquals(personInfo.getAge(), actualAge);
        assertEquals(personInfo.getEmail(), actualEmail);
        assertEquals(personInfo.getPhone(), actualPhone);
        assertEquals(personInfo.getMedicament(), actualMedicament);
        assertEquals(personInfo.getAllergies(), actualAllergies);
        assertEquals(personInfo.toString(), actualToString);
    }
}
