package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.DTO.MedicalRecordDTO;
import com.safetynet.alerts.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/medicalrecords")
    public List<MedicalRecordDTO> listMedicalRecords() {
        logger.info("Ask GET medicalrecords");
        logger.info("Response : " + medicalRecordService.findAll());
        return medicalRecordService.findAll();
    }
}
