package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {



    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

    void deleteMedicalRecord(MedicalRecord medicalRecord);



    List<MedicalRecord> findAll();

//    List<String> findAllergiesByName(String firstName, String lastName);

//    List<String>findMedicationsByName(String firstName, String lastName);


}
