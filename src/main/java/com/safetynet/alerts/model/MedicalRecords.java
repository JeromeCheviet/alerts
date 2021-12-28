package com.safetynet.alerts.model;

import java.util.List;
import java.util.Map;

public class MedicalRecords {


    private String fistName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
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
        return "MedicalRecords{" +
                "fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthdate + '\'' +
                ", medication=" + medications +
                ", allergies=" + allergies +
                '}';
    }
}
