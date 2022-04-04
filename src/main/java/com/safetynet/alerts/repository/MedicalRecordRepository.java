package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.dto.MedicalRecordDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to serializing the medical records in DTO model object.
 */
@Repository
public class MedicalRecordRepository implements GetJsonData {

    private static final Logger logger = LogManager.getLogger(MedicalRecordRepository.class);

    private final List<MedicalRecordDTO> medicalRecordDTOList = new ArrayList<>();
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private List<String> medications;
    private List<String> allergies;

    @Autowired
    private MedicalRecordDTO medicalRecordDTO;

    /**
     * {@inheritDoc}
     *
     * @param medicalRecords <b>JsonNode</b> : Medical Records information.
     * @throws NullPointerException The application is exited.
     */
    @Override
    public void setModel(JsonNode medicalRecords) {
        logger.debug("MedicalRecordsRepository setModel");

        try {
            if (medicalRecords.isArray()) {
                for (JsonNode eachRecord : medicalRecords) {
                    logger.debug(eachRecord);

                    firstName = eachRecord.get("firstName").asText();
                    lastName = eachRecord.get("lastName").asText();
                    birthdate = LocalDate.parse(eachRecord.get("birthdate").asText(), format);
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


                    medicalRecordDTOList.add(new MedicalRecordDTO(firstName, lastName, birthdate, medications, allergies));
                }
            }
        } catch (NullPointerException e) {
            logger.error("Error on Json field name " + e);
            System.exit(1);
        }
        logger.debug(medicalRecordDTOList);
    }

    /**
     * Get all medical records in a list.
     *
     * @return The list of all MedicalRecordDTO objects.
     */
    public List<MedicalRecordDTO> getMedicalRecordList() {
        logger.debug("MedicalRecordRepository getMedicalRecordList");
        logger.debug("return " + medicalRecordDTOList);

        return medicalRecordDTOList;
    }

    /**
     * Add a new medical record.
     *
     * @param medicalRecordDTO <b>MedicalRecordDTO</b> : medical record to add.
     */
    public void addMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        logger.debug("MedicalRecordRepository addMedicalRecord");
        logger.debug("medicalRecordDTO : " + medicalRecordDTO.toString());

        medicalRecordDTOList.add(medicalRecordDTO);
    }

    /**
     * Delete a medical record.
     *
     * @param medicalRecordDTO <b>MedicalRecordDTO</b> : medical record to delete.
     */
    public void deleteMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        logger.debug("MedicalRecordRepository deleteMecicalRecord");
        logger.debug("medicalRecordDTO : " + medicalRecordDTO);

        medicalRecordDTOList.remove(medicalRecordDTO);
    }

}