package com.safetynet.alerts.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicalRecordTest {

    private String actualFirstName = "Jérôme";
    private String actualLastName = "Cheviet";
    private String actualBirthdate = "01-01-2000";
    private List<String> actualMedications = new ArrayList<>();
    private List<String> actualAllergies = new ArrayList<>();

    @Test
    public void createAMedicalRecords() {

        MedicalRecord medicalRecords = new MedicalRecord();

        actualMedications.add("aznol:350mg");
        actualMedications.add("hydrapermazol:100mg");

        actualAllergies.add("peanut");
        actualAllergies.add("shellfish");
        actualAllergies.add("aznol");

        String actualToString = "MedicalRecords{" +
                "firstName='" + actualFirstName + '\'' +
                ", lastName='" + actualLastName + '\'' +
                ", birthday='" + actualBirthdate + '\'' +
                ", medication=" + actualMedications +
                ", allergies=" + actualAllergies +
                '}';

        medicalRecords.setFirstName(actualFirstName);
        medicalRecords.setLastName(actualLastName);
        medicalRecords.setBirthdate(actualBirthdate);
        medicalRecords.setMedications(actualMedications);
        medicalRecords.setAllergies(actualAllergies);

        assertEquals(medicalRecords.getFirstName(), actualFirstName);
        assertEquals(medicalRecords.getLastName(), actualLastName);
        assertEquals(medicalRecords.getBirthdate(), actualBirthdate);
        assertEquals(medicalRecords.getMedications(), actualMedications);
        assertEquals(medicalRecords.getAllergies(), actualAllergies);
        assertEquals(medicalRecords.toString(), actualToString);
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
                ", birthday='" + actualBirthdate + '\'' +
                ", medication=" + actualMedications +
                ", allergies=" + actualAllergies +
                '}';

        MedicalRecord medicalRecords = new MedicalRecord(
                actualFirstName,
                actualLastName,
                actualBirthdate,
                actualMedications,
                actualAllergies
        );

        assertEquals(medicalRecords.getFirstName(), actualFirstName);
        assertEquals(medicalRecords.getLastName(), actualLastName);
        assertEquals(medicalRecords.getBirthdate(), actualBirthdate);
        assertEquals(medicalRecords.getMedications(), actualMedications);
        assertEquals(medicalRecords.getAllergies(), actualAllergies);
        assertEquals(medicalRecords.toString(), actualToString);
    }
}