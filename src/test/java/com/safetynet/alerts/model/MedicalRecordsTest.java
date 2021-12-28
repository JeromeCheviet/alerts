package com.safetynet.alerts.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicalRecordsTest {

    private String actualFirstName = "Jérôme";
    private String actualLastName = "Cheviet";
    private String actualBirthdate = "01-01-2000";
    private List<String> actualMedications = new ArrayList<>();
    private List<String> actualAllergies = new ArrayList<>();

    @Test
    public void createAMedicalRecords() {

        MedicalRecords medicalRecords = new MedicalRecords();

        actualMedications.add("aznol:350mg");
        actualMedications.add("hydrapermazol:100mg");

        actualAllergies.add("peanut");
        actualAllergies.add("shellfish");
        actualAllergies.add("aznol");

        String actualToString = "MedicalRecords{" +
                "fistName='" + actualFirstName + '\'' +
                ", lastName='" + actualLastName + '\'' +
                ", birthday='" + actualBirthdate + '\'' +
                ", medication=" + actualMedications +
                ", allergies=" + actualAllergies +
                '}';

        medicalRecords.setFistName(actualFirstName);
        medicalRecords.setLastName(actualLastName);
        medicalRecords.setBirthdate(actualBirthdate);
        medicalRecords.setMedications(actualMedications);
        medicalRecords.setAllergies(actualAllergies);

        assertEquals(medicalRecords.getFistName(), actualFirstName);
        assertEquals(medicalRecords.getLastName(), actualLastName);
        assertEquals(medicalRecords.getBirthdate(), actualBirthdate);
        assertEquals(medicalRecords.getMedications(), actualMedications);
        assertEquals(medicalRecords.getAllergies(), actualAllergies);
        assertEquals(medicalRecords.toString(), actualToString);
    }
}