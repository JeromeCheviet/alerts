package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.dto.FireStationDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void addFireStation(FireStationDTO fireStationDTO ) {
        logger.debug("FireStationRepository addFireStation");
        logger.debug("fireStationDTO : " + fireStationDTO.toString());

        fireStationDTOList.add(fireStationDTO);
    }

    public void deleteMappingFireStationNumber(int stationNumber) {
        logger.debug("FireStationRepository deleteMappingFireStationNumber");
        logger.debug("stationNumber : " + stationNumber);

        Iterator<FireStationDTO> fireStationDTOIterator = getFireStationList().iterator();

        while (fireStationDTOIterator.hasNext()) {
            fireStationDTO = fireStationDTOIterator.next();
            if (fireStationDTO.getStation() == stationNumber) {
                fireStationDTOIterator.remove();
            }
        }
    }

    public void deleteMappingFireStationAddress(String address) {
        logger.debug("FireStationRepository deleteMappingFireStationAddress");
        logger.debug("address : " + address);

        Iterator<FireStationDTO> fireStationDTOIterator = fireStationDTOList.iterator();

        while (fireStationDTOIterator.hasNext()) {
            fireStationDTO = fireStationDTOIterator.next();
            if (fireStationDTO.getAddress().equals(address)) {
                fireStationDTOIterator.remove();
            }
        }
    }

    public void deleteFireStation(String address, int stationNumber) {
        logger.debug("FireStationRepository deleteFireStation");
        logger.debug("address : " + address + " | stationNumber : " + stationNumber);

        Iterator<FireStationDTO> fireStationDTOIterator = fireStationDTOList.iterator();

        while (fireStationDTOIterator.hasNext()) {
            fireStationDTO = fireStationDTOIterator.next();
            if (fireStationDTO.getAddress().equals(address) && fireStationDTO.getStation() == stationNumber) {
                fireStationDTOIterator.remove();
            }
        }
    }

}
