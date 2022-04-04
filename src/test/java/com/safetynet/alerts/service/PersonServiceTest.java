package com.safetynet.alerts.service;

import com.safetynet.alerts.manager.PersonMapper;
import com.safetynet.alerts.model.application.*;
import com.safetynet.alerts.model.core.Person;
import com.safetynet.alerts.model.dto.FireStationDTO;
import com.safetynet.alerts.model.dto.MedicalRecordDTO;
import com.safetynet.alerts.model.dto.PersonDTO;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    private final LocalDate today = LocalDate.now();
    private final LocalDate birthDate = today.minusYears(41);
    private final Period calculateAge = Period.between(birthDate, today);
    private final int age = calculateAge.getYears();
    private final LocalDate minorBirthDate = today.minusYears(10);
    private final Period minorCalculateAge = Period.between(minorBirthDate, today);
    private final int minorAge = minorCalculateAge.getYears();
    private final List<PersonDTO> personDTOList = new ArrayList<>();
    private final List<Person> personList = new ArrayList<>();
    private final List<PersonInfo> personInfoList = new ArrayList<>();
    private final List<FireStationDTO> fireStationDTOList = new ArrayList<>();
    private final List<MedicalRecordDTO> medicalRecordDTOList = new ArrayList<>();
    private final List<ChildrenByAddress> childrenByAddressList = new ArrayList<>();
    private final List<AdultByAddress> adultByAddressList = new ArrayList<>();

    @InjectMocks
    private PersonService personService = new PersonServiceImpl();
    @Mock
    private PersonMapper personMapper = new PersonMapper();
    @Mock
    private PersonRepository personRepository;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @Mock
    private FireStationRepository fireStationRepository;
    @Mock
    private CalculateDate calculateDate;
    @Mock
    private PersonDTO expectPersonDTO;
    @Mock
    private PersonDTO expectMinorPersonDTO;
    @Mock
    private Person expectPerson;
    @Mock
    private PersonInfo expectPersonInfo;
    @Mock
    private PersonByAddress expectPersonByAddress;
    @Mock
    private MedicalRecordDTO expectMedicalRecordDTO;
    @Mock
    private MedicalRecordDTO expectMinorMedicalRecordDTO;
    @Mock
    private FireStationDTO expectFireStationDTO;
    @Mock
    private ChildrenByAddress expectChildrenByAddress;
    @Mock
    private AdultByAddress expectAdultByAddress;
    @Mock
    private ChildrenByAddressConstructor expectChildrenByAddressConstructor;

    @BeforeEach
    private void setUp() {
        List<String> expectMedicament = new ArrayList<>();
        List<String> expectAllergies = new ArrayList<>();
        List<String> expectMinorMedicament = new ArrayList<>();
        List<String> expectMinorAllergies = new ArrayList<>();

        String expectFirstName = "Jerome";
        String expectLastName = "Cheviet";
        String expectAddress = "1 rue du Puit";
        String expectCity = "Cluver";
        int expectZip = 97451;
        String expectEmail = "jcheviet@email.com";
        String expectPhone = "123-456-7890";

        int expectAge = age;
        expectMedicament.add("aznol:350mg");
        expectAllergies.add("peanut");

        LocalDate expectBirthDate = birthDate;

        String expectMinorFirstName = "Julien";
        int expectMinorAge = minorAge;
        LocalDate expectMinorBirthDate = minorBirthDate;
        expectMinorAllergies.add("pollen");

        int expectStation = 20;

        expectPersonDTO = new PersonDTO(expectFirstName,
                expectLastName,
                expectAddress,
                expectCity,
                expectZip,
                expectPhone,
                expectEmail);

        expectMinorPersonDTO = new PersonDTO(expectMinorFirstName,
                expectLastName,
                expectAddress,
                expectCity,
                expectZip,
                expectPhone,
                expectEmail);

        expectPerson = new Person(expectFirstName,
                expectLastName,
                expectAddress,
                expectCity,
                expectZip,
                expectPhone,
                expectEmail);

        expectPersonInfo = new PersonInfo(expectFirstName,
                expectLastName,
                expectAddress,
                expectCity,
                expectZip,
                expectAge,
                expectEmail,
                expectPhone,
                expectMedicament,
                expectAllergies);

        expectMedicalRecordDTO = new MedicalRecordDTO(expectFirstName,
                expectLastName,
                expectBirthDate,
                expectMedicament,
                expectAllergies);

        expectMinorMedicalRecordDTO = new MedicalRecordDTO(expectMinorFirstName,
                expectLastName,
                expectMinorBirthDate,
                expectMinorMedicament,
                expectMinorAllergies);

        expectFireStationDTO = new FireStationDTO(expectAddress, expectStation);

        expectAdultByAddress = new AdultByAddress(expectFirstName, expectLastName);

        expectChildrenByAddress = new ChildrenByAddress(expectMinorFirstName, expectLastName, expectMinorAge);

    }

    @Test
    public void personExist_return_true() {
        personDTOList.add(expectPersonDTO);

        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        String actualAddress = "1 rue du Puit";
        String actualCity = "Cluver";
        int actualZip = 97451;
        String actualEmail = "jcheviet@email.com";
        String actualPhone = "123-456-7890";

        Person actualPerson = new Person(actualFirstName,
                actualLastName,
                actualAddress,
                actualCity,
                actualZip,
                actualPhone,
                actualEmail);

        when(personRepository.getPersonList()).thenReturn(personDTOList);

        boolean actualPersonExist = personService.personExist(actualPerson);

        assertEquals(true, actualPersonExist);
    }

    @Test
    public void personExist_return_false() {
        personDTOList.add(expectPersonDTO);

        String actualFirstName = "Julien";
        String actualLastName = "Cheviet";
        String actualAddress = "1 rue du Puit";
        String actualCity = "Cluver";
        int actualZip = 97451;
        String actualEmail = "jcheviet@email.com";
        String actualPhone = "123-456-7890";

        Person actualPerson = new Person(actualFirstName,
                actualLastName,
                actualAddress,
                actualCity,
                actualZip,
                actualPhone,
                actualEmail);

        when(personRepository.getPersonList()).thenReturn(personDTOList);

        boolean actualPersonExist = personService.personExist(actualPerson);

        assertEquals(false, actualPersonExist);
    }

    @Test
    public void findAll_return_person() {
        personList.add(expectPerson);
        personDTOList.add(expectPersonDTO);

        when(personRepository.getPersonList()).thenReturn(personDTOList);
        when(personMapper.mapDtoToDomainPersonList(personDTOList)).thenReturn(personList);

        List<Person> actualPersonList = personService.findAll();

        assertEquals(personList, actualPersonList);
    }

    @Test
    public void findAll_return_empty() {

        when(personRepository.getPersonList()).thenReturn(personDTOList);
        when(personMapper.mapDtoToDomainPersonList(personDTOList)).thenReturn(personList);

        List<Person> actualPersonList = personService.findAll();

        assertEquals(personList, actualPersonList);
    }

    /*@Test
    public void testFindByFirstName() {
        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        String actualAddress = "1 rue du Puit";
        String actualCity = "Cluver";
        int actualZip = 97451;
        String actualEmail = "jcheviet@email.com";
        String actualPhone = "123-456-7890";

        Person actualPerson = new Person(actualFirstName,
                actualLastName,
                actualAddress,
                actualCity,
                actualZip,
                actualPhone,
                actualEmail);
        personDTOList.add(expectPersonDTO);
        personList.add(actualPerson);


        when(personRepository.getPersonList()).thenReturn(personDTOList);
        when(personMapper.mapDtoToDomainPersonList(personDTOList)).thenReturn(personList);

        List<Person> actualPersonList = personService.findByFirstName(actualFirstName);

        System.out.println(actualPerson);

        assertEquals(personList, actualPersonList);
    }

    @Test
    public void testFindByAddress() {
        personDTOList.add(expectPersonDTO);
        personList.add(expectPerson);

        String actualAddress = "1 rue du Puit";

        when(personRepository.getPersonList()).thenReturn(personDTOList);
        when(personMapper.mapDtoToDomainPersonList(personDTOList)).thenReturn(personList);

        List<Person> actualPerson = personService.findByAddress(actualAddress);

        assertEquals(personList.toString(), actualPerson.toString());
    }*/

    @Test
    public void testFindEmailByCity() {
        personDTOList.add(expectPersonDTO);
        List<String> expectEmailList = new ArrayList<>();
        expectEmailList.add(expectPersonDTO.getEmail());

        String actualCity = "Cluver";

        when(personRepository.getPersonList()).thenReturn(personDTOList);

        List<String> actualEmailList = personService.findEmailByCity(actualCity);

        assertEquals(expectEmailList, actualEmailList);
    }

    @Test
    public void testFindPersonByAddress() {
        personDTOList.add(expectPersonDTO);
        medicalRecordDTOList.add(expectMedicalRecordDTO);
        personInfoList.add(expectPersonInfo);
        fireStationDTOList.add(expectFireStationDTO);

        expectPersonByAddress = new PersonByAddress(personInfoList, expectFireStationDTO.getStation());

        when(personRepository.getPersonList()).thenReturn(personDTOList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);
        when(calculateDate.calculateAge(expectMedicalRecordDTO.getBirthdate())).thenReturn(age);
        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);

        PersonByAddress actualPersonByAddress = personService.findPersonByAddress("1 rue du Puit");

        assertEquals(expectPersonByAddress.toString(), actualPersonByAddress.toString());

    }

    @Test
    public void testFindChildrenByAddress_without_Children() {
        personDTOList.add(expectPersonDTO);
        medicalRecordDTOList.add(expectMedicalRecordDTO);

        ChildrenByAddressConstructor expectChildrenByAddressConstructor = new ChildrenByAddressConstructor();

        when(personRepository.getPersonList()).thenReturn(personDTOList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);
        when(calculateDate.isAdult(birthDate)).thenReturn(true);

        ChildrenByAddressConstructor actualChildrenByAddressConstructor = personService.findChildrenByAddress("1 rue du Puit");

        assertEquals(expectChildrenByAddressConstructor.toString(), actualChildrenByAddressConstructor.toString());
    }

    @Test
    public void testFindChildrenByAddress_with_Children() {
        personDTOList.add(expectPersonDTO);
        personDTOList.add(expectMinorPersonDTO);
        medicalRecordDTOList.add(expectMedicalRecordDTO);
        medicalRecordDTOList.add(expectMinorMedicalRecordDTO);
        childrenByAddressList.add(expectChildrenByAddress);
        adultByAddressList.add(expectAdultByAddress);

        ChildrenByAddressConstructor expectChildrenByAddressConstructor = new ChildrenByAddressConstructor(childrenByAddressList, adultByAddressList);

        when(personRepository.getPersonList()).thenReturn(personDTOList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);
        when(calculateDate.isAdult(birthDate)).thenReturn(true);
        when(calculateDate.isAdult(minorBirthDate)).thenReturn(false);
        when(calculateDate.calculateAge(minorBirthDate)).thenReturn(minorAge);

        ChildrenByAddressConstructor actualChildrenByAddressConstructor = personService.findChildrenByAddress("1 rue du Puit");

        assertEquals(expectChildrenByAddressConstructor.toString(), actualChildrenByAddressConstructor.toString());
    }

    @Test
    public void testFindPersonInfo() {
        personDTOList.add(expectPersonDTO);
        medicalRecordDTOList.add(expectMedicalRecordDTO);
        personInfoList.add(expectPersonInfo);

        when(personRepository.getPersonList()).thenReturn(personDTOList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);
        when(calculateDate.calculateAge(birthDate)).thenReturn(age);

        List<PersonInfo> actualPersonInfoList = personService.findPersonInfo("Jerome", "Cheviet");

        System.out.println(actualPersonInfoList);
        System.out.println(personInfoList);

        assertEquals(personInfoList.toString(), actualPersonInfoList.toString());
    }

    @Test
    public void testAddPerson() {
        String actualFirstName = "Ronane";
        String actualLastName = "Cheviet";
        String actualAddress = "3 rue du Puit";
        String actualCity = "Cluver";
        int actualZip = 97451;
        String actualEmail = "rcheviet@email.com";
        String actualPhone = "987-654-3210";
        Person actualPerson = new Person(actualFirstName,
                actualLastName,
                actualAddress,
                actualCity,
                actualZip,
                actualEmail,
                actualPhone);
        personList.add(actualPerson);
        personDTOList.add(expectPersonDTO);

        when(personMapper.mapDomainPersonToDto(actualPerson)).thenReturn(expectPersonDTO);
        doNothing().when(personRepository).addPerson(expectPersonDTO);
        when(personMapper.mapDtoToDomainPersonList(personDTOList)).thenReturn(personList);
        when(personRepository.getPersonList()).thenReturn(personDTOList);

        List<Person> actualPersonList = personService.addPerson(actualPerson);

        assertEquals(personList, actualPersonList);

        verify(personRepository, times(1)).addPerson(expectPersonDTO);
    }

    @Test
    public void testDeletePerson_return_true() {
        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        personDTOList.add(expectPersonDTO);

        when(personRepository.getPersonList()).thenReturn(personDTOList);
        doNothing().when(personRepository).deletePerson(expectPersonDTO);

        boolean actualDeletePerson = personService.deletePerson(actualFirstName, actualLastName);

        assertEquals(true, actualDeletePerson);

        verify(personRepository, times(1)).deletePerson(expectPersonDTO);
    }

    @Test
    public void testDeletePerson_return_false() {
        String actualFirstName = "Julien";
        String actualLastName = "Cheviet";
        personDTOList.add(expectPersonDTO);

        when(personRepository.getPersonList()).thenReturn(personDTOList);

        boolean actualDeletePerson = personService.deletePerson(actualFirstName, actualLastName);

        assertEquals(false, actualDeletePerson);
    }

    @Test
    public void testUpdatePerson() {
        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        String actualAddress = "3 rue du Puit";
        String actualCity = "Cluver";
        int actualZip = 97451;
        String actualEmail = "jcheviet@email.com";
        String actualPhone = "987-654-3210";
        Person actualPerson = new Person(actualFirstName,
                actualLastName,
                actualAddress,
                actualCity,
                actualZip,
                actualEmail,
                actualPhone);

        personDTOList.add(expectPersonDTO);
        personList.add(actualPerson);

        when(personRepository.getPersonList()).thenReturn(personDTOList);
        when(personMapper.mapDtoToDomainPersonList(personDTOList)).thenReturn(personList);

        List<Person> actualPersonList = personService.updatePerson(actualPerson);

        assertEquals(personList, actualPersonList);
    }


}
