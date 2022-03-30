package com.safetynet.alerts.model.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class which groups a list person and the number of adults and children it contains in an object.
 */
public class PersonByFireStation {

    private static final Logger logger = LogManager.getLogger(PersonByFireStation.class);

    private List<PersonInfo> personInfoList;
    private int nbAdult;
    private int nbMinor;

    public PersonByFireStation() {

    }

    public PersonByFireStation(List<PersonInfo> personInfoList, int nbAdult, int nbMinor) {
        logger.debug("PersonByFireStation constructor");
        logger.debug("personInfoList : " + personInfoList + " | nbAdult : " + nbAdult + " | nbMinor : " + nbMinor);
        this.personInfoList = personInfoList;
        this.nbAdult = nbAdult;
        this.nbMinor = nbMinor;
    }

    public List<PersonInfo> getPersonInfoList() {
        return personInfoList;
    }

    public void setPersonInfoList(List<PersonInfo> personInfoList) {
        this.personInfoList = personInfoList;
    }

    public int getNbAdult() {
        return nbAdult;
    }

    public void setNbAdult(int nbAdult) {
        this.nbAdult = nbAdult;
    }

    public int getNbMinor() {
        return nbMinor;
    }

    public void setNbMinor(int nbMinor) {
        this.nbMinor = nbMinor;
    }

    @Override
    public String toString() {
        return "{" +
                personInfoList +
                ", nbAdult=" + nbAdult +
                ", nbMinor=" + nbMinor +
                '}';
    }
}