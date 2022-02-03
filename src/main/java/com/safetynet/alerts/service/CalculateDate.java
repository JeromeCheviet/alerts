package com.safetynet.alerts.service;

import java.time.LocalDate;

public interface CalculateDate {

    boolean isAdult(LocalDate birthdate);
    int calculateAge(LocalDate birthdate);

}
