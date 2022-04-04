package com.safetynet.alerts.service;

import com.safetynet.alerts.manager.MedicalRecordMapper;
import com.safetynet.alerts.model.core.MedicalRecord;
import com.safetynet.alerts.model.dto.MedicalRecordDTO;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {
    private final LocalDate today = LocalDate.now();
    private final LocalDate birthDate = today.minusYears(41);
    private final List<MedicalRecordDTO> medicalRecordDTOList = new ArrayList<>();
    private final List<MedicalRecord> medicalRecordList = new ArrayList<>();

    @InjectMocks
    private MedicalRecordService medicalRecordService = new MedicalRecordServiceImpl();
    @Mock
    private MedicalRecordMapper medicalRecordMapper = new MedicalRecordMapper();
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @Mock
    private MedicalRecordDTO expectMedicalRecordDTO;
    @Mock
    private MedicalRecord expectMedicalRecord;

    @BeforeEach
    private void setUp() {
        List<String> expectMedicament = new ArrayList<>();
        List<String> expectAllergies = new ArrayList<>();

        String expectFirstName = "Jerome";
        String expectLastName = "Cheviet";
        LocalDate expectBirthDate = birthDate;

        expectMedicament.add("aznol:350mg");
        expectAllergies.add("peanut");

        expectMedicalRecordDTO = new MedicalRecordDTO(expectFirstName,
                expectLastName,
                expectBirthDate,
                expectMedicament,
                expectAllergies);

        expectMedicalRecord = new MedicalRecord(expectFirstName,
                expectLastName,
                expectBirthDate,
                expectMedicament,
                expectAllergies);

    }

    @Test
    public void medicalRecordExist_return_true() {
        List<String> actualMedicament = new ArrayList<>();
        List<String> actualAllergies = new ArrayList<>();

        medicalRecordDTOList.add(expectMedicalRecordDTO);

        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        LocalDate actualBirthDate = birthDate;

        actualMedicament.add("aznol:350mg");
        actualAllergies.add("peanut");

        MedicalRecord actualMedicalRecord = new MedicalRecord(actualFirstName,
                actualLastName,
                actualBirthDate,
                actualMedicament,
                actualAllergies);

        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);

        boolean actualMedicalRecordExist = medicalRecordService.medicalRecordExist(actualMedicalRecord);

        assertEquals(true, actualMedicalRecordExist);

    }

    @Test
    public void medicalRecordExist_return_false() {
        List<String> actualMedicament = new ArrayList<>();
        List<String> actualAllergies = new ArrayList<>();

        medicalRecordDTOList.add(expectMedicalRecordDTO);

        String actualFirstName = "Julien";
        String actualLastName = "Cheviet";
        LocalDate actualBirthDate = birthDate;

        actualMedicament.add("aznol:350mg");
        actualAllergies.add("peanut");

        MedicalRecord actualMedicalRecord = new MedicalRecord(actualFirstName,
                actualLastName,
                actualBirthDate,
                actualMedicament,
                actualAllergies);

        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);

        boolean actualMedicalRecordExist = medicalRecordService.medicalRecordExist(actualMedicalRecord);

        assertEquals(false, actualMedicalRecordExist);

    }

    @Test
    public void findAll_return_medicalrecord() {
        medicalRecordDTOList.add(expectMedicalRecordDTO);

        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);

        List<MedicalRecordDTO> actualMedicalRecord = medicalRecordService.findAll();

        assertEquals(medicalRecordDTOList, actualMedicalRecord);

    }

    @Test
    public void findAll_return_empty() {
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);

        List<MedicalRecordDTO> actualMedicalRecord = medicalRecordService.findAll();

        assertEquals(medicalRecordDTOList, actualMedicalRecord);

    }

    @Test
    public void testFindByFirstName() {
        String actualFirstName = "Jerome";
        medicalRecordDTOList.add(expectMedicalRecordDTO);

        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);

        List<MedicalRecordDTO> actualMedicalRecordDTO = medicalRecordService.findByFirstName(actualFirstName);

        assertEquals(medicalRecordDTOList.toString(), actualMedicalRecordDTO.toString());
    }

    @Test
    public void testAddMedicalRecord() {
        List<String> actualMedicament = new ArrayList<>();
        List<String> actualAllergies = new ArrayList<>();

        String actualFirstName = "Ronane";
        String actualLastName = "Cheviet";
        LocalDate actualBirthDate = birthDate;

        actualAllergies.add("peanut");

        MedicalRecord actualMedicalRecord = new MedicalRecord(actualFirstName,
                actualLastName,
                actualBirthDate,
                actualMedicament,
                actualAllergies);

        medicalRecordDTOList.add(expectMedicalRecordDTO);
        medicalRecordList.add(expectMedicalRecord);
        medicalRecordList.add(actualMedicalRecord);

        when(medicalRecordMapper.mapDomainMedicalRecordToDto(actualMedicalRecord)).thenReturn(expectMedicalRecordDTO);
        doNothing().when(medicalRecordRepository).addMedicalRecord(expectMedicalRecordDTO);
        when(medicalRecordMapper.mapToDomainMedicalRecordList(medicalRecordDTOList)).thenReturn(medicalRecordList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);

        List<MedicalRecord> actualMedicalRecordList = medicalRecordService.addMedicalRecord(actualMedicalRecord);

        assertEquals(medicalRecordList, actualMedicalRecordList);

        verify(medicalRecordRepository, times(1)).addMedicalRecord(expectMedicalRecordDTO);
    }

    @Test
    public void testUpdateMedicalRecord() {
        List<String> actualMedicament = new ArrayList<>();
        List<String> actualAllergies = new ArrayList<>();

        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        LocalDate actualBirthDate = birthDate;

        actualMedicament.add("aznol:350mg");
        actualAllergies.add("peanut");
        actualAllergies.add("pollen");

        MedicalRecord actualMedicalRecord = new MedicalRecord(actualFirstName,
                actualLastName,
                actualBirthDate,
                actualMedicament,
                actualAllergies);

        medicalRecordDTOList.add(expectMedicalRecordDTO);
        medicalRecordList.add(actualMedicalRecord);

        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);
        when(medicalRecordMapper.mapToDomainMedicalRecordList(medicalRecordDTOList)).thenReturn(medicalRecordList);

        List<MedicalRecord> actualMedicalRecordList = medicalRecordService.updateMedicalRecord(actualMedicalRecord);

        assertEquals(medicalRecordList, actualMedicalRecordList);
    }

    @Test
    public void testDeleteMedicalRecord_return_true() {
        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        medicalRecordDTOList.add(expectMedicalRecordDTO);

        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);
        doNothing().when(medicalRecordRepository).deleteMedicalRecord(expectMedicalRecordDTO);

        boolean actualDeleteMedicalRecord = medicalRecordService.deleteMedicalRecord(actualFirstName, actualLastName);

        assertEquals(true, actualDeleteMedicalRecord);

        verify(medicalRecordRepository, times(1)).deleteMedicalRecord(expectMedicalRecordDTO);
    }

    @Test
    public void testDeleteMedicalRecord_return_false() {
        String actualFirstName = "Ronane";
        String actualLastName = "Cheviet";
        medicalRecordDTOList.add(expectMedicalRecordDTO);

        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalRecordDTOList);

        boolean actualDeleteMedicalRecord = medicalRecordService.deleteMedicalRecord(actualFirstName, actualLastName);

        assertEquals(false, actualDeleteMedicalRecord);
    }

}
