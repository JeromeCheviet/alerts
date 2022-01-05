package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private static Logger logger = LogManager.getLogger(MedicalRecordService.class);

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalRecord> findAll() {
        logger.debug("MedicalRecordService findAll");
        if (medicalRecordRepository.getMedicalRecordList().isEmpty()) {
            logger.warn("Medical record is empty");
        }
        return medicalRecordRepository.getMedicalRecordList();
    }
}
