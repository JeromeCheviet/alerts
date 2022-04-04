package com.safetynet.alerts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class that manages the operations related to the calculation of the age.
 */
@Component
public class CalculateDateImpl implements CalculateDate {

    private static final Logger logger = LogManager.getLogger(CalculateDate.class);

    private LocalDate today = LocalDate.now();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdult(LocalDate birthdate) {
        logger.debug("CalculateDate isAdult");
        logger.debug("birthdate : " + birthdate);

        Period age = Period.between(birthdate, today);
        logger.debug("current age : " + age.getYears());
        if (age.getYears() > 18) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateAge(LocalDate birthdate) {
        logger.debug("CalculateDate calculateAge");
        logger.debug("birthdate : " + birthdate);

        Period age = Period.between(birthdate, today);
        logger.debug("return " + age.getYears());
        return age.getYears();
    }
}