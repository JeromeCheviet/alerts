package com.safetynet.alerts.model;

import com.safetynet.alerts.model.FireStations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FireStationsTest {

    @Test
    public void createAFireStation() {
        FireStations fireStations = new FireStations(1, "1 allée du Chateau", 9);

        assertEquals(fireStations.getId(), 1);
        assertEquals(fireStations.getAddress(), "1 allée du Chateau");
        assertEquals(fireStations.getStation(), 9);
    }
}
