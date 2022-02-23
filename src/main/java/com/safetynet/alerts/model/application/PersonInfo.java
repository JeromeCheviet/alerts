package com.safetynet.alerts.model.application;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@JsonFilter("personInfoFilter")
@Component
public class PersonInfo {

    private static final Logger logger = LogManager.getLogger(PersonInfo.class);

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int zip;
    private int age;
    private String email;
    private String phone;
    private List<String> medicament;
    private List<String> allergies;

    public PersonInfo() {

    }

    public PersonInfo(String firstName, String lastName, String address, String city, int zip, int age, String email, String phone, List<String> medicament, List<String> allergies) {
        logger.debug("PersonInfo constructor");
        logger.debug("firstName : " + firstName + " | lastName : " + lastName + " | address : " + address + " | city : " + city + " | zip : " + zip + " | age : " + age + " | email : " + email + " | phone " + phone + " | medicament : " + medicament + " | allergies : " + allergies);

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.medicament = medicament;
        this.allergies = allergies;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getMedicament() {
        return medicament;
    }

    public void setMedicament(List<String> medicament) {
        this.medicament = medicament;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", medicament=" + medicament +
                ", allergies=" + allergies +
                '}';
    }
}
