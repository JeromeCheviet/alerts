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
    private List<MedicalRecord> medicalRecordList = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    @Autowired
    private MedicalRecord medicalRecord;

    @Override
    public void setModel(JsonNode medicalRecords) {
        logger.debug("Class MedicalRecordsRepository setModel");

        try {
            if (medicalRecords.isArray()) {
                for (JsonNode eachRecord : medicalRecords) {
                    logger.debug(eachRecord);

                    firstName = eachRecord.get("firstName").asText();
                    lastName = eachRecord.get("lastName").asText();
                    birthdate = eachRecord.get("birthdate").asText();
                    medications = new ArrayList<>();
                    if (eachRecord.get("medications").isArray()) {
                        for (JsonNode eachMedication : eachRecord.get("medications")) {
                            medications.add(eachMedication.asText());
                        }
                    }
                    allergies = new ArrayList<>();
                    if (eachRecord.get("allergies").isArray()) {
                        for (JsonNode eachAllergy : eachRecord.get("allergies")) {
                            allergies.add(eachAllergy.asText());
                        }
                    }


                    medicalRecord.setFirstName(firstName);
                    medicalRecord.setLastName(lastName);
                    medicalRecord.setBirthdate(birthdate);
                    medicalRecord.setMedications(medications);
                    medicalRecord.setAllergies(allergies);

                    logger.debug(medicalRecord.toString());
                    medicalRecordList.add(new MedicalRecord(firstName, lastName, birthdate, medications, allergies));
                }
            }
        } catch (NullPointerException e) {
            logger.error("Error on Json field name " + e);
            System.exit(1);
        }
        logger.debug(medicalRecordList);
    }

    public List<MedicalRecord> getMedicalRecordList() {
        return medicalRecordList;
    }

}