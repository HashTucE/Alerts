package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {



    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    void updateMedicalRecord(MedicalRecord medicalRecord);

    void deleteMedicalRecord(MedicalRecord medicalRecord);


    ///////////
    List<MedicalRecord> findAllMedicalRecords();
    ///////////
}
