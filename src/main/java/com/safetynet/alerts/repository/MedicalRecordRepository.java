package com.safetynet.alerts.repository;

import com.jsoniter.any.Any;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.util.DataReader;
import com.safetynet.alerts.util.DataWriter;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Data
public class MedicalRecordRepository {



    private static final Logger log = LogManager.getLogger(MedicalRecordRepository.class);

    private List<MedicalRecord> medicalRecordsList = loadMedicalRecordsList();

    private static DataWriter dataWriter = new DataWriter();



    /**
     * @return a list of medical records containing fistName, lastName, birthdate,
     * a list of medications and a list of allergies
     */
    public List<MedicalRecord> loadMedicalRecordsList() {

        Any any = DataReader.jsonReader();
        Any anyMedicalRecords = any.get("medicalrecords");
        List<MedicalRecord> medicalRecordsList = new ArrayList<>();
        for (Any element : anyMedicalRecords) {
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setFirstName(String.valueOf(element.get("firstName")));
            medicalRecord.setLastName(String.valueOf(element.get("lastName")));
            medicalRecord.setBirthdate(String.valueOf(element.get("birthdate")));

            Any anyMedication = element.get("medications");
            List<String> medicationsList = new ArrayList<>();
            for (Any medication : anyMedication) {medicationsList.add(String.valueOf(medication));}
            medicalRecord.setMedications(medicationsList);

            Any anyAllergy = element.get("allergies");
            List<String> allergiesList = new ArrayList<>();
            for (Any allergy : anyAllergy) {allergiesList.add(String.valueOf(allergy));}
            medicalRecord.setAllergies(allergiesList);

            medicalRecordsList.add(medicalRecord);
        }
        log.debug("Trying to return the medical record's list");
        return medicalRecordsList;
    }


    /**
     * Add a medical record
     * @param medicalRecord medical record to add
     */
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        if (dataWriter.getMedicalRecordList()
                .stream()
                .anyMatch(m -> m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName()))) {
            throw new IllegalArgumentException("The medical record of " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " already exist");
        }else {
            dataWriter.getMedicalRecordList().add(medicalRecord);
            dataWriter.jsonWriter();
            log.info("Adding the medical record for " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        }
    }


    /**
     * Update an existing medical record (all fields except names can be modified)
     * @param medicalRecord medical record to update
     */
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecord updateMedicalRecord = dataWriter.getMedicalRecordList()
                .stream()
                .filter(m -> m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName()))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("The medical record of " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " was not found"));
        dataWriter.getMedicalRecordList().set(dataWriter.getMedicalRecordList().indexOf(updateMedicalRecord),medicalRecord);
        dataWriter.jsonWriter();
        log.info("Trying to update the medical record for " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
    }


    /**
     * Remove a medical record (use first and last name as an identifier unique)
     * @param medicalRecord medical record to delete
     */
    public void deleteMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecord deleteMedicalRecord = dataWriter.getMedicalRecordList()
                .stream()
                .filter(m -> m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName()))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("The medical record of " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " was not found"));
        dataWriter.getMedicalRecordList().remove(deleteMedicalRecord);
        dataWriter.jsonWriter();
        log.info("Deleting to update the medical record for " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
    }


    /**
     * Find the full medical records list
     * @return a list medical records
     */
    public List<MedicalRecord> findAll() {
        log.info("Medical Records list loaded");
        return medicalRecordsList;
    }


    /**
     * Find a medications list from first name and last name
     * @param firstName first name
     * @param lastName last name
     * @return a list of medications
     */
    public List<String> findMedicationsByName(String firstName, String lastName) {
        for (MedicalRecord medicalRecord : medicalRecordsList) {
            if (medicalRecord.getFirstName().equals(firstName)
                && medicalRecord.getLastName().equals(lastName)) {
                log.debug("Medications found for " + firstName +" "+ lastName);
                return medicalRecord.getMedications();
            }
        }
        log.debug("Medications not found for " + firstName + " " + lastName);
        return null;
    }


    /**
     * Find an allergies list from first name and last name
     * @param firstName first name
     * @param lastName last name
     * @return a list of allergies
     */
    public List<String> findAllergiesByName(String firstName, String lastName) {
        for (MedicalRecord medicalRecord : medicalRecordsList) {
            if (medicalRecord.getFirstName().equals(firstName)
                    && medicalRecord.getLastName().equals(lastName)) {
                log.debug("Allergies found for " + firstName + " " + lastName);
                return medicalRecord.getAllergies();
            }
        }
        log.debug("Allergies not found for " + firstName + " " + lastName);
        return null;
    }
}