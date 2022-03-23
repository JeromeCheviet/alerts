package com.safetynet.alerts.manager;

import com.safetynet.alerts.model.core.MedicalRecord;
import com.safetynet.alerts.model.dto.MedicalRecordDTO;
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

@ExtendWith(MockitoExtension.class)
public class MedicalRecordMapperTest {
    private final List<MedicalRecordDTO> medicalRecordDTOList = new ArrayList<>();
    private final List<MedicalRecord> medicalRecordList = new ArrayList<>();
    private final LocalDate today = LocalDate.now();
    private final LocalDate birthDate = today.minusYears(41);

    @InjectMocks
    private MedicalRecordMapper medicalRecordMapper = new MedicalRecordMapper();
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
    public void testMapDtoDomainMedicalRecord() {
        List<String> actualMedicament = new ArrayList<>();
        List<String> actualAllergies = new ArrayList<>();

        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        LocalDate actualBirthDate = birthDate;

        actualMedicament.add("aznol:350mg");
        actualAllergies.add("peanut");
        MedicalRecordDTO actualMedicalRecordDTO = new MedicalRecordDTO(actualFirstName,
                actualLastName,
                actualBirthDate,
                actualMedicament,
                actualAllergies);

        MedicalRecord actualMedicalRecord = medicalRecordMapper.mapDtoToDomainMedicalRecord(actualMedicalRecordDTO);

        assertEquals(expectMedicalRecord.getFirstName(), actualMedicalRecord.getFirstName());
        assertEquals(expectMedicalRecord.getLastName(), actualMedicalRecord.getLastName());
        assertEquals(expectMedicalRecord.getBirthdate(), actualMedicalRecord.getBirthdate());
        assertEquals(expectMedicalRecord.getMedications(), actualMedicalRecord.getMedications());
        assertEquals(expectMedicalRecord.getAllergies(), actualMedicalRecord.getAllergies());
        assertEquals(expectMedicalRecord.toString(), actualMedicalRecord.toString());
    }

    @Test
    public void testMapToDomainMedicalRecordList() {
        List<String> actualMedicament = new ArrayList<>();
        List<String> actualAllergies = new ArrayList<>();

        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        LocalDate actualBirthDate = birthDate;

        actualMedicament.add("aznol:350mg");
        actualAllergies.add("peanut");
        MedicalRecordDTO actualMedicalRecordDTO = new MedicalRecordDTO(actualFirstName,
                actualLastName,
                actualBirthDate,
                actualMedicament,
                actualAllergies);

        medicalRecordDTOList.add(actualMedicalRecordDTO);
        medicalRecordList.add(expectMedicalRecord);

        List<MedicalRecord> actualMedicalRecordList = medicalRecordMapper.mapToDomainMedicalRecordList(medicalRecordDTOList);

        assertEquals(medicalRecordList.toString(), actualMedicalRecordList.toString());
    }

    @Test
    public void testMapDomainMedicalRecordToDto() {
        List<String> actualMedicament = new ArrayList<>();
        List<String> actualAllergies = new ArrayList<>();

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

        MedicalRecordDTO actualMedicalRecordDTO = medicalRecordMapper.mapDomainMedicalRecordToDto(actualMedicalRecord);

        assertEquals(expectMedicalRecordDTO.getFirstName(), actualMedicalRecordDTO.getFirstName());
        assertEquals(expectMedicalRecordDTO.getLastName(), actualMedicalRecordDTO.getLastName());
        assertEquals(expectMedicalRecordDTO.getBirthdate(), actualMedicalRecordDTO.getBirthdate());
        assertEquals(expectMedicalRecordDTO.getMedications(), actualMedicalRecordDTO.getMedications());
        assertEquals(expectMedicalRecordDTO.getAllergies(), actualMedicalRecordDTO.getAllergies());
        assertEquals(expectMedicalRecordDTO.toString(), actualMedicalRecordDTO.toString());
    }

}
