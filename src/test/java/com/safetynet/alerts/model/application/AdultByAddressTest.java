package com.safetynet.alerts.model.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdultByAddressTest {
    private static String actualFirstName = "Jerome";
    private static String actualLastName = "Cheviet";
    private static String actualToString;

    private AdultByAddress adultByAddress = new AdultByAddress();

    @BeforeEach
    private void setUp() {
        actualToString = "{" +
                "firstName='" + actualFirstName + '\'' +
                ", lastName='" + actualLastName + '\'' +
                '}';
    }

    @Test
    public void createAdultByAddress() {
        adultByAddress.setFirstName(actualFirstName);
        adultByAddress.setLastName(actualLastName);

        assertEquals(adultByAddress.getFirstName(), actualFirstName);
        assertEquals(adultByAddress.getLastName(), actualLastName);
        assertEquals(adultByAddress.toString(), actualToString);
    }

    @Test
    public void createAdultByAddressWithConstructor() {
        adultByAddress = new AdultByAddress(actualFirstName, actualLastName);

        assertEquals(adultByAddress.getFirstName(), actualFirstName);
        assertEquals(adultByAddress.getLastName(), actualLastName);
        assertEquals(adultByAddress.toString(), actualToString);
    }
}
