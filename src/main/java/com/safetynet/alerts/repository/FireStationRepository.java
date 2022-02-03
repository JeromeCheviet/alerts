package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.DTO.FireStationDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//TODO Tests and JavaDoc
@Repository
public class FireStationRepository implements GetJsonData {

    private static final Logger logger = LogManager.getLogger(FireStationRepository.class);

    private List<FireStationDTO> fireStationDTOList = new ArrayList<>();
    private String address;
    private int station;

    @Autowired
    private FireStationDTO fireStationDTO;

    @Override
    public void setModel(JsonNode fireStations) {
        logger.debug("Class FireStationRepository setModel");

        try {
            if (fireStations.isArray()) {
                for (JsonNode eachStation : fireStations) {
                    logger.debug(eachStation);

                    address = eachStation.get("address").asText();
                    station = eachStation.get("station").asInt();

                    fireStationDTO.setAddress(address);
                    fireStationDTO.setStation(station);

                    logger.debug(fireStationDTO.toString());
                    fireStationDTOList.add(new FireStationDTO(address, station));
                }
            }

        } catch (NullPointerException e) {
            logger.error("Error on Json field name " + e);
            System.exit(1);
        }
        logger.debug(fireStationDTOList);
    }

    public List<FireStationDTO> getFireStationList() {
        logger.debug("FireStationRepository getFireStationList");
        logger.debug("return " + fireStationDTOList);
        return fireStationDTOList;
    }
}
