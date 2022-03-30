package com.safetynet.alerts.manager;

import com.safetynet.alerts.model.core.MedicalRecord;
import com.safetynet.alerts.model.dto.MedicalRecordDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class which maps objects from the MedicalRecordDTO class to the MedicalRecord class or reverse.
 */
@Component
public class MedicalRecordMapper {

    private static final Logger logger = LogManager.getLogger(MedicalRecordMapper.class);

    /**
     * Map a MedicalRecordDTO object to a FireStation object.
     *
     * @param medicalRecordDTO <b>MedicalRecordDTO</b> : One medical record from DTO object.
     * @return a MedicalRecord object.
     */
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

    /**
     * Map a list of MedicalrRecordDTO object to a list of MedicalRecord object.
     *
     * @param medicalRecordDTOArrayList <b>List</b> : list of MecicalRecordDTO object.
     * @return a list of MedicalRecord object.
     */
    public List<MedicalRecord> mapToDomainMedicalRecordList(List<MedicalRecordDTO> medicalRecordDTOArrayList) {
        logger.debug("medicalRecordDTOArrayList : " + medicalRecordDTOArrayList);
        return medicalRecordDTOArrayList.stream().map(medicalRecordDTO -> mapDtoToDomainMedicalRecord(medicalRecordDTO)).collect(Collectors.toList());
    }

    /**
     * Map a MedicalRecord object to MedicalRecordDTO object.
     *
     * @param medicalRecord <b>MedicalObject</b> : One medical record from domain object.
     * @return a MedicalRecordDTO object.
     */
    public MedicalRecordDTO mapDomainMedicalRecordToDto(MedicalRecord medicalRecord) {
        logger.debug("MedicalRecordMapper mapDomainMedicalRecordToDto");
        logger.debug("medicalRecord : " + medicalRecord.toString());
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setFirstName(medicalRecord.getFirstName());
        medicalRecordDTO.setLastName(medicalRecord.getLastName());
        medicalRecordDTO.setBirthdate(medicalRecord.getBirthdate());
        medicalRecordDTO.setMedications(medicalRecord.getMedications());
        medicalRecordDTO.setAllergies(medicalRecord.getAllergies());

        logger.debug("return " + medicalRecordDTO.toString());
        return medicalRecordDTO;
    }

}
