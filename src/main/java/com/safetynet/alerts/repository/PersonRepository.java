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
    private List<String> personList = new ArrayList<>();

    @Autowired
    private Person person;

    @Override
    public void setModel(JsonNode persons) {
        logger.debug("Class PersonRepository setModel");

        try {
            if (persons.isArray()) {
                for (JsonNode eachPerson : persons) {
                    logger.debug(eachPerson);
                    person.setFirstName(eachPerson.get("firstName").asText());
                    person.setLastName(eachPerson.get("lastName").asText());
                    person.setAddress(eachPerson.get("address").asText());
                    person.setCity(eachPerson.get("city").asText());
                    person.setZip(eachPerson.get("zip").asInt());
                    person.setPhone(eachPerson.get("phone").asText());
                    person.setEmail(eachPerson.get("email").asText());

                    logger.debug(person.toString());
                    personList.add(person.toString());
                }
            }
        } catch (NullPointerException e) {
            logger.error("Error on Json field name " + e);
            System.exit(1);
        }
        logger.debug(personList);
    }

    public List<String> getPersonList() {
        return personList;
    }
}
