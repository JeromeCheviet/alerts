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
public class AddressByFireStationTest {
    private final List<PersonInfo> personInfoList = new ArrayList<>();
    private final List<String> expectMedicament = new ArrayList<>();
    private final List<String> expectAllergies = new ArrayList<>();
    private static String expectFirstName = "Jerome";
    private static String expectLastName = "Cheviet";
    private static String expectAddress = "1 rue du Puit";
    private static String expectCity = "Cluver";
    private static int expectZip = 97451;
    private static int expectAge = 41;
    private static String expectEmail = "jcheviet@email.com";
    private static String expectPhone = "123-456-7890";
    private static String expectToString;

    @InjectMocks
    private AddressByFireStation expectAddressByFireStation;
    @Mock
    private PersonInfo expectPersonInfo;

    @BeforeEach
    private void setUp() {

        expectMedicament.add("aznol:350mg");
        expectAllergies.add("peanut");

        expectPersonInfo = new PersonInfo(expectFirstName,
                expectLastName,
                expectAddress,
                expectCity,
                expectZip,
                expectAge,
                expectEmail,
                expectPhone,
                expectMedicament,
                expectAllergies);

        personInfoList.add(expectPersonInfo);

        expectToString = "{'" +
                expectAddress + "'" +
                ", " + personInfoList +
                "}";

    }

    @Test
    public void createAddressByFireStation() {
        expectAddressByFireStation.setAddress(expectAddress);
        expectAddressByFireStation.setPersonInfoList(personInfoList);

        assertEquals(expectAddressByFireStation.getAddress(), expectPersonInfo.getAddress());
        assertEquals(expectAddressByFireStation.getPersonInfoList().toString(), personInfoList.toString());
        assertEquals(expectAddressByFireStation.toString(), expectToString);
    }

}
