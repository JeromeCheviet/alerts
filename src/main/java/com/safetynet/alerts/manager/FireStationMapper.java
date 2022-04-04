package com.safetynet.alerts.manager;

import com.safetynet.alerts.model.core.FireStation;
import com.safetynet.alerts.model.dto.FireStationDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class which maps objects from the FireStationDTO class to the FireStation class or reverse.
 */
@Component
public class FireStationMapper {

    private static final Logger logger = LogManager.getLogger(FireStationMapper.class);

    /**
     * Map a FireStationDTO object to a FireStation object.
     *
     * @param fireStationDTO <b>FireStationDTO</b> : One fire station from DTO object.
     * @return a FireStation object.
     */
    public FireStation mapDtoToDomainFireStation(FireStationDTO fireStationDTO) {
        logger.debug("FireStationMapper mapDtoToDomainFireStation");
        logger.debug("fireStationDTO : " + fireStationDTO.toString());
        FireStation fireStation = new FireStation();
        fireStation.setAddress(fireStationDTO.getAddress());
        fireStation.setStation(fireStationDTO.getStation());

        logger.debug("return " + fireStation.toString());
        return fireStation;
    }

    /**
     * Map a list of FireStationDTO  object to a list of FireStation object.
     *
     * @param fireStationDTOArrayList <b>List</b> : list of FireStationDTO object.
     * @return a list of FireStation object
     */
    public List<FireStation> mapDtoToDomainFireStationList(List<FireStationDTO> fireStationDTOArrayList) {
        logger.debug("FireStationMapper mapDtoToDomainFireStationList");
        logger.debug("firesStationDTOArrayList : " + fireStationDTOArrayList);

        return fireStationDTOArrayList.stream().map(fireStationDTO -> mapDtoToDomainFireStation(fireStationDTO)).collect(Collectors.toList());
    }

    /**
     * Map a FireStation object to FireStationDTO object.
     *
     * @param fireStation <b>FireStation</b> : One fire station from domain object.
     * @return a FireStationDTO object.
     */
    public FireStationDTO mapDomainFireStationToDto(FireStation fireStation) {
        logger.debug("FireStationMapper mapDtoToDomainFireStation");
        logger.debug("fireStationDTO : " + fireStation.toString());
        FireStationDTO fireStationDTO = new FireStationDTO();
        fireStationDTO.setAddress(fireStation.getAddress());
        fireStationDTO.setStation(fireStation.getStation());

        logger.debug("return " + fireStationDTO.toString());
        return fireStationDTO;
    }
}
