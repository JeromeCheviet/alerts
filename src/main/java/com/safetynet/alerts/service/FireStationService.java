package com.safetynet.alerts.service;

import com.safetynet.alerts.model.application.AddressByFireStation;
import com.safetynet.alerts.model.application.PersonByFireStation;
import com.safetynet.alerts.model.core.FireStation;
import com.safetynet.alerts.model.dto.FireStationDTO;

import java.util.List;

/**
 * Interface linked to the management of fire stations.
 */
public interface FireStationService {

    /**
     * Check if a fire station existing
     *
     * @param fireStation <b>FireStation</b> object.
     * @return a <i>boolean</i>, true if existing, or false if not.
     */
    boolean addressExist(FireStation fireStation);

    /**
     * Find all fire stations
     *
     * @return a list of <i>FireStationDTO</i> objects.
     */
    List<FireStationDTO> findAll();

    /**
     * Find all persons whose coverage by a fire station.
     *
     * @param stationNumber <b>int</b> : the fire station number
     * @return a <b>PersonByFireStation</b> object.
     */
    PersonByFireStation findPersonCoverageByFireStation(int stationNumber);

    /**
     * Find all the phone numbers that depend on a fire station
     *
     * @param stationNumber <b>int</b> : the fire station number
     * @return a list of phone number in <b>String</b> format.
     */
    List<String> findPhoneNumberByFireStation(int stationNumber);

    /**
     * Find all addresses that are attached to a fire station list.
     *
     * @param stations <b>List</b> of <b>Integer</b> : list which contain one or more fire station number.
     * @return a list of <i>AddressByFireStation</i> objects.
     */
    List<AddressByFireStation> findAddressByFireStation(List<Integer> stations);

    /**
     * Adding a fire station.
     *
     * @param fireStation <b>FireStation</b> :  a new fire station to add.
     * @return all fire stations in a list.
     */
    List<FireStation> addFireStation(FireStation fireStation);

    /**
     * Deleting a fire station.
     *
     * @param address       <b>String</b> : the fire station address to delete. <b>Can be null if <i>stationNumber</i> is present.</b>.
     * @param stationNumber <b>integer</b> : the fire station number to delete. <b>Can be null if <i>address</i> is present.</b>
     * @return an <b>int</b> corresponding to the state of the result. If <i>1</i> : a specific fire station was deleted; else if <i>2</i> all fire station with the same station number are deleted; else <i>3</i> all fire station mapped to the same address are deleted.
     */
    int deleteFireStation(String address, Integer stationNumber);

    /**
     * Updating an existing fire station.
     *
     * @param fireStation <b>FireStation</b> : object with updates information.
     * @return all fire stations in a list.
     */
    List<FireStation> updateFireStation(FireStation fireStation);
}
