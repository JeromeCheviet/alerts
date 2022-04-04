package com.safetynet.alerts.model.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Class used to manipulate this medical record object instead of medical record DTO object.
 * <p>
 * The objects are sent by MedicalRecordMapper class.
 */
@Component
public class MedicalRecord {

    private static final Logger logger = LogManager.getLogger(MedicalRecord.class);

    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private List<String> medications;
    private List<String> allergies;

    public MedicalRecord() {
    }

    public MedicalRecord(String firstName, String lastName, LocalDate birthdate, List<String> medications, List<String> allergies) {
        logger.debug("MedicalRecord constructor");
        logger.debug("firstname : " + firstName + " | lastName : " + lastName + " | birthdate : " + birthdate + " | medications : " + medications + " | allergies : " + allergies);

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fistName) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    @JsonFormat(pattern = "MM/dd/yyyy")
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "MedicalRecords{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", birthdate='" + birthdate + '\'' + ", medication=" + medications + ", allergies=" + allergies + '}';
    }
}