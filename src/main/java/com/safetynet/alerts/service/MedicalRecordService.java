package com.safetynet.alerts.service;

import com.safetynet.alerts.model.DTO.MedicalRecordDTO;

import java.util.List;

public interface MedicalRecordService {

    List<MedicalRecordDTO> findAll();
    List<MedicalRecordDTO> findByFirstName(String firstName);
}
