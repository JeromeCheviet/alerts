package com.safetynet.alerts.service;

import com.safetynet.alerts.model.core.MedicalRecord;
import com.safetynet.alerts.model.dto.MedicalRecordDTO;

import java.util.List;

public interface MedicalRecordService {

    boolean medicalRecordExist(MedicalRecord medicalRecord);

    List<MedicalRecordDTO> findAll();
    List<MedicalRecordDTO> findByFirstName(String firstName);

    List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord);

    List<MedicalRecord> updateMedicalRecord(MedicalRecord medicalRecord);

    boolean deleteMedicalRecord(String firstName, String lastName);
}
