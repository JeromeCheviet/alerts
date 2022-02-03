package com.safetynet.alerts.model;

import com.safetynet.alerts.model.DTO.FireStationDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FireStationDTOTest {

    private String actualAddress = "1 rue du Puit";
    private int actualStation = 100;
    private String actualToString = "FireStations{" +
            "address='" + actualAddress + '\'' +
            ", station=" + actualStation +
            '}';

    @Test
    public void createAFireStation() {
        FireStationDTO fireStationDTO = new FireStationDTO();

        fireStationDTO.setAddress(actualAddress);
        fireStationDTO.setStation(actualStation);

        assertEquals(fireStationDTO.getAddress(), actualAddress);
        assertEquals(fireStationDTO.getStation(), actualStation);
        assertEquals(fireStationDTO.toString(), actualToString);
    }

    @Test
    public void createAFireStationWithConstructor() {
        FireStationDTO fireStationDTO = new FireStationDTO(actualAddress, actualStation);

        assertEquals(fireStationDTO.getAddress(), actualAddress);
        assertEquals(fireStationDTO.getStation(), actualStation);
        assertEquals(fireStationDTO.toString(), actualToString);
    }
}
