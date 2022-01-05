package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//TODO Tests and JavaDoc
@Repository
public class PersonRepository implements GetJsonData {

    private static Logger logger = LogManager.getLogger(PersonRepository.class);
    private List<Person> personList = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int zip;
    private String phone;
    private String email;


    @Autowired
    private Person person;

    @Override
    public void setModel(JsonNode persons) {
        logger.debug("Class PersonRepository setModel");

        try {
            if (persons.isArray()) {
                for (JsonNode eachPerson : persons) {
                    logger.debug(eachPerson);

                    firstName = eachPerson.get("firstName").asText();
                    lastName = eachPerson.get("lastName").asText();
                    address = eachPerson.get("address").asText();
                    city = eachPerson.get("city").asText();
                    zip = eachPerson.get("zip").asInt();
                    phone = eachPerson.get("phone").asText();
                    email = eachPerson.get("email").asText();

                    person.setFirstName(firstName);
                    person.setLastName(lastName);
                    person.setAddress(address);
                    person.setCity(city);
                    person.setZip(zip);
                    person.setPhone(phone);
                    person.setEmail(email);

                    logger.debug(person.toString());
                    personList.add(new Person(firstName, lastName, address, city, zip, phone, email));
                }
            }
        } catch (NullPointerException e) {
            logger.error("Error on Json field name " + e);
            System.exit(1);
        }
        logger.debug(personList);
    }

    public List<Person> getPersonList() {
        return personList;
    }
}
