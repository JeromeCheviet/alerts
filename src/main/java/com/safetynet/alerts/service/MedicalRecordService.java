package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {

    List<MedicalRecord> findAll();
}
