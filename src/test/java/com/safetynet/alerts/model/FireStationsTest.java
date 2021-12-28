package com.safetynet.alerts.model;

import com.safetynet.alerts.model.FireStations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FireStationsTest {

    private String actualAddress = "1 rue du Puit";
    private int actualStation = 100;
    private String actualToString = "FireStations{" +
            "address='" + actualAddress + '\'' +
            ", station=" + actualStation +
            '}';

    @Test
    public void createAFireStation() {
        FireStations fireStations = new FireStations();

        fireStations.setAddress(actualAddress);
        fireStations.setStation(actualStation);

        assertEquals(fireStations.getAddress(), actualAddress);
        assertEquals(fireStations.getStation(), actualStation);
        assertEquals(fireStations.toString(), actualToString);
    }
}
