package com.safetynet.alerts.manager;

import com.safetynet.alerts.model.core.FireStation;
import com.safetynet.alerts.model.dto.FireStationDTO;
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
public class FireStationMapperTest {
    private final List<FireStationDTO> fireStationDTOList = new ArrayList<>();
    private final List<FireStation> fireStationList = new ArrayList<>();

    @InjectMocks
    private FireStationMapper fireStationMapper = new FireStationMapper();
    @Mock
    private FireStationDTO expectFireStationDTO;
    @Mock
    private FireStation expectFireStation;

    @BeforeEach
    private void setUp() {
        String expectAddress = "1 rue du Puit";
        int expectStation = 20;

        expectFireStationDTO = new FireStationDTO(expectAddress, expectStation);
        expectFireStation = new FireStation(expectAddress, expectStation);
    }

    @Test
    public void testMapDtoToDomainFireStation() {
        String actualAddress = "1 rue du Puit";
        int actualStation = 20;
        FireStationDTO actualFireStationDTO = new FireStationDTO(actualAddress, actualStation);

        FireStation actualFireStation = fireStationMapper.mapDtoToDomainFireStation(actualFireStationDTO);

        assertEquals(expectFireStation.getAddress(), actualFireStation.getAddress());
        assertEquals(expectFireStation.getStation(), actualFireStation.getStation());
        assertEquals(expectFireStation.toString(), actualFireStation.toString());
    }

    @Test
    public void testMapDtoToDomainFireStationList() {
        String actualAddress = "1 rue du Puit";
        int actualStation = 20;
        FireStationDTO actualFireStationDTO = new FireStationDTO(actualAddress, actualStation);
        fireStationDTOList.add(actualFireStationDTO);

        fireStationList.add(expectFireStation);

        List<FireStation> actualFireStationList = fireStationMapper.mapDtoToDomainFireStationList(fireStationDTOList);

        assertEquals(fireStationList.toString(), actualFireStationList.toString());
    }

    @Test
    public void testMapDomainFireStationToDto() {
        String actualAddress = "1 rue du Puit";
        int actualStation = 20;
        FireStation actualFireStation = new FireStation(actualAddress, actualStation);

        FireStationDTO actualFireStationDTO = fireStationMapper.mapDomainFireStationToDto(actualFireStation);

        assertEquals(expectFireStationDTO.getAddress(), actualFireStationDTO.getAddress());
        assertEquals(expectFireStationDTO.getStation(), actualFireStationDTO.getStation());
        assertEquals(expectFireStationDTO.toString(), actualFireStationDTO.toString());
    }
}
