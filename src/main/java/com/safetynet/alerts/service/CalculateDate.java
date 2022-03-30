package com.safetynet.alerts.service;

import java.time.LocalDate;

/**
 * Interface linked to the management of birthdate.
 */
public interface CalculateDate {

    /**
     * Define if person is an adult or a children.
     *
     * @param birthdate <b>LocalDate</b>
     * @return a boolean if is an adult true or false for a children.
     */
    boolean isAdult(LocalDate birthdate);

    /**
     * Calculate a person's age based on their birthdate.
     *
     * @param birthdate <b>LocalDate</b>
     * @return the age of a person in <b>int</b> format.
     */
    int calculateAge(LocalDate birthdate);

}
