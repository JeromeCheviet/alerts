package com.safetynet.alerts.model.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Class used to manipulate this person object instead of person DTO object.
 * <p>
 * The objects are sent by PersonMapper class.
 */
@Component
public class Person {

    private static final Logger logger = LogManager.getLogger(Person.class);

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int zip;
    private String phone;
    private String email;

    public Person() {
    }

    public Person(String firstName, String lastName, String address, String city, int zip, String phone, String email) {
        logger.debug("Person constructor");
        logger.debug("firstName : " + firstName + " | lastName : " + lastName + " | address : " + address + " | city : " + city + " | zip : " + zip + " | phone : " + phone + " | email : " + email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnoreProperties(value = {"firstName", "lastName", "address", "city", "zip", "phone"})
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + email + '\'' +
                '}';
    }
}