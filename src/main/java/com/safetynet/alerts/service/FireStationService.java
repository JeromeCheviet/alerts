package com.safetynet.alerts.service;

import com.safetynet.alerts.model.core.FireStation;
import com.safetynet.alerts.model.dto.FireStationDTO;
import com.safetynet.alerts.model.application.AddressByFireStation;
import com.safetynet.alerts.model.application.PersonByFireStation;

import java.util.List;

public interface FireStationService {

    boolean addressExist(FireStation fireStation);

    List<FireStationDTO> findAll();
    PersonByFireStation findPersonCoverageByFireStation(int stationNumber);
    List<String> findPhoneNumberByFireStation(int stationNumber);
    List<AddressByFireStation> findAddressByFireStation(List<Integer> stations);
    List<FireStation> addFireStation(FireStation fireStation);
    int deleteFireStation(String address, Integer stationNumber);

    List<FireStation> updateFireStation(FireStation fireStation);
}
