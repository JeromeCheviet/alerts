package com.safetynet.alerts.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateDateTest {

    private CalculateDate calculateDate = new CalculateDateImpl();

    private LocalDate today = LocalDate.now();

    @Test
    public void isAdult_return_true() throws Exception {
        LocalDate adultBirthDate = today.minusYears(20);

        boolean actualIsAdult = calculateDate.isAdult(adultBirthDate);

        assertEquals(true, actualIsAdult);

    }

    @Test
    public void isAdult_return_false() throws Exception {
        LocalDate minorBirthDate = today.minusYears(10);

        boolean actualIsMinor = calculateDate.isAdult(minorBirthDate);

        assertEquals(false, actualIsMinor);
    }

    @Test
    public void calculateAge_return_40() throws Exception {
        LocalDate birthDate = today.minusYears(40);

        int actualAge = calculateDate.calculateAge(birthDate);

        assertEquals(40, actualAge);

    }
}
