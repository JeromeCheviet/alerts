package com.safetynet.alerts.model;

import java.util.List;
import java.util.Map;

public class MedicalRecords {
    private int id;
    private String fistName;
    private String lastName;
    private String birthday;
    private Map<String, String> medication;
    private List<String> allergies;

    public MedicalRecords(int id, String fistName, String lastName, String birthday, Map<String, String> medication, List<String> allergies) {
        this.id = id;
        this.fistName = fistName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.medication = medication;
        this.allergies = allergies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Map<String, String> getMedication() {
        return medication;
    }

    public void setMedication(Map<String, String> medication) {
        this.medication = medication;
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
                "id=" + id +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", medication=" + medication +
                ", allergies=" + allergies +
                '}';
    }
}
