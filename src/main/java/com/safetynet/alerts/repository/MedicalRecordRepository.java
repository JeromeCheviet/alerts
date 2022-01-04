package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//TODO Tests and JavaDoc

@Repository
public class MedicalRecordRepository implements GetJsonData {

    private static Logger logger = LogManager.getLogger(MedicalRecordRepository.class);
    private List<String> medicalRecordList = new ArrayList<>();

    @Autowired
    private MedicalRecord medicalRecord;

    @Override
    public void setModel(JsonNode medicalRecords) {
        logger.debug("Class MedicalRecordsRepository setModel");

        try {
            if (medicalRecords.isArray()) {
                for (JsonNode eachRecord : medicalRecords) {
                    logger.debug(eachRecord);

                    List<String> medication = new ArrayList<>();
                    if (eachRecord.get("medications").isArray()) {
                        for (JsonNode eachMedication : eachRecord.get("medications")) {
                            medication.add(eachMedication.asText());
                        }
                    }

                    List<String> allergy = new ArrayList<>();
                    if (eachRecord.get("allergies").isArray()) {
                        for (JsonNode eachAllergy : eachRecord.get("allergies")) {
                            allergy.add(eachAllergy.asText());
                        }
                    }

                    medicalRecord.setFirstName(eachRecord.get("firstName").asText());
                    medicalRecord.setLastName(eachRecord.get("lastName").asText());
                    medicalRecord.setBirthdate(eachRecord.get("birthdate").asText());
                    medicalRecord.setMedications(medication);
                    medicalRecord.setAllergies(allergy);

                    logger.debug(medicalRecord.toString());
                    medicalRecordList.add(medicalRecord.toString());
                }
            }
        } catch (NullPointerException e) {
            logger.error("Error on Json field name " + e);
            System.exit(1);
        }
        logger.debug(medicalRecordList);
    }

    public List<String> getMedicalRecordList() {
        return medicalRecordList;
    }

}