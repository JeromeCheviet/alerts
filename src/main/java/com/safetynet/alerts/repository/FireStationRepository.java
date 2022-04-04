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

/**
 * Class to serializing the fire stations in DTO model object.
 */
@Repository
public class FireStationRepository implements GetJsonData {

    private static final Logger logger = LogManager.getLogger(FireStationRepository.class);

    private List<FireStationDTO> fireStationDTOList = new ArrayList<>();
    private String address;
    private int station;

    @Autowired
    private FireStationDTO fireStationDTO;

    /**
     * {@inheritDoc}
     *
     * @param fireStations <b>JsonNode</b> :  Fire stations information.
     * @throws NullPointerException The application is exited.
     */
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

    /**
     * Get all fire stations in a list.
     *
     * @return The list of all FireStationDTO objects.
     */
    public List<FireStationDTO> getFireStationList() {
        logger.debug("FireStationRepository getFireStationList");
        logger.debug("return " + fireStationDTOList);

        return fireStationDTOList;
    }

    /**
     * Add a new fire station.
     *
     * @param fireStationDTO <b>FireStationDTO</b> : Fire station to add
     */
    public void addFireStation(FireStationDTO fireStationDTO) {
        logger.debug("FireStationRepository addFireStation");
        logger.debug("fireStationDTO : " + fireStationDTO.toString());

        fireStationDTOList.add(fireStationDTO);
    }

    /**
     * Delete all fire stations with the same station number.
     *
     * @param stationNumber <b>int</b> : The station number to be deleting.
     */
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

    /**
     * Delete all fire stations with the same address
     *
     * @param address <b>String</b> : The address to be deleting.
     */
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

    /**
     * Delete the fire station with this address and this station number.
     *
     * @param address       <b>String</b> : the address.
     * @param stationNumber <b>int</b> : the station number.
     */
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
