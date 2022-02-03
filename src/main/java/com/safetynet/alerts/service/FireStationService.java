package com.safetynet.alerts.service;

import com.safetynet.alerts.model.DTO.FireStationDTO;
import com.safetynet.alerts.model.application.AddressByFireStation;
import com.safetynet.alerts.model.application.PersonByFireStation;

import java.util.List;

public interface FireStationService {

    List<FireStationDTO> findAll();
    PersonByFireStation findPersonCoverageByFireStation(int stationNumber);
    List<String> findPhoneNumberByFireStation(int stationNumber);
    List<AddressByFireStation> findAddressByFireStation(List<Integer> stations);
}
