package com.safetynet.alerts.service;

import com.safetynet.alerts.model.DTO.MedicalRecordDTO;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private static Logger logger = LogManager.getLogger(MedicalRecordService.class);

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalRecordDTO> findAll() {
        logger.debug("MedicalRecordService findAll");
        if (medicalRecordRepository.getMedicalRecordList().isEmpty()) {
            logger.warn("Medical record is empty");
        }
        return medicalRecordRepository.getMedicalRecordList();
    }

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
}
