package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.dto.FireStationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FireStationRepositoryTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private FireStationDTO fireStationDTO;

    private List<FireStationDTO> fireStationDTOList = new ArrayList<>();

    private FireStationRepository fireStationRepository;
    private JsonNode firestations;

    @BeforeEach
    private void setUp() throws Exception{

        String jsonString = "{ \"firestations\": [\n" +
                "\t    { \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" }]}";

        firestations = mapper.readTree(jsonString);

        //String address = "1 rue du puit";
        //int station = 20;

        //fireStationDTO = new FireStationDTO(address, station);
    }

   /* @Test
    public void testSetModel() throws Exception {

        fireStationRepository.setModel(firestations);
        System.out.println(fireStationRepository.getFireStationList());
        verify(mapper, times(1)).readValue(anyString(), eq(FireStationDTO.class));
    }*/

/*    @Test
    public void testGetFireStationList() throws Exception {
        fireStationDTOList.add(fireStationDTO);
        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);
        System.out.println(fireStationRepository.getFireStationList());

    }*/
}
