package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;

public interface MedicalRecordService {


    /**
     * Create a medicalRecord
     * @param medicalRecord
     * @return medicalRecord
     */
    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Update a medicalRecord
     * @param medicalRecord
     */
    void updateMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Delete a medicalRecord
     * @param medicalRecord
     */
    void deleteMedicalRecord(MedicalRecord medicalRecord);



}
