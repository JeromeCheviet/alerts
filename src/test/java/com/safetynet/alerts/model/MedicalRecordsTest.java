package com.safetynet.alerts.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordsTest {

    @Test
    public void createAMedicalRecords() {
        Map<String, String> medications = new HashMap<>();
        List<String> allergies = new ArrayList<>();

        medications.put("paracetamol", "1000g");
        allergies.add("pollen");
        allergies.add("arachid");

        MedicalRecords medicalRecords = new MedicalRecords(
                1,
                "Jérôme",
                "Cheviet",
                "1980/09/02",
                medications,
                allergies
        );

        assertEquals(medicalRecords.getId(), 1);
        assertEquals(medicalRecords.getFistName(), "Jérôme");
        assertEquals(medicalRecords.getBirthday(), "1980/09/02");
        assertEquals(medicalRecords.getMedication().toString(), "{paracetamol=1000g}");
        assertEquals(medicalRecords.getAllergies().toString(), "[pollen, arachid]");
    }
}