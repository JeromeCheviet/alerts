package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.application.CustomMessage;
import com.safetynet.alerts.model.core.MedicalRecord;
import com.safetynet.alerts.model.dto.MedicalRecordDTO;
import com.safetynet.alerts.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping(value = "/medicalRecord")
    public ResponseEntity<Object> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("POST new Medical Record : " + medicalRecord.toString());

        if (medicalRecordService.medicalRecordExist(medicalRecord)) {
            logger.info("Medical Record already exist");
            return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Medical Record already exist"), HttpStatus.CONFLICT);
        }
        logger.info("New Medical Record " + medicalRecord.toString() + " has been created");
        return new ResponseEntity<>(medicalRecordService.addMedicalRecord(medicalRecord), HttpStatus.CREATED);
    }

    @PutMapping(value = "/medicalRecord")
    public ResponseEntity<Object> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("PUT update Medical Record : " + medicalRecord);

        if (!medicalRecordService.medicalRecordExist(medicalRecord)) {
            logger.info("Medical Record not exist");
            return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Medical Record not exist"), HttpStatus.CONFLICT);
        }
        logger.info("Medical Record " + medicalRecord.toString() + " has been updated");
        return new ResponseEntity<>(medicalRecordService.updateMedicalRecord(medicalRecord), HttpStatus.OK);
    }

    @DeleteMapping(value = "/medicalRecord")
    public ResponseEntity<CustomMessage> deleteMedicalRecord(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        logger.info("DELETE medical record for firstName : " + firstName + " and lastName : " + lastName);

        boolean isDeleted = medicalRecordService.deleteMedicalRecord(firstName, lastName);
        logger.debug("isDeleted : " + isDeleted);
        if (isDeleted) {
            logger.info(firstName + " " + lastName + "'s medical record has been deleted");
            return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.OK.value(), firstName + " " + lastName + "'s medical record has been deleted"), HttpStatus.OK);
        }
        logger.info(firstName + " " + lastName + "'s medical record has not been deleted");
        return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), firstName + " " + lastName + "'s medical record has not been deleted"), HttpStatus.CONFLICT);
    }

    @GetMapping("/medicalrecords")
    public List<MedicalRecordDTO> listMedicalRecords() {
        logger.info("Ask GET medicalrecords");
        logger.info("Response : " + medicalRecordService.findAll());
        return medicalRecordService.findAll();
    }
}
