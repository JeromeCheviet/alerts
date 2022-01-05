package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationServiceImpl implements FireStationService {

    private static Logger logger = LogManager.getLogger(FireStationService.class);

    @Autowired
    private FireStationRepository fireStationRepository;

    public FireStationServiceImpl(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    @Override
    public List<FireStation> findAll() {
        logger.debug("FireStationService findAll");
        if (fireStationRepository.getFireStationList().isEmpty()) {
            logger.warn("Fire Stations list is empty");
        }
        return fireStationRepository.getFireStationList();
    }
}
