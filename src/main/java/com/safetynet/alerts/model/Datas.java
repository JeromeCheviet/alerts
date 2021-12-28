package com.safetynet.alerts.model;

import java.util.List;

public class Datas {

    private List<Person> persons;
    private List<FireStations> firestations;
    private List<MedicalRecords> medicalrecords;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<FireStations> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<FireStations> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecords> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
