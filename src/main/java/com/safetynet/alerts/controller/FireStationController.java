package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.DTO.FireStationDTO;
import com.safetynet.alerts.model.application.AddressByFireStation;
import com.safetynet.alerts.model.application.PersonByFireStation;
import com.safetynet.alerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireStationController {

    private static final Logger logger = LogManager.getLogger(FireStationController.class);

    @Autowired
    private FireStationService fireStationService;

    @GetMapping("/firestations")
    public List<FireStationDTO> listFireStation() {
        logger.info("Ask GET firestations");
        logger.info("Response : " + fireStationService.findAll());
        return fireStationService.findAll();
    }

    @GetMapping("/firestation")
    public ResponseEntity<MappingJacksonValue> getPersonCoverageByAFireStation(@RequestParam(value = "stationNumber") int stationNumber) {
        logger.info("Ask GET firestation?stationNumber=" + stationNumber);
        PersonByFireStation personByFireStation = fireStationService.findPersonCoverageByFireStation(stationNumber);
        logger.info("Response : " + personByFireStation);

        SimpleBeanPropertyFilter cityZipAgeEmailMedicamentAllergies = SimpleBeanPropertyFilter.serializeAllExcept("city", "zip", "age", "email", "medicament", "allergies");
        FilterProvider filters = new SimpleFilterProvider().addFilter("personInfoFilter", cityZipAgeEmailMedicamentAllergies);
        MappingJacksonValue personInfoFilters = new MappingJacksonValue(personByFireStation);
        personInfoFilters.setFilters(filters);

        if (personByFireStation.toString().isEmpty()) {
            return new ResponseEntity<>(personInfoFilters, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(personInfoFilters, HttpStatus.OK);
        }
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getPhoneNumberByFireStation(@RequestParam (value = "firestation") int firestation_number) {
        logger.info("Ask Get phoneAlert?firestation=" + firestation_number);
        List<String> phoneNumbers = fireStationService.findPhoneNumberByFireStation(firestation_number);
        logger.info("Response : " + phoneNumbers);

        if (phoneNumbers.isEmpty()) {
            return new ResponseEntity<>(phoneNumbers,HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
        }
    }

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
