package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.application.AddressByFireStation;
import com.safetynet.alerts.model.application.CustomMessage;
import com.safetynet.alerts.model.application.PersonByFireStation;
import com.safetynet.alerts.model.core.FireStation;
import com.safetynet.alerts.model.dto.FireStationDTO;
import com.safetynet.alerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class grouping fire station APIs.
 */
@RestController
public class FireStationController {

    private static final Logger logger = LogManager.getLogger(FireStationController.class);

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    private CustomMessage error;

    /**
     * POST-like API for creating a fire station.
     *
     * @param fireStation <b>FireSation</b> : object to create.
     * @return the list of all fire stations.
     */
    @PostMapping(value = "/firestation")
    public ResponseEntity<Object> createFireStation(@RequestBody FireStation fireStation) {
        logger.info("POST new Fire Station : " + fireStation.toString());

        if (fireStationService.addressExist(fireStation)) {
            logger.info("Fire Station already exist");
            return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Fire Station already exist"), HttpStatus.CONFLICT);
        }

        logger.info("New FireStation " + fireStation.toString() + " has been created");
        return new ResponseEntity<>(fireStationService.addFireStation(fireStation), HttpStatus.CREATED);
    }

    /**
     * PUT-like API for updating a fire station.
     *
     * @param fireStation <b>FireStation</b> : object to update.
     * @return the list of all fire stations.
     */
    @PutMapping(value = "/firestation")
    public ResponseEntity<Object> updateFireStation(@RequestBody FireStation fireStation) {
        logger.info("PUT update Fire Station : " + fireStation.toString());

        if (!fireStationService.addressExist(fireStation)) {
            logger.info("Fire Station not exist");
            return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Fire Station not exist"), HttpStatus.CONFLICT);
        }

        logger.info("Fire Station " + fireStation.toString() + " has been updated");
        return new ResponseEntity<>(fireStationService.updateFireStation(fireStation), HttpStatus.OK);
    }

    /**
     * DELETE-like API for deleting a fire station.
     *
     * @param address       <b>String</b>
     * @param stationNumber <b>int</b>
     * @return the HTTP status code of the request result.
     */
    @DeleteMapping(value = "/firestation")
    public ResponseEntity<CustomMessage> deleteFireStation(@RequestParam(value = "address", required = false) String address, @RequestParam(value = "stationNumber", required = false) Integer stationNumber) {
        logger.info("DELETE fire station mapping address : " + address + " and/or mapping station : " + stationNumber);

        int deletedCode = fireStationService.deleteFireStation(address, stationNumber);
        logger.debug("deletedCode : " + deletedCode);

        switch (deletedCode) {
            case 1:
                logger.info("Fire station with address : " + address + " and station number : " + stationNumber + " has been deleted");
                return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.OK.value(), "Fire station with address : " + address + " and station number : " + stationNumber + " has been deleted"), HttpStatus.OK);
            case 2:
                logger.info("Fire station mapping with station number : " + stationNumber + " has been deleted");
                return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.OK.value(), "Fire station mapping with station number : " + stationNumber + " has been deleted"), HttpStatus.OK);
            case 3:
                logger.info("Fire station mapping to address : " + address + " has been deleted");
                return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.OK.value(), "Fire station mapping to address : " + address + " has been deleted"), HttpStatus.OK);
            default:
                logger.info("Fire station with address : " + address + " and station number : " + stationNumber + " not exist");
                return new ResponseEntity<>(new CustomMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Fire station with address : " + address + " and station number : " + stationNumber + " not exist"), HttpStatus.CONFLICT);
        }
    }

    /**
     * GET-like API to have all fire stations.
     *
     * @return all the fire stations in a list.
     */
    @GetMapping("/firestations")
    public List<FireStationDTO> listFireStation() {
        logger.info("Ask GET firestations");
        logger.info("Response : " + fireStationService.findAll());
        return fireStationService.findAll();
    }

    /**
     * GET-like API to have the first name, last name, address, phone number for each person and the number of adults and children covered by the corresponding fire station.
     *
     * @param stationNumber <b>int</b> : the fire station number.
     * @return a list of persons.
     */
    @GetMapping("/firestation")
    public ResponseEntity<MappingJacksonValue> getPersonCoverageByAFireStation(@RequestParam(value = "stationNumber") int stationNumber) {
        logger.info("Ask GET firestation?stationNumber=" + stationNumber);
        PersonByFireStation personByFireStation = fireStationService.findPersonCoverageByFireStation(stationNumber);
        logger.info("Response : " + personByFireStation);

        SimpleBeanPropertyFilter cityZipAgeEmailMedicamentAllergies = SimpleBeanPropertyFilter.serializeAllExcept("city", "zip", "age", "email", "medicament", "allergies");
        FilterProvider filters = new SimpleFilterProvider().addFilter("personInfoFilter", cityZipAgeEmailMedicamentAllergies);
        MappingJacksonValue personInfoFilters = new MappingJacksonValue(personByFireStation);
        personInfoFilters.setFilters(filters);

        if (personByFireStation.getPersonInfoList().isEmpty()) {
            return new ResponseEntity<>(personInfoFilters, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(personInfoFilters, HttpStatus.OK);
        }
    }

    /**
     * GET-like API to have the phone number list covered by a specific fire station.
     *
     * @param firestation_number <b>int</b> : the fire station number
     * @return a list of phone number covered by the fire station.
     */
    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getPhoneNumberByFireStation(@RequestParam(value = "firestation") int firestation_number) {
        logger.info("Ask Get phoneAlert?firestation=" + firestation_number);
        List<String> phoneNumbers = fireStationService.findPhoneNumberByFireStation(firestation_number);
        logger.info("Response : " + phoneNumbers);

        if (phoneNumbers.isEmpty()) {
            return new ResponseEntity<>(phoneNumbers, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
        }
    }

    /**
     * GET-like API to have a list that groups person by fire station address. It must include the first name, last name, phone number, age, medications and allergies for each people.
     *
     * @param stations <b>List</b> of <i>int</i> : the list of fire station number.
     * @return a list of all homes served by the list of fire station.
     */
    @GetMapping("/flood/stations")
    public ResponseEntity<MappingJacksonValue> getAddressByFireStation(@RequestParam(value = "stations") List<Integer> stations) {
        logger.info("Ask Get flood/stations?stations=" + stations);
        List<AddressByFireStation> addressByFireStationList = fireStationService.findAddressByFireStation(stations);
        logger.info("Response : " + addressByFireStationList);

        SimpleBeanPropertyFilter emailAddressZipCityFilter = SimpleBeanPropertyFilter.serializeAllExcept("email", "address", "zip", "city");
        FilterProvider filters = new SimpleFilterProvider().addFilter("personInfoFilter", emailAddressZipCityFilter);
        MappingJacksonValue personInfoFilters = new MappingJacksonValue(addressByFireStationList);
        personInfoFilters.setFilters(filters);

        if (addressByFireStationList.isEmpty()) {
            return new ResponseEntity<>(personInfoFilters, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(personInfoFilters, HttpStatus.OK);
        }
    }

}
