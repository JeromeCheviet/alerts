package com.safetynet.alerts.manager;

import com.safetynet.alerts.model.DTO.MedicalRecordDTO;
import com.safetynet.alerts.model.core.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicalRecordMapper {

    private static final Logger logger = LogManager.getLogger(MedicalRecordMapper.class);

    public MedicalRecord mapDtoToDomainMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        logger.debug("MedicalRecordMapper mapDtoToDomainMedicalRecord");
        logger.debug("medicalRecordDTO : " + medicalRecordDTO.toString());
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(medicalRecordDTO.getFirstName());
        medicalRecord.setLastName(medicalRecordDTO.getLastName());
        medicalRecord.setBirthdate(medicalRecordDTO.getBirthdate());
        medicalRecord.setMedications(medicalRecordDTO.getMedications());
        medicalRecord.setAllergies(medicalRecordDTO.getAllergies());

        logger.debug("return " + medicalRecord.toString());
        return medicalRecord;
    }

    public List<MedicalRecord> mapToDomainMedicalRecordList(List<MedicalRecordDTO> medicalRecordDTOArrayList) {
        logger.debug("medicalRecordDTOArrayList : " + medicalRecordDTOArrayList);
        return medicalRecordDTOArrayList.stream().map(medicalRecordDTO -> mapDtoToDomainMedicalRecord(medicalRecordDTO)).collect(Collectors.toList());
    }
}
