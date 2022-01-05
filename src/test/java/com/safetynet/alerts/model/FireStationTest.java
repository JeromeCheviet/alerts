package com.safetynet.alerts.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FireStationTest {

    private String actualAddress = "1 rue du Puit";
    private int actualStation = 100;
    private String actualToString = "FireStations{" +
            "address='" + actualAddress + '\'' +
            ", station=" + actualStation +
            '}';

    @Test
    public void createAFireStation() {
        FireStation fireStation = new FireStation();

        fireStation.setAddress(actualAddress);
        fireStation.setStation(actualStation);

        assertEquals(fireStation.getAddress(), actualAddress);
        assertEquals(fireStation.getStation(), actualStation);
        assertEquals(fireStation.toString(), actualToString);
    }

    @Test
    public void createAFireStationWithConstructor() {
        FireStation fireStation = new FireStation(actualAddress, actualStation);

        assertEquals(fireStation.getAddress(), actualAddress);
        assertEquals(fireStation.getStation(), actualStation);
        assertEquals(fireStation.toString(), actualToString);
    }
}
