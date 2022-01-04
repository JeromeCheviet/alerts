package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.FireStation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//TODO Tests and JavaDoc
@Repository
public class FireStationRepository implements GetJsonData {

    private static Logger logger = LogManager.getLogger(FireStationRepository.class);
    List<String> fireStationList = new ArrayList<>();

    @Autowired
    private FireStation fireStation;

    @Override
    public void setModel(JsonNode fireStations) {
        logger.debug("Class FireStationRepository setModel");

        try {
            if (fireStations.isArray()) {
                for (JsonNode eachStation : fireStations) {
                    logger.debug(eachStation);
                    fireStation.setAddress(eachStation.get("address").asText());
                    fireStation.setStation(eachStation.get("station").asInt());

                    logger.debug(fireStation.toString());
                    fireStationList.add(fireStation.toString());
                }
            }

        } catch (NullPointerException e) {
            logger.error("Error on Json field name " + e);
            System.exit(1);
        }
        logger.debug(fireStationList);
    }

    public List<String> getFireStationList() {
        return fireStationList;
    }
}
