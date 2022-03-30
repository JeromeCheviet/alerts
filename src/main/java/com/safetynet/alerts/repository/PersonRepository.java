package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.dto.PersonDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to serializing the persons in DTO model object.
 */
@Repository
public class PersonRepository implements GetJsonData {

    private static final Logger logger = LogManager.getLogger(PersonRepository.class);

    private List<PersonDTO> personDTOList = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int zip;
    private String phone;
    private String email;


    @Autowired
    private PersonDTO personDTO;

    /**
     * {@inheritDoc}
     *
     * @param persons <b>JsonNode</b> : Persons information.
     * @throws NullPointerException The application is exited.
     */
    @Override
    public void setModel(JsonNode persons) {
        logger.debug("PersonRepository setModel");

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


                    personDTOList.add(new PersonDTO(firstName, lastName, address, city, zip, phone, email));
                }
            }
        } catch (NullPointerException e) {
            logger.error("Error on Json field name " + e);
            System.exit(1);
        }
        logger.debug(personDTOList);
    }

    /**
     * Get all persons in a list
     *
     * @return The list of PersonDTO objects.
     */
    public List<PersonDTO> getPersonList() {
        logger.debug("PersonRepository getPersonList");
        logger.debug("return " + personDTOList);

        return personDTOList;
    }

    /**
     * Add new person.
     *
     * @param personDTO <b>PersonDTO</b> : person to add.
     */
    public void addPerson(PersonDTO personDTO) {
        logger.debug("PersonRepository addPerson");
        logger.debug("personDTO : " + personDTO.toString());

        personDTOList.add(personDTO);
    }

    /**
     * Delete a person
     *
     * @param personDTO <b>PersonDTO</b> : person to delete.
     */
    public void deletePerson(PersonDTO personDTO) {
        logger.debug("PersonRepository deletePerson");
        logger.debug("PersonDTO : " + personDTO.toString());

        personDTOList.remove(personDTO);
    }

}
