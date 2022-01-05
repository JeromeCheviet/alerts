package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonServiceImpl implements PersonService {

    private static Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        logger.debug("PersonService findAll");
        if (personRepository.getPersonList().isEmpty()) {
            logger.warn("Persons list is empty");
        }
        return personRepository.getPersonList();
    }

    @Override
    public List<Person> findByFirstName(String firstName) {
        logger.debug("PersonService findByFirstName");
        List<Person> personList = new ArrayList<>();
        for (Person person : personRepository.getPersonList()) {
            if (person.getFirstName().equals(firstName)) {
                personList.add(new Person(person.getFirstName(), person.getLastName(), person.getAddress(), person.getCity(), person.getZip(), person.getPhone(), person.getEmail()));
            }
        }
        return personList;
    }
}
