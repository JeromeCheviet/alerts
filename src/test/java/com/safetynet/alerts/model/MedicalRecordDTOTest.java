package com.safetynet.alerts.model;

import com.safetynet.alerts.model.DTO.MedicalRecordDTO;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicalRecordDTOTest {

    private String actualFirstName = "Jérôme";
    private String actualLastName = "Cheviet";
    //private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private String birthday = "12/25/2000";
    private LocalDate actualBirthdate = LocalDate.parse(birthday, format);
    private List<String> actualMedications = new ArrayList<>();
    private List<String> actualAllergies = new ArrayList<>();

    MedicalRecordDTOTest() throws ParseException {
    }

    @Test
    public void createAMedicalRecords() {

        MedicalRecordDTO medicalRecordsDTO = new MedicalRecordDTO();

        actualMedications.add("aznol:350mg");
        actualMedications.add("hydrapermazol:100mg");

        actualAllergies.add("peanut");
        actualAllergies.add("shellfish");
        actualAllergies.add("aznol");

        String actualToString = "MedicalRecords{" +
                "firstName='" + actualFirstName + '\'' +
                ", lastName='" + actualLastName + '\'' +
                ", birthdate='" + actualBirthdate + '\'' +
                ", medication=" + actualMedications +
                ", allergies=" + actualAllergies +
                '}';

        medicalRecordsDTO.setFirstName(actualFirstName);
        medicalRecordsDTO.setLastName(actualLastName);
        medicalRecordsDTO.setBirthdate(actualBirthdate);
        medicalRecordsDTO.setMedications(actualMedications);
        medicalRecordsDTO.setAllergies(actualAllergies);

        assertEquals(medicalRecordsDTO.getFirstName(), actualFirstName);
        assertEquals(medicalRecordsDTO.getLastName(), actualLastName);
        assertEquals(medicalRecordsDTO.getBirthdate(), actualBirthdate);
        assertEquals(medicalRecordsDTO.getMedications(), actualMedications);
        assertEquals(medicalRecordsDTO.getAllergies(), actualAllergies);
        assertEquals(medicalRecordsDTO.toString(), actualToString);
    }

    @Test
    public void createMedicalRecordWithConstructor() {
        actualMedications.add("aznol:350mg");
        actualMedications.add("hydrapermazol:100mg");

        actualAllergies.add("peanut");
        actualAllergies.add("shellfish");
        actualAllergies.add("aznol");

        String actualToString = "MedicalRecords{" +
                "firstName='" + actualFirstName + '\'' +
                ", lastName='" + actualLastName + '\'' +
                ", birthdate='" + actualBirthdate + '\'' +
                ", medication=" + actualMedications +
                ", allergies=" + actualAllergies +
                '}';

        MedicalRecordDTO medicalRecordsDTO = new MedicalRecordDTO(
                actualFirstName,
                actualLastName,
                actualBirthdate,
                actualMedications,
                actualAllergies
        );

        assertEquals(medicalRecordsDTO.getFirstName(), actualFirstName);
        assertEquals(medicalRecordsDTO.getLastName(), actualLastName);
        assertEquals(medicalRecordsDTO.getBirthdate(), actualBirthdate);
        assertEquals(medicalRecordsDTO.getMedications(), actualMedications);
        assertEquals(medicalRecordsDTO.getAllergies(), actualAllergies);
        assertEquals(medicalRecordsDTO.toString(), actualToString);
    }
}