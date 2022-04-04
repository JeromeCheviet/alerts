package com.safetynet.alerts.service;

import com.safetynet.alerts.model.core.MedicalRecord;
import com.safetynet.alerts.model.dto.MedicalRecordDTO;

import java.util.List;

/**
 * Interface linked to the management of medical records.
 */
public interface MedicalRecordService {

    /**
     * Check if a medical record existing.
     *
     * @param medicalRecord <b>MedicalRecord</b> object.
     * @return a <i>boolean</i>, true if existing, or false if not.
     */
    boolean medicalRecordExist(MedicalRecord medicalRecord);

    /**
     * Find all medical records
     *
     * @return a list of <i>MedicalRecordDTO</i> object.
     */
    List<MedicalRecordDTO> findAll();

    /**
     * Find a person's medical records
     *
     * @param firstName <b>String</b> : the person's first name.
     * @return a list of <i>MedicalRecord</i> objects.
     */
    List<MedicalRecordDTO> findByFirstName(String firstName);

    /**
     * Adding a medical record.
     *
     * @param medicalRecord <b>MedicalRecord</b> : a new medical record to add.
     * @return all medical records in a list.
     */
    List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Updating an existing medical record.
     *
     * @param medicalRecord <b>MedicalRecord</b> : object with updates information.
     * @return all medical records in a list.
     */
    List<MedicalRecord> updateMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Deleting a medical record.
     *
     * @param firstName <b>String</b> : person's first name who owns the medical record to delete.
     * @param lastName  <b>String</b> : person's last name who owns the medical record to delete.
     * @return <b>true</b> if delete completed or <b>false</b> if not.
     */
    boolean deleteMedicalRecord(String firstName, String lastName);
}
