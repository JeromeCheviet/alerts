package com.safetynet.alerts.model;

import com.safetynet.alerts.model.DTO.PersonDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonDTOTest {

    private String actualFirstName = "Jérôme";
    private String actualLastName = "Cheviet";
    private String actualAdress = "1 rue du Puit";
    private String actualCity = "Paris";
    private int actualZip = 75000;
    private String actualPhone = "06.07.08.09.10";
    private String actualEmail = "jcheviet@mail.me";
    private String actualToString = "PersonDTO{" +
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

        PersonDTO personDTO = new PersonDTO();

        personDTO.setFirstName(actualFirstName);
        personDTO.setLastName(actualLastName);
        personDTO.setAddress(actualAdress);
        personDTO.setCity(actualCity);
        personDTO.setZip(actualZip);
        personDTO.setPhone(actualPhone);
        personDTO.setEmail(actualEmail);


        assertEquals(personDTO.getFirstName(), actualFirstName);
        assertEquals(personDTO.getLastName(), actualLastName);
        assertEquals(personDTO.getAddress(), actualAdress);
        assertEquals(personDTO.getCity(), actualCity);
        assertEquals(personDTO.getZip(), actualZip);
        assertEquals(personDTO.getPhone(), actualPhone);
        assertEquals(personDTO.getEmail(), actualEmail);
        assertEquals(personDTO.toString(), actualToString);
    }

    @Test
    public void createAPersonWithConstructor() {
        PersonDTO personDTO = new PersonDTO(
                actualFirstName,
                actualLastName,
                actualAdress,
                actualCity,
                actualZip,
                actualPhone,
                actualEmail
        );

        assertEquals(personDTO.getFirstName(), actualFirstName);
        assertEquals(personDTO.getLastName(), actualLastName);
        assertEquals(personDTO.getAddress(), actualAdress);
        assertEquals(personDTO.getCity(), actualCity);
        assertEquals(personDTO.getZip(), actualZip);
        assertEquals(personDTO.getPhone(), actualPhone);
        assertEquals(personDTO.getEmail(), actualEmail);
        assertEquals(personDTO.toString(), actualToString);
    }

}
