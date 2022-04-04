package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.application.ChildrenByAddressConstructor;
import com.safetynet.alerts.model.application.CustomMessage;
import com.safetynet.alerts.model.application.PersonByAddress;
import com.safetynet.alerts.model.application.PersonInfo;
import com.safetynet.alerts.model.core.Person;
import com.safetynet.alerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class grouping person APIs.
 */
@RestController
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    /**
     * POST-like API for creating a person.
     *
     * @param person <b>Person</b> : object to create.
     * @return the list of all persons.
     */
    @PostMapping(value = "/person")
    public ResponseEntity<Object> createPerson(@RequestBody Person person) {
        logger.info("POST new Person : " + person.toString());

        if (personService.personExist(person)) {
            logger.info("Person already exist");
            return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Person already exist"), HttpStatus.CONFLICT);
        }
        logger.info("New Person " + person + " has been created");
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
    }

    /**
     * PUT-like API for updating a person.
     *
     * @param person <b>Person</b> : person to update.
     * @return the list of all persons.
     */
    @PutMapping(value = "/person")
    public ResponseEntity<Object> updatePerson(@RequestBody Person person) {
        logger.info("PUT update person : " + person.toString());

        if (!personService.personExist(person)) {
            logger.info("Person not exist");
            return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Person not exist"), HttpStatus.CONFLICT);
        }
        logger.info("Person " + person.toString() + " has been updated");
        return new ResponseEntity<>(personService.updatePerson(person), HttpStatus.OK);
    }

    /**
     * DELETE-like API for deleting a person.
     *
     * @param firstName <b>String</b>
     * @param lastName  <b>String</b>
     * @return the HTTP status code of the request result.
     */
    @DeleteMapping(value = "/person")
    public ResponseEntity<CustomMessage> deletePerson(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        logger.info("DELETE person with firstName : " + firstName + " and lastName : " + lastName);

        boolean isDeleted = personService.deletePerson(firstName, lastName);
        logger.debug("isDeleted : " + isDeleted);
        if (isDeleted) {
            logger.info(firstName + " " + lastName + " has been deleted");
            return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.OK.value(), firstName + " " + lastName + " has been deleted"), HttpStatus.OK);
        }
        logger.info(firstName + " " + lastName + " has not been deleted");
        return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Person not delete"), HttpStatus.CONFLICT);
    }

    /**
     * GET-like API to have all persons
     *
     * @return all persons in a list.
     */
    @GetMapping("/persons")
    public List<Person> listPersons() {
        logger.info("Ask Get persons");
        logger.info("Response : " + personService.findAll());
        return personService.findAll();
    }


    /**
     * GET-like API to have email from all person living in the city.
     *
     * @param city <b>String</b> : The city name.
     * @return a list of all email from person livieng in the city.
     */
    @GetMapping(value = "communityEmail", produces = "application/json")
    public ResponseEntity<List<String>> getCommunityEmail(@RequestParam(value = "city") String city) {
        logger.info("Ask Get all email from person to live in city : " + city);
        List<String> emails = personService.findEmailByCity(city);
        logger.info("Response : " + emails);
        if (emails.isEmpty()) {
            return new ResponseEntity<>(emails, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(emails, HttpStatus.OK);
        }
    }

    /**
     * GET-like API to have the first name, last name, phone number, age, medications and allergies for each people living at the same address and the number of the fire station number that it must appear.
     *
     * @param address <b>String</b>
     * @return the list of person living at this address and the fire station number which cover it.
     */
    @GetMapping("/fire")
    public ResponseEntity<MappingJacksonValue> getPersonByAddress(@RequestParam(value = "address") String address) {
        logger.info("Ask Get fire?address=" + address);
        PersonByAddress personByAddress = personService.findPersonByAddress(address);
        logger.info("Response : " + personByAddress);

        SimpleBeanPropertyFilter addressCityZipEmailFilter = SimpleBeanPropertyFilter.serializeAllExcept("address", "city", "zip", "email");
        FilterProvider filters = new SimpleFilterProvider().addFilter("personInfoFilter", addressCityZipEmailFilter);
        MappingJacksonValue personInfoFiters = new MappingJacksonValue(personByAddress);
        personInfoFiters.setFilters(filters);

        if (personByAddress.getPersonInfoList() == null) {
            return new ResponseEntity<>(personInfoFiters, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(personInfoFiters, HttpStatus.OK);
        }

    }

    /**
     * GET-like API to have the first name, last name and age living at the same address, and the first name and last name from each adult living with them.
     *
     * @param address <b>String</b>.
     * @return the list of all person having 18 years old or younger living at the same address.
     */
    @GetMapping(value = "/childAlert", produces = "application/json")
    public ResponseEntity<ChildrenByAddressConstructor> getChildrenByAddress(@RequestParam(value = "address") String address) {
        logger.info("Ask Get childAlert?address=" + address);
        ChildrenByAddressConstructor childrenByAddressConstructor = personService.findChildrenByAddress(address);
        logger.info("Response : " + childrenByAddressConstructor);

        if (childrenByAddressConstructor.getChildrenByAddressList() == null) {
            return new ResponseEntity<>(childrenByAddressConstructor, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(childrenByAddressConstructor, HttpStatus.OK);
        }

    }

    /**
     * GET-like API to have the first name, last name, address, age, email, medications and allergies from a specific person.
     *
     * @param firstName <b>String</b>
     * @param lastName  <b>String</b>
     * @return The list of all people with the first name and last name requested.
     */
    @GetMapping("/personInfo")
    public ResponseEntity<MappingJacksonValue> getPersonInfo(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        logger.info("Ask Get personInfo?firstName=" + firstName + "&lastName=" + lastName);
        List<PersonInfo> personInfoList = personService.findPersonInfo(firstName, lastName);
        logger.info("Response : " + personInfoList);

        SimpleBeanPropertyFilter phoneZipCityFilter = SimpleBeanPropertyFilter.serializeAllExcept("phone", "zip", "city");
        FilterProvider filters = new SimpleFilterProvider().addFilter("personInfoFilter", phoneZipCityFilter);
        MappingJacksonValue personInfoFilters = new MappingJacksonValue(personInfoList);
        personInfoFilters.setFilters(filters);

        if (personInfoList.isEmpty()) {
            return new ResponseEntity<>(personInfoFilters, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(personInfoFilters, HttpStatus.OK);
        }

    }

}
