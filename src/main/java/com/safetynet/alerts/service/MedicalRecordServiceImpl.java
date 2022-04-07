package com.safetynet.alerts.service;

import com.safetynet.alerts.manager.MedicalRecordMapper;
import com.safetynet.alerts.model.core.MedicalRecord;
import com.safetynet.alerts.model.dto.MedicalRecordDTO;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles all operations related to medical records.
 */
@Repository
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private static Logger logger = LogManager.getLogger(MedicalRecordService.class);

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean medicalRecordExist(MedicalRecord medicalRecord) {
        logger.debug("MedicalRecordService medicalRecordExist");
        logger.debug("medicalRecord : " + medicalRecord);

        for (MedicalRecordDTO medicalRecordDTO : medicalRecordRepository.getMedicalRecordList()) {
            if (medicalRecordDTO.getFirstName().equals(medicalRecord.getFirstName()) &&
                    medicalRecordDTO.getLastName().equals(medicalRecord.getLastName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicalRecordDTO> findAll() {
        logger.debug("MedicalRecordService findAll");
        if (medicalRecordRepository.getMedicalRecordList().isEmpty()) {
            logger.warn("Medical record is empty");
        }
        return medicalRecordRepository.getMedicalRecordList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicalRecordDTO> findByFirstName(String firstName) {
        logger.debug("MedicalRecord findByFirstName");
        List<MedicalRecordDTO> medicalRecordDTOList = new ArrayList<>();
        for (MedicalRecordDTO medicalRecordDTO : medicalRecordRepository.getMedicalRecordList()) {
            if (medicalRecordDTO.getFirstName().equals(firstName)) {
                medicalRecordDTOList.add(new MedicalRecordDTO(
                        medicalRecordDTO.getFirstName(),
                        medicalRecordDTO.getLastName(),
                        medicalRecordDTO.getBirthdate(),
                        medicalRecordDTO.getMedications(),
                        medicalRecordDTO.getAllergies()
                ));
            }
        }
        logger.debug("medicalRecordDTOList");
        return medicalRecordDTOList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord) {
        logger.debug("MedicalRecordService addMedicalRecord");
        logger.debug("medicalRecord : " + medicalRecord.toString());

        medicalRecordRepository.addMedicalRecord(medicalRecordMapper.mapDomainMedicalRecordToDto(medicalRecord));
        return medicalRecordMapper.mapToDomainMedicalRecordList(medicalRecordRepository.getMedicalRecordList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicalRecord> updateMedicalRecord(MedicalRecord medicalRecord) {
        logger.debug("MedicalRecordService updateMedicalRecord");
        logger.debug("medicalRecord : " + medicalRecord.toString());

        for (MedicalRecordDTO medicalRecordDTO : medicalRecordRepository.getMedicalRecordList()) {
            if (medicalRecordDTO.getFirstName().equals(medicalRecord.getFirstName()) &&
                    medicalRecordDTO.getLastName().equals(medicalRecord.getLastName())) {
                medicalRecordDTO.setBirthdate(medicalRecord.getBirthdate());
                medicalRecordDTO.setMedications(medicalRecord.getMedications());
                medicalRecordDTO.setAllergies(medicalRecord.getAllergies());
            }
        }
        return medicalRecordMapper.mapToDomainMedicalRecordList(medicalRecordRepository.getMedicalRecordList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteMedicalRecord(String firstName, String lastName) {
        logger.debug("MedicalRecordService deleteMedicalRecord");
        logger.debug("firstName : " + firstName + " | lastName : " + lastName);

        for (MedicalRecordDTO medicalRecordDTO : medicalRecordRepository.getMedicalRecordList()) {
            if (medicalRecordDTO.getFirstName().equals(firstName) && medicalRecordDTO.getLastName().equals(lastName)) {
                logger.debug("medicalRecordDTO : " + medicalRecordDTO.toString());
                medicalRecordRepository.deleteMedicalRecord(medicalRecordDTO);
                return true;
            }
        }
        return false;
    }

}
