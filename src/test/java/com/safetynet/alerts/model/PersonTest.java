package com.safetynet.alerts.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonTest {

    private String actualFirstName = "Jérôme";
    private String actualLastName = "Cheviet";
    private String actualAdress = "1 rue du Puit";
    private String actualCity = "Paris";
    private int actualZip = 75000;
    private String actualPhone = "06.07.08.09.10";
    private String actualEmail = "jcheviet@mail.me";
    private String actualToString = "Person{" +
            "firstName='" + actualFirstName + '\'' +
            ", lastName='" + actualLastName + '\'' +
            ", address='" + actualAdress + '\'' +
            ", city='" + actualCity + '\'' +
            ", zip='" + actualZip + '\'' +
            ", phone='" + actualPhone + '\'' +
            ", mail='" + actualEmail + '\'' +
            '}';


    @Test
    public void createAPerson() {

        Person person = new Person();

        person.setFirstName(actualFirstName);
        person.setLastName(actualLastName);
        person.setAddress(actualAdress);
        person.setCity(actualCity);
        person.setZip(actualZip);
        person.setPhone(actualPhone);
        person.setEmail(actualEmail);


        assertEquals(person.getFirstName(), actualFirstName);
        assertEquals(person.getLastName(), actualLastName);
        assertEquals(person.getAddress(), actualAdress);
        assertEquals(person.getCity(), actualCity);
        assertEquals(person.getZip(), actualZip);
        assertEquals(person.getPhone(), actualPhone);
        assertEquals(person.getEmail(), actualEmail);
        assertEquals(person.toString(), actualToString);
    }
}
