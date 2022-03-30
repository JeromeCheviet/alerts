package com.safetynet.alerts.service;

import com.safetynet.alerts.manager.FireStationMapper;
import com.safetynet.alerts.model.application.AddressByFireStation;
import com.safetynet.alerts.model.application.PersonByFireStation;
import com.safetynet.alerts.model.application.PersonInfo;
import com.safetynet.alerts.model.core.FireStation;
import com.safetynet.alerts.model.core.Person;
import com.safetynet.alerts.model.dto.FireStationDTO;
import com.safetynet.alerts.model.dto.MedicalRecordDTO;
import com.safetynet.alerts.model.dto.PersonDTO;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles all operations related to fire stations.
 */
@Repository
public class FireStationServiceImpl implements FireStationService {

    private static final Logger logger = LogManager.getLogger(FireStationService.class);

    @Autowired
    private FireStationRepository fireStationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private CalculateDate calculateDate;

    @Autowired
    private FireStationMapper fireStationMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addressExist(FireStation fireStation) {
        logger.debug("FireStationService addressExist");
        logger.debug("fireStation : " + fireStation);

        for (FireStationDTO fireStationDTO : fireStationRepository.getFireStationList()) {
            if (fireStationDTO.getAddress().equals(fireStation.getAddress())) {
                logger.debug("Fire station exist");
                return true;
            }
        }
        logger.debug("Fire station not exist");
        return false;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FireStationDTO> findAll() {
        logger.debug("FireStationService findAll");
        if (fireStationRepository.getFireStationList().isEmpty()) {
            logger.warn("Fire Stations list is empty");
        }
        return fireStationRepository.getFireStationList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonByFireStation findPersonCoverageByFireStation(int stationNumber) {
        logger.debug("FireStationService findPersonCoverageByFireStation");
        logger.debug("stationNumber : " + stationNumber);

        List<Person> personDTOS;
        List<PersonInfo> personInfoList = new ArrayList<>();
        LocalDate birthdate = null;
        int numberAdults = 0;
        int numberMinors = 0;
        int age = 0;

        for (FireStationDTO fireStationDTO : fireStationRepository.getFireStationList()) {
            if (fireStationDTO.getStation() == stationNumber) {
                personDTOS = personService.findByAddress(fireStationDTO.getAddress());
                for (Person person : personDTOS) {
                    List<String> medications = new ArrayList<>();
                    List<String> allergies = new ArrayList<>();

                    for (MedicalRecordDTO medicalRecordDTO : medicalRecordRepository.getMedicalRecordList()) {
                        if (medicalRecordDTO.getFirstName().equals(person.getFirstName())) {
                            birthdate = medicalRecordDTO.getBirthdate();
                            for (String medication : medicalRecordDTO.getMedications()) {
                                medications.add(medication);
                            }
                            logger.debug("medications : " + medications);
                            for (String allergy : medicalRecordDTO.getAllergies()) {
                                allergies.add(allergy);
                            }
                            logger.debug("allergies : " + allergies);
                        }
                    }

                    age = calculateDate.calculateAge(birthdate);

                    personInfoList.add(new PersonInfo(person.getFirstName(),
                            person.getLastName(),
                            person.getAddress(),
                            person.getCity(),
                            person.getZip(),
                            age,
                            person.getEmail(),
                            person.getPhone(),
                            medications,
                            allergies));

                    if (calculateDate.isAdult(birthdate)) {
                        numberAdults++;
                    } else {
                        numberMinors++;
                    }
                }
            }
        }
        logger.debug("personInfoList : " + personInfoList);
        logger.debug("Total adults : " + numberAdults + " and total childrens : " + numberMinors);
        return new PersonByFireStation(personInfoList, numberAdults, numberMinors);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> findPhoneNumberByFireStation(int stationNumber) {
        logger.debug("FireStationService findPhoneNumberByFireStation");
        logger.debug("stationNumber : " + stationNumber);
        List<Person> personDTOS;
        List<String> phoneNumberList = new ArrayList<>();
        for (FireStationDTO fireStationDTO : fireStationRepository.getFireStationList()) {
            if (fireStationDTO.getStation() == stationNumber) {
                personDTOS = personService.findByAddress(fireStationDTO.getAddress());
                for (Person person : personDTOS) {
                    if (!phoneNumberList.contains(person.getPhone())) {
                        phoneNumberList.add(person.getPhone());
                    }
                }
            }
        }
        logger.debug("phoneNumberList : " + phoneNumberList);
        return phoneNumberList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AddressByFireStation> findAddressByFireStation(List<Integer> stations) {
        logger.debug(("FireStationService findAddressByFireStation"));
        List<AddressByFireStation> addressByFireStationList = new ArrayList<>();
        int age = 0;
        for (Integer station : stations) {
            List<PersonInfo> personInfosList = new ArrayList<>();
            for (FireStationDTO fireStationDTO : fireStationRepository.getFireStationList()) {
                if (fireStationDTO.getStation() == station) {
                    for (PersonDTO personDTO : personRepository.getPersonList()) {
                        if (personDTO.getAddress().equals(fireStationDTO.getAddress())) {
                            List<String> medications = new ArrayList<>();
                            List<String> allergies = new ArrayList<>();
                            for (MedicalRecordDTO medicalRecordDTO : medicalRecordRepository.getMedicalRecordList()) {
                                if (medicalRecordDTO.getFirstName().equals(personDTO.getFirstName())) {
                                    age = calculateDate.calculateAge(medicalRecordDTO.getBirthdate());
                                    for (String medication : medicalRecordDTO.getMedications()) {
                                        medications.add(medication);
                                    }
                                    for (String allergy : medicalRecordDTO.getAllergies()) {
                                        allergies.add(allergy);
                                    }
                                }
                            }
                            logger.debug("medications : " + medications);
                            logger.debug("allergies : " + allergies);
                            personInfosList.add(new PersonInfo(personDTO.getFirstName(),
                                    personDTO.getLastName(),
                                    personDTO.getAddress(),
                                    personDTO.getCity(),
                                    personDTO.getZip(),
                                    age,
                                    personDTO.getEmail(),
                                    personDTO.getPhone(),
                                    medications,
                                    allergies));
                            logger.debug("personInfosList : " + personInfosList);
                        }
                    }
                    addressByFireStationList.add(new AddressByFireStation(fireStationDTO.getAddress(), personInfosList));
                }
            }
        }
        logger.debug("addressByFireStationList : " + addressByFireStationList);
        return addressByFireStationList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FireStation> addFireStation(FireStation fireStation) {
        logger.debug("FireStationService addFireStation");
        logger.debug("fireStation : " + fireStation.toString());

        fireStationRepository.addFireStation(fireStationMapper.mapDomainFireStationToDto(fireStation));
        return fireStationMapper.mapDtoToDomainFireStationList(fireStationRepository.getFireStationList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteFireStation(String address, Integer stationNumber) {
        logger.debug("FireStationService deleteMappingFireStationNumber");
        logger.debug("address : " + address + " | stationNumber : " + stationNumber);


        if (address != null && stationNumber != null) {
            fireStationRepository.deleteFireStation(address, stationNumber);
            return 1;
        } else if (address == null && stationNumber != null) {
            fireStationRepository.deleteMappingFireStationNumber(stationNumber);
            return 2;
        } else if (address != null && stationNumber == null) {
            fireStationRepository.deleteMappingFireStationAddress(address);
            return 3;
        }

        return 9999;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FireStation> updateFireStation(FireStation fireStation) {
        logger.debug("FireStationService updateFireStation");
        logger.debug("fireStation : " + fireStation.toString());

        for (FireStationDTO fireStationDTO : fireStationRepository.getFireStationList()) {
            if (fireStationDTO.getAddress().equals(fireStation.getAddress())) {
                fireStationDTO.setStation(fireStation.getStation());
            }
        }
        return fireStationMapper.mapDtoToDomainFireStationList(fireStationRepository.getFireStationList());
    }

}
