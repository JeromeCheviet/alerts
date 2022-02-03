package com.safetynet.alerts.service;

import com.safetynet.alerts.manager.PersonMapper;
import com.safetynet.alerts.model.DTO.FireStationDTO;
import com.safetynet.alerts.model.DTO.MedicalRecordDTO;
import com.safetynet.alerts.model.DTO.PersonDTO;
import com.safetynet.alerts.model.application.*;
import com.safetynet.alerts.model.core.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonServiceImpl implements PersonService {

    private static Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private CalculateDate calculateDate;


    @Override
    public List<Person> findAll() {
        logger.debug("PersonService findAll");
        if (personRepository.getPersonList().isEmpty()) {
            logger.warn("Persons list is empty");
        }
        return personMapper.mapDtoToDomainPersonList(personRepository.getPersonList());
    }

    @Override
    public List<Person> findByFirstName(String firstName) {
        logger.debug("PersonService findByFirstName");
        List<PersonDTO> personDTOList = new ArrayList<>();
        for (PersonDTO personDTO : personRepository.getPersonList()) {
            if (personDTO.getFirstName().equals(firstName)) {
                personDTOList.add(new PersonDTO(
                        personDTO.getFirstName(),
                        personDTO.getLastName(),
                        personDTO.getAddress(),
                        personDTO.getCity(),
                        personDTO.getZip(),
                        personDTO.getPhone(),
                        personDTO.getEmail()
                ));
            }
        }
        return personMapper.mapDtoToDomainPersonList(personDTOList);
    }

    @Override
    public List<Person> findByAddress(String address) {
        List<PersonDTO> personDTOList = new ArrayList<>();
        for (PersonDTO personDTO : personRepository.getPersonList()) {
            if (personDTO.getAddress().equals(address)) {
                personDTOList.add(new PersonDTO(
                        personDTO.getFirstName(),
                        personDTO.getLastName(),
                        personDTO.getAddress(),
                        personDTO.getCity(),
                        personDTO.getZip(),
                        personDTO.getPhone(),
                        personDTO.getEmail()
                ));
            }
        }
        return personMapper.mapDtoToDomainPersonList(personDTOList);
    }

    @Override
    public List<String> findEmailByCity(String city) {
        logger.debug("PersonService findEmailByCity");
        logger.debug("city : " + city);

        List<String> emailList = new ArrayList<>();
        for (PersonDTO personDTO : personRepository.getPersonList()) {
            if (personDTO.getCity().equals(city)) {
                if (!emailList.contains(personDTO.getEmail())) {
                    emailList.add(personDTO.getEmail());
                }
            }
        }
        logger.debug("return " + emailList);
        return emailList;
    }

    @Override
    public PersonByAddress findPersonByAddress(String address) {
        logger.debug("PersonService findPersonByAddress");
        logger.debug("address : " + address);
        List<PersonInfo> personInfoList = new ArrayList<>();
        int fireStationNumber = 0;
        int age = 0;
        for (PersonDTO personDTO : personRepository.getPersonList()) {
            List<String> medications = new ArrayList<>();
            List<String> allergies = new ArrayList<>();
            if (personDTO.getAddress().equals(address)) {
                for (MedicalRecordDTO medicalRecordDTO : medicalRecordRepository.getMedicalRecordList()) {
                    if (medicalRecordDTO.getFirstName().equals(personDTO.getFirstName())) {
                        for (String medication : medicalRecordDTO.getMedications()) {
                            medications.add(medication);
                        }
                        for (String allergy : medicalRecordDTO.getAllergies()) {
                            allergies.add(allergy);
                        }
                        age = calculateDate.calculateAge(medicalRecordDTO.getBirthdate());
                    }
                }
                logger.debug("medications : " + medications);
                logger.debug("allergies : " + allergies);

                personInfoList.add(new PersonInfo(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getAddress(), personDTO.getCity(), personDTO.getZip(), age, personDTO.getEmail(), personDTO.getPhone(), medications, allergies));
            }
        }
        for (FireStationDTO fireStationDTO : fireStationRepository.getFireStationList()) {
            if (fireStationDTO.getAddress().equals(address)) {
                fireStationNumber = fireStationDTO.getStation();
            }
        }
        logger.debug("personInfoList : " + personInfoList);
        logger.debug("fireStationNumber : " + fireStationNumber);
        return new PersonByAddress(personInfoList, fireStationNumber);
    }

    @Override
    public ChildrenByAddressConstructor findChildrenByAddress(String address) {
        logger.debug("PersonService findChildrenByAddress");
        logger.debug("address : " + address);
        List<ChildrenByAddress> childrenByAddressList = new ArrayList<>();
        List<AdultByAddress> adultByAddressList = new ArrayList<>();
        int age;

        for (PersonDTO personDTO : personRepository.getPersonList()) {
            if (personDTO.getAddress().equals(address)) {
                for (MedicalRecordDTO medicalRecordDTO : medicalRecordRepository.getMedicalRecordList()) {
                    if (medicalRecordDTO.getFirstName().equals(personDTO.getFirstName())) {
                        if (!calculateDate.isAdult(medicalRecordDTO.getBirthdate())) {
                            age = calculateDate.calculateAge(medicalRecordDTO.getBirthdate());
                            childrenByAddressList.add(new ChildrenByAddress(personDTO.getFirstName(), personDTO.getLastName(), age));
                        } else {
                            adultByAddressList.add(new AdultByAddress(personDTO.getFirstName(), personDTO.getLastName()));
                        }
                    }
                }

            }
        }
        logger.debug("childrenByAddressList" + childrenByAddressList);
        logger.debug("adultByAddressList" + adultByAddressList);
        if (childrenByAddressList.isEmpty()) {
            return new ChildrenByAddressConstructor();
        } else {
            return new ChildrenByAddressConstructor(childrenByAddressList, adultByAddressList);
        }
    }

    @Override
    public List<PersonInfo> findPersonInfo(String firstName, String lastName) {
        logger.debug("PersonService PersonInfo");
        logger.debug("firstname : " + firstName);
        logger.debug("lastName : " + lastName);

        List<PersonInfo> personInfoList = new ArrayList<>();
        int age = 0;

        for (PersonDTO personDTO : personRepository.getPersonList()) {
            List<String> medications = new ArrayList<>();
            List<String> allergies = new ArrayList<>();
            if (personDTO.getFirstName().equals(firstName) && personDTO.getLastName().equals(lastName)) {
                for (MedicalRecordDTO medicalRecordDTO : medicalRecordRepository.getMedicalRecordList()) {
                    if (medicalRecordDTO.getFirstName().equals(firstName)) {
                        age = calculateDate.calculateAge(medicalRecordDTO.getBirthdate());
                        for (String medication : medicalRecordDTO.getMedications()) {
                            medications.add(medication);
                        }
                        for (String allergy : medicalRecordDTO.getAllergies()) {
                            allergies.add(allergy);
                        }
                    }
                }
                personInfoList.add(new PersonInfo(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getAddress(), personDTO.getCity(), personDTO.getZip(), age, personDTO.getEmail(), personDTO.getPhone(), medications, allergies));
            }
        }
        logger.debug("personInfoList : " + personInfoList);

        return personInfoList;
    }

}

