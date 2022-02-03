package com.safetynet.alerts.model.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;


public class AddressByFireStation {

    private static final Logger logger = LogManager.getLogger(AddressByFireStation.class);

    private String address;
    private List<PersonInfo> personInfoList;

    public AddressByFireStation(String address, List<PersonInfo> personInfoList) {
        logger.debug("AddressByFireStation constructor");
        logger.debug("address : " + address + " | personInfoList : " + personInfoList);
        this.address = address;
        this.personInfoList = personInfoList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonInfo> getPersonInfoList() {
        return personInfoList;
    }

    public void setPersonInfoList(List<PersonInfo> personInfoList) {
        this.personInfoList = personInfoList;
    }

    @Override
    public String toString() {
        return "{" +
                "'" + address + '\'' +
                ", " + personInfoList +
                '}';
    }
}
