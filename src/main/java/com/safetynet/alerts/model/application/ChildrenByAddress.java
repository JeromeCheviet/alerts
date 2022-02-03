package com.safetynet.alerts.model.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


public class ChildrenByAddress {

    private static final Logger logger = LogManager.getLogger(ChildrenByAddress.class);

    private String firstName;
    private String lastName;
    private int age;

    public ChildrenByAddress(String firstName, String lastName, int age) {
        logger.debug("ChildrenByAddress constructor");
        logger.debug("firstName : " + firstName + " | lastName : " + lastName + " | age : " + age);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
