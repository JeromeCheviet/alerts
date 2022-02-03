package com.safetynet.alerts.model.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FireStation {

    private static final Logger logger = LogManager.getLogger(FireStation.class);

    private String address;
    private int station;

    public FireStation() {
    }

    public FireStation(String address, int station) {
        logger.debug("FireStation constructor");
        logger.debug("address : " + address + " | station : " + station);
        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "FireStations{" +
                "address='" + address + '\'' +
                ", station=" + station +
                '}';
    }
}