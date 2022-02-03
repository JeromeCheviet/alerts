package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.application.ChildrenByAddressConstructor;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;
    @Autowired
    private ChildrenByAddressConstructor childrenByAddressConstructor;

    @GetMapping("/persons")
    public List<Person> listPersons() {
        logger.info("Ask Get persons");
        logger.info("Response : " + personService.findAll());
        return personService.findAll();
    }

    @GetMapping(value = "/persons/{firstName}")
    public List<Person> returnOnePerson(@PathVariable String firstName) {
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        logger.info("Ask GET one person with firstName = " + firstName);
        logger.info("Response : " + personService.findByFirstName(firstName));
        return personService.findByFirstName(firstName);
    }

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

    @GetMapping("/fire")
    public ResponseEntity<MappingJacksonValue> getPersonByAddress(@RequestParam(value = "address") String address) {
        logger.info("Ask Get fire?address=" + address);
        PersonByAddress personByAddress = personService.findPersonByAddress(address);
        logger.info("Response : " + personByAddress);

        SimpleBeanPropertyFilter addressCityZipEmailFilter = SimpleBeanPropertyFilter.serializeAllExcept("address", "city", "zip", "email");
        FilterProvider filters = new SimpleFilterProvider().addFilter("personInfoFilter", addressCityZipEmailFilter);
        MappingJacksonValue personInfoFiters = new MappingJacksonValue(personByAddress);
        personInfoFiters.setFilters(filters);

        if (personByAddress.toString().isEmpty()) {
            return new ResponseEntity<>(personInfoFiters, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(personInfoFiters, HttpStatus.OK);
        }

    }

    @GetMapping(value = "/childAlert", produces = "application/json")
    public ResponseEntity <ChildrenByAddressConstructor> getChildrenByAddress(@RequestParam(value = "address") String address) {
        logger.info("Ask Get childAlert?address=" + address);
        childrenByAddressConstructor = personService.findChildrenByAddress(address);
        logger.info("Response : " + childrenByAddressConstructor);

        if (childrenByAddressConstructor.toString().isEmpty()) {
            return new ResponseEntity<>(childrenByAddressConstructor, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(childrenByAddressConstructor, HttpStatus.OK);
        }

    }

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
