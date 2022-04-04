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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {
    private final LocalDate today = LocalDate.now();
    private final LocalDate birthDate = today.minusYears(41);
    private final Period calculateAge = Period.between(birthDate, today);
    private final int age = calculateAge.getYears();
    private final List<FireStationDTO> fireStationDTOList = new ArrayList<>();
    private final List<FireStation> fireStationList = new ArrayList<>();
    private final List<Person> personList = new ArrayList<>();
    private final List<PersonDTO> personDTOList = new ArrayList<>();
    private final List<PersonInfo> personInfoList = new ArrayList<>();
    private final List<MedicalRecordDTO> medicalRecordDTOList = new ArrayList<>();

    @InjectMocks
    private FireStationService fireStationService = new FireStationServiceImpl();
    @Mock
    private PersonService personService = new PersonServiceImpl();
    @Mock
    private FireStationMapper fireStationMapper = new FireStationMapper();
    @Mock
    private FireStation actualFireStation;
    @Mock
    private FireStationDTO expectFireStationDTO;
    @Mock
    private PersonDTO expectAdultPersonDTO;
    @Mock
    private Person expectAdultPerson;
    @Mock
    private PersonInfo expectPersonInfo;
    @Mock
    private MedicalRecordDTO expectMedicalRecordDTO;
    @Mock
    private CalculateDate calculateDate;
    @Mock
    private FireStationRepository fireStationRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @Mock
    private PersonByFireStation expectPersonByFireStation;
    @Mock
    private AddressByFireStation expectAddressByFireStation;

    @BeforeEach
    private void setUp() {
        List<String> expectMedicament = new ArrayList<>();
        List<String> expectAllergies = new ArrayList<>();
        List<PersonInfo> personInfos = new ArrayList<>();

        String expectAddress = "1 rue du Puit";
        int expectStation = 20;

        String expectFirstName = "Jerome";
        String expectLastName = "Cheviet";
        String expectCity = "Cluver";
        int expectZip = 97451;
        int expectAge = age;
        LocalDate expectBirthDate = birthDate;
        String expectEmail = "jcheviet@email.com";
        String expectPhone = "123-456-7890";
        expectMedicament.add("aznol:350mg");
        expectAllergies.add("peanut");

        expectFireStationDTO = new FireStationDTO(expectAddress, expectStation);

        expectAdultPersonDTO = new PersonDTO(expectFirstName,
                expectLastName,
                expectAddress,
                expectCity,
                expectZip,
                expectPhone,
                expectEmail);

        expectAdultPerson = new Person(expectFirstName,
                expectLastName,
                expectAddress,
                expectCity,
                expectZip,
                expectPhone,
                expectEmail);

        expectMedicalRecordDTO = new MedicalRecordDTO(expectFirstName,
                expectLastName,
                expectBirthDate,
                expectMedicament,
                expectAllergies);

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

        personInfos.add(expectPersonInfo);
        expectAddressByFireStation = new AddressByFireStation(expectFireStationDTO.getAddress(), personInfos);

    }

    @Test
    public void addressExist_return_true() throws Exception {
        String actualAddress = "1 rue du Puit";
        int actualStation = 20;
        actualFireStation = new FireStation(actualAddress, actualStation);
        fireStationDTOList.add(expectFireStationDTO);

        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);

        boolean actualAddressExist = fireStationService.addressExist(actualFireStation);

        assertEquals(true, actualAddressExist);

    }

    @Test
    public void addressExist_return_false() throws Exception {
        String actualAddress = "5 rue de la Fontaine";
        int actualStation = 30;
        actualFireStation = new FireStation(actualAddress, actualStation);
        fireStationDTOList.add(expectFireStationDTO);

        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);

        boolean actualAddressExist = fireStationService.addressExist(actualFireStation);

        assertEquals(false, actualAddressExist);

    }

    @Test
    public void findAll_return_fireStationList() {
        fireStationDTOList.add(expectFireStationDTO);

        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);

        List<FireStationDTO> actualFireStationDTOList = fireStationService.findAll();

        assertEquals(fireStationDTOList, actualFireStationDTOList);

    }

    @Test
    public void findAll_return_empty() {
        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);

        List<FireStationDTO> actualFireStationDTOList = fireStationService.findAll();

        assertEquals(fireStationDTOList, actualFireStationDTOList);

    }

    @Test
    public void testFindPersonCoverageByFireStation() {
        fireStationDTOList.add(expectFireStationDTO);
        personList.add(expectAdultPerson);
        personDTOList.add(expectAdultPersonDTO);
        medicalRecordDTOList.add(expectMedicalRecordDTO);
        personInfoList.add(expectPersonInfo);

        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);
        when(personService.findByAddress("1 rue du Puit")).thenReturn(personList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);
        when(calculateDate.calculateAge(birthDate)).thenReturn(age);
        when(calculateDate.isAdult(birthDate)).thenReturn(true);

        expectPersonByFireStation = new PersonByFireStation(personInfoList, 1, 0);
        PersonByFireStation actualPersonByFireStation = fireStationService.findPersonCoverageByFireStation(20);

        assertEquals(expectPersonByFireStation.toString(), actualPersonByFireStation.toString());

    }

    @Test
    public void testFindPhoneNumberByFireStation() {
        List<String> expectPhoneNumberList = new ArrayList<>();

        fireStationDTOList.add(expectFireStationDTO);
        personList.add(expectAdultPerson);
        expectPhoneNumberList.add(expectAdultPersonDTO.getPhone());

        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);
        when(personService.findByAddress("1 rue du Puit")).thenReturn(personList);

        List<String> actualPhoneNumberList = fireStationService.findPhoneNumberByFireStation(20);

        assertEquals(expectPhoneNumberList, actualPhoneNumberList);

    }

    @Test
    public void testFindAddressByFireStation() {
        List<Integer> stations = new ArrayList<>();
        List<AddressByFireStation> expectAddressByFireStationList = new ArrayList<>();

        fireStationDTOList.add(expectFireStationDTO);
        personDTOList.add(expectAdultPersonDTO);
        medicalRecordDTOList.add(expectMedicalRecordDTO);
        stations.add(expectFireStationDTO.getStation());
        expectAddressByFireStationList.add(expectAddressByFireStation);

        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);
        when(personRepository.getPersonList()).thenReturn(personDTOList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);
        when(calculateDate.calculateAge(birthDate)).thenReturn(age);

        List<AddressByFireStation> actualAddressByFireStationList = fireStationService.findAddressByFireStation(stations);

        assertEquals(expectAddressByFireStationList.toString(), actualAddressByFireStationList.toString());

    }

    @Test
    public void testAddFireStation() {
        String actualAddress = "2 rue du Puit";
        int actualStation = 21;
        fireStationDTOList.add(expectFireStationDTO);
        actualFireStation = new FireStation(actualAddress, actualStation);
        fireStationList.add(new FireStation(expectFireStationDTO.getAddress(), expectFireStationDTO.getStation()));
        fireStationList.add(actualFireStation);

        when(fireStationMapper.mapDomainFireStationToDto(actualFireStation)).thenReturn(expectFireStationDTO);
        doNothing().when(fireStationRepository).addFireStation(expectFireStationDTO);
        when(fireStationMapper.mapDtoToDomainFireStationList(fireStationDTOList)).thenReturn(fireStationList);
        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);

        List<FireStation> actualFireStationList = fireStationService.addFireStation(actualFireStation);

        assertEquals(fireStationList, actualFireStationList);

        verify(fireStationRepository, times(1)).addFireStation(expectFireStationDTO);
    }

    @Test
    public void testDeleteFireStation_return_1() {
        String actualAddress = "1 rue du Puit";
        int actualStation = 1;

        doNothing().when(fireStationRepository).deleteFireStation(actualAddress, actualStation);

        int actualDeleteFireStation = fireStationService.deleteFireStation(actualAddress, actualStation);

        assertEquals(1, actualDeleteFireStation);

        verify(fireStationRepository, times(1)).deleteFireStation(actualAddress, actualStation);
    }

    @Test
    public void testDeleteFireStation_return_2() {
        String actualAddress = null;
        int actualStation = 1;

        doNothing().when(fireStationRepository).deleteMappingFireStationNumber(actualStation);

        int actualDeleteFireStation = fireStationService.deleteFireStation(actualAddress, actualStation);

        assertEquals(2, actualDeleteFireStation);

        verify(fireStationRepository, times(1)).deleteMappingFireStationNumber(actualStation);
    }

    @Test
    public void testDeleteFireStation_return_3() {
        String actualAddress = "1 rue du Puit";
        Integer actualStation = null;

        doNothing().when(fireStationRepository).deleteMappingFireStationAddress(actualAddress);

        int actualDeleteFireStation = fireStationService.deleteFireStation(actualAddress, actualStation);

        assertEquals(3, actualDeleteFireStation);

        verify(fireStationRepository, times(1)).deleteMappingFireStationAddress(actualAddress);
    }

    @Test
    public void testUpdateFireStation() {
        String actualAddress = "1 rue du Puit";
        int actualStation = 21;
        fireStationDTOList.add(expectFireStationDTO);
        actualFireStation = new FireStation(actualAddress, actualStation);
        fireStationList.add(actualFireStation);

        when(fireStationRepository.getFireStationList()).thenReturn(fireStationDTOList);
        when(fireStationMapper.mapDtoToDomainFireStationList(fireStationDTOList)).thenReturn(fireStationList);

        List<FireStation> actualFireStationList = fireStationService.updateFireStation(actualFireStation);

        assertEquals(fireStationList, actualFireStationList);

    }

}
