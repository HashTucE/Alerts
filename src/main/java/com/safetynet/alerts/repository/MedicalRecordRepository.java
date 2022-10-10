package com.safetynet.alerts.repository;

import com.jsoniter.any.Any;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.util.DataReader;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Data
public class MedicalRecordRepository {



    private static final Logger log = LogManager.getLogger(MedicalRecordRepository.class);

    private List<MedicalRecord> medicalRecordsList = loadMedicalRecordsList();

    private static Any any = DataReader.jsonReader();



    public List<MedicalRecord> loadMedicalRecordsList() {


        Any anyMedicalRecords = any.get("medicalrecords");
        List<MedicalRecord> medicalRecordsList = new ArrayList<>();
        for (Any element : anyMedicalRecords) {
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setFirstName(String.valueOf(element.get("firstName")));
            medicalRecord.setLastName(String.valueOf(element.get("lastName")));
            medicalRecord.setBirthdate(String.valueOf(element.get("birthdate")));
            medicalRecord.setMedications(Collections.singletonList(String.valueOf(element.get("medications"))));
            medicalRecord.setAllergies(Collections.singletonList(String.valueOf(element.get("allergies"))));
            medicalRecordsList.add(medicalRecord);
        }
        log.debug("Medical records loaded");
        return medicalRecordsList;
    }



    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecordsList.stream()
                .anyMatch(m -> m.getFirstName().equals(medicalRecord.getFirstName())
                && m.getLastName().equals(medicalRecord.getLastName()))) {
            throw new IllegalArgumentException("The value is already in the list.");
        }else {
            medicalRecordsList.add(medicalRecord);
            log.info("Medical Record added !");
            return medicalRecord;
        }
    }


    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecord updateMedicalRecord = medicalRecordsList.stream()
                .filter(m -> m.getFirstName().equals(medicalRecord.getFirstName())
                        && m.getLastName().equals(medicalRecord.getLastName()))
                .findAny().orElseThrow(()-> new IllegalArgumentException("Medical Record not found !"));
        medicalRecordsList.set(medicalRecordsList.indexOf(updateMedicalRecord),medicalRecord);
        log.info("Medical Record updated !");
        return medicalRecord;
    }


    public void deleteMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecord deleteMedicalRecord = medicalRecordsList.stream()
                .filter(m -> m.getFirstName().equals(medicalRecord.getFirstName())
                        && m.getLastName().equals(medicalRecord.getLastName()))
                .findAny().orElseThrow(()-> new IllegalArgumentException("Medical Record not found !"));
        medicalRecordsList.remove(deleteMedicalRecord);
        log.info("Medical Record deleted !");
    }




    public List<MedicalRecord> findAll() {
        log.info("Medical Records loaded !");
        return medicalRecordsList;
    }


    public MedicalRecord findMedicalRecordByName(String firstName, String lastName) {
        return  medicalRecordsList.stream()
                .filter(person -> person.getFirstName().equals(firstName)
                        && person.getLastName().equals(lastName))
                .findAny().orElseThrow(()-> new IllegalArgumentException("Medical Record not found !"));
    }


    public List<String> findMedicationsByName(String firstName, String lastName) {
        for (MedicalRecord medicalRecord : medicalRecordsList) {
            if (medicalRecord.getFirstName().equals(firstName)
                && medicalRecord.getLastName().equals(lastName)) {
                log.debug("Medications found for" + firstName + lastName);
                return medicalRecord.getMedications();
            }
        }
        log.debug("Medications not found for" + firstName + lastName);
        return null;
    }


    public List<String> findAllergiesByName(String firstName, String lastName) {
        for (MedicalRecord medicalRecord : medicalRecordsList) {
            if (medicalRecord.getFirstName().equals(firstName)
                    && medicalRecord.getLastName().equals(lastName)) {
                log.debug("Allergies found for" + firstName + lastName);
                return medicalRecord.getAllergies();
            }
        }
        log.debug("Allergies not found for" + firstName + lastName);
        return null;
    }
}