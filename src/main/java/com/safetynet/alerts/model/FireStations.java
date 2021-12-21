package com.safetynet.alerts.model;

public class FireStations {
    private int id;
    private String address;
    private int station;

    public FireStations(int id, String address, int station) {
        this.id = id;
        this.address = address;
        this.station = station;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", address='" + address + '\'' +
                ", station=" + station +
                '}';
    }
}
