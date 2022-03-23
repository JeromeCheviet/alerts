package com.safetynet.alerts.model.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PersonByAddressTest {
    private static String actualFirstName = "Jerome";
    private static String actualLastName = "Cheviet";
    private static String actualAddress = "1 rue du Puit";
    private static String actualCity = "Cluver";
    private static int actualZip = 97451;
    private static int actualAge = 41;
    private static String actualEmail = "jcheviet@email.com";
    private static String actualPhone = "123-456-7890";
    private static int actualFireStationNumber = 20;
    private static String actualToString;

    private static List<PersonInfo> personInfoList = new ArrayList<>();
    private final List<String> actualMedicament = new ArrayList<>();
    private final List<String> actualAllergies = new ArrayList<>();

    @InjectMocks
    private PersonByAddress personByAddress;
    @Mock
    private PersonInfo actualPersonInfo;

    @BeforeEach
    private void setUp() {
        actualMedicament.add("aznol:350mg");
        actualAllergies.add("peanut");

        actualPersonInfo = new PersonInfo(actualFirstName,
                actualLastName,
                actualAddress,
                actualCity,
                actualZip,
                actualAge,
                actualEmail,
                actualPhone,
                actualMedicament,
                actualAllergies);

        personInfoList.add(actualPersonInfo);

        actualToString = "{" +
                personInfoList +
                ", fireStationNumber=" + actualFireStationNumber +
                '}';
    }

    @Test
    public void createPersonByAddress() {
        personByAddress.setPersonInfoList(personInfoList);
        personByAddress.setFireStationNumber(actualFireStationNumber);

        assertEquals(personByAddress.getPersonInfoList().toString(), personInfoList.toString());
        assertEquals(personByAddress.getFireStationNumber(), actualFireStationNumber);
        assertEquals(personByAddress.toString(), actualToString);
    }

    @Test
    public void createPersonByAddressWithConstructor() {
        personByAddress = new PersonByAddress(personInfoList, actualFireStationNumber);

        assertEquals(personByAddress.getPersonInfoList().toString(), personInfoList.toString());
        assertEquals(personByAddress.getFireStationNumber(), actualFireStationNumber);
        assertEquals(personByAddress.toString(), actualToString);
    }

}
