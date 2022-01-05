package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireStationController {

    private Logger logger = LogManager.getLogger(FireStationController.class);

    @Autowired
    private FireStationService fireStationService;

    @GetMapping("/firestations")
    public List<FireStation> listFireStation() {
        logger.info("Ask GET firestations");
        logger.info("Response : " + fireStationService.findAll());
        return fireStationService.findAll();
    }

}
