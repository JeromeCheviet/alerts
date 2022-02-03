package com.safetynet.alerts.manager;

import com.safetynet.alerts.model.DTO.FireStationDTO;
import com.safetynet.alerts.model.core.FireStation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FireStationMapper {

    private static final Logger logger = LogManager.getLogger(FireStationMapper.class);

    public FireStation mapDtoToDomainFireStation(FireStationDTO fireStationDTO) {
        logger.debug("FireStationMapper mapDtoToDomainFireStation");
        logger.debug("fireStationDTO : " + fireStationDTO.toString());
        FireStation fireStation = new FireStation();
        fireStation.setStation(fireStationDTO.getStation());
        fireStation.setStation(fireStationDTO.getStation());

        logger.debug("return " + fireStation.toString());
        return fireStation;
    }

    public List<FireStation> mapDtoToDomainFireStationList(List<FireStationDTO> fireStationDTOArrayList) {
        logger.debug("FireStationMapper mapDtoToDomainFireStationList");
        logger.debug("firesStationDTOArrayList : " + fireStationDTOArrayList);

        return fireStationDTOArrayList.stream().map(fireStationDTO -> mapDtoToDomainFireStation(fireStationDTO)).collect(Collectors.toList());
    }
}
