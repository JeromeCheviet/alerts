package com.safetynet.alerts.model.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


public class AdultByAddress {

    private static final Logger logger = LogManager.getLogger(AdultByAddress.class);

    private String firstName;
    private String lastName;

    public AdultByAddress(String firstName, String lastName) {
        logger.debug("AdultByAddress constructor");
        logger.debug("firstName : " + firstName + " | lastName : " + lastName);
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
