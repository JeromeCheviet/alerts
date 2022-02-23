package com.safetynet.alerts.service;

import com.safetynet.alerts.model.application.ChildrenByAddressConstructor;
import com.safetynet.alerts.model.application.PersonByAddress;
import com.safetynet.alerts.model.application.PersonInfo;
import com.safetynet.alerts.model.core.Person;

import java.util.List;

public interface PersonService {

    boolean personExist(Person person);

    List<Person> findAll();
    List<Person> findByFirstName(String firstName);
    List<Person> findByAddress(String address);
    List<String> findEmailByCity(String city);
    PersonByAddress findPersonByAddress(String address);
    ChildrenByAddressConstructor findChildrenByAddress(String address);
    List<PersonInfo> findPersonInfo(String firstName, String lastName);
    List<Person> addPerson(Person person);
    boolean deletePerson(String firstName, String lastName);
    List<Person> updatePerson(Person person);
}
