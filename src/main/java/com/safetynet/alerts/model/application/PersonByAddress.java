package com.safetynet.alerts.model.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class which groups a list of all person depend on the same station in an object.
 */
public class PersonByAddress {

    private static final Logger logger = LogManager.getLogger(PersonByAddress.class);

    private List<PersonInfo> personInfoList;
    private int fireStationNumber;

    public PersonByAddress() {

    }

    public PersonByAddress(List<PersonInfo> personInfoList, int fireStationNumber) {
        logger.debug("PersonByAddress constructor");
        logger.debug("personInfoList : " + personInfoList + " | fireStationNumber : " + fireStationNumber);
        this.personInfoList = personInfoList;
        this.fireStationNumber = fireStationNumber;
    }

    public List<PersonInfo> getPersonInfoList() {
        return personInfoList;
    }

    public void setPersonInfoList(List<PersonInfo> personInfoList) {
        this.personInfoList = personInfoList;
    }

    public int getFireStationNumber() {
        return fireStationNumber;
    }

    public void setFireStationNumber(int fireStationNumber) {
        this.fireStationNumber = fireStationNumber;
    }

    @Override
    public String toString() {
        return "{" +
                personInfoList +
                ", fireStationNumber=" + fireStationNumber +
                '}';
    }
}
