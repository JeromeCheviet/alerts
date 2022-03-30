package com.safetynet.alerts.service;

import com.safetynet.alerts.model.application.ChildrenByAddressConstructor;
import com.safetynet.alerts.model.application.PersonByAddress;
import com.safetynet.alerts.model.application.PersonInfo;
import com.safetynet.alerts.model.core.Person;

import java.util.List;

/**
 * Interface linked to the management of persons.
 */
public interface PersonService {

    /**
     * Check if a person existing.
     *
     * @param person <b>Person</b> object.
     * @return a <i>boolean</i>, true if existing, or false if not.
     */
    boolean personExist(Person person);

    /**
     * Find all persons
     * @return a list of <i>Peron</i>
     */
    List<Person> findAll();

    /**
     * Find persons with the same firstname
     * @param firstName <b>String</b> : the person's first name.
     * @return a list of <i>Person</i> with this first name.
     */
    List<Person> findByFirstName(String firstName);

    /**
     * Find all persons living at the same address.
     * @param address <b>String</b> : address to search
     * @return a list of <i>Person</i> objects.
     */
    List<Person> findByAddress(String address);

    /**
     * Find emails from all persons living in the same city.
     * @param city <b>String</b> : city name.
     * @return list of emails in <i>String</i> format.
     */
    List<String> findEmailByCity(String city);

    /**
     * Find all person's information living at the same address and fire station number that depend on it.
     * @param address <b>String</b> : address to search
     * @return <i>PersonByAddress</i> object.
     */
    PersonByAddress findPersonByAddress(String address);

    /**
     * Find all children and adults living at the same address.
     * @param address <b>String</b> : address to search
     * @return <i>ChildreByAddressConstructor</i> object.
     */
    ChildrenByAddressConstructor findChildrenByAddress(String address);

    /**
     * Find all person's information with the same first name and the same last name.
     * @param firstName <b>String</b> : person's first name.
     * @param lastName <b>String</b> : person's last name.
     * @return list of <i>PersonInfo</i> object.
     */
    List<PersonInfo> findPersonInfo(String firstName, String lastName);

    /**
     * Adding a person.
     * @param person <b>Person</b> : a new person to add
     * @return all person in a list.
     */
    List<Person> addPerson(Person person);

    /**
     * Deleting a person.
     * @param firstName <b>String</b> : person's first name to delete
     * @param lastName <b>String</b> : person's last name to delete
     * @return <b>true</b> if delete completed or <b>false</b> if not.
     */
    boolean deletePerson(String firstName, String lastName);

    /**
     * Updating an existing person.
     * @param person <b>Person</b> : object with updates information.
     * @return all persons in a list.
     */
    List<Person> updatePerson(Person person);
}
