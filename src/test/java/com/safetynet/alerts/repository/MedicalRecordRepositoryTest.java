package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class MedicalRecordRepositoryTest {


    private final MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();



    @Test
    @DisplayName("Should throw an exception when the MedicalRecord is already existing")
    void addMedicalRecordWhenMedicalRecordIsAlreadyExistingThenThrowException() {

        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan"));

        assertThrows(IllegalArgumentException.class, () -> medicalRecordRepository.addMedicalRecord(medicalRecord));
    }


    @Test
    @DisplayName("Should save the medicalRecord when the medicalRecord does not exist")
    void addMedicalRecordWhenMedicalRecordDoesNotExist() {

        MedicalRecord medicalRecord = new MedicalRecord(
                "Jack",
                "Mimoun",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan"));
        medicalRecordRepository.addMedicalRecord(medicalRecord);

        assertEquals(medicalRecord, medicalRecordRepository.findMedicalRecord("Jack", "Mimoun"));

        medicalRecordRepository.deleteMedicalRecord(medicalRecord);
    }


    @Test
    @DisplayName("Should throw an exception when the medicalRecord is not found")
    void updateMedicalRecordWhenMedicalRecordIsNotFoundThenThrowException() {
        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Voight",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan"));

        assertThrows(IllegalArgumentException.class, () -> medicalRecordRepository.updateMedicalRecord(medicalRecord));
    }


    @Test
    @DisplayName("Should update the medicalRecord when the medicalRecord is found")
    void updateMedicalRecordWhenMedicalRecordIsFound() {

        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Petrucci",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan"));
        medicalRecordRepository.addMedicalRecord(medicalRecord);

        MedicalRecord medicalRecordUpdated = new MedicalRecord(
                "John",
                "Petrucci",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan"));
        medicalRecordRepository.updateMedicalRecord(medicalRecordUpdated);

        assertEquals(medicalRecordUpdated, medicalRecordRepository.findMedicalRecord("John", "Petrucci"));

        medicalRecordRepository.deleteMedicalRecord(medicalRecordUpdated);
    }


    @Test
    @DisplayName("Should throw an exception when the medicalRecord is not found")
    void deleteMedicalRecordWhenMedicalRecordIsNotFoundThenThrowException() {

        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Bonjovi",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan"));

        assertThrows(IllegalArgumentException.class, () -> medicalRecordRepository.deleteMedicalRecord(medicalRecord));
    }


    @Test
    @DisplayName("Should delete the medicalRecord when the medicalRecord is found")
    void deleteMedicalRecordWhenMedicalRecordIsFound() {

        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Bonjovi",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan"));

        medicalRecordRepository.addMedicalRecord(medicalRecord);
        medicalRecordRepository.deleteMedicalRecord(medicalRecord);

        assertThrows(IllegalArgumentException.class, () -> medicalRecordRepository.findMedicalRecord("John", "Bonjovi"));
    }


    @Test
    @DisplayName("Should throw an exception when the medicalRecord does not exist")
    void findMedicalRecordWhenMedicalRecordDoesNotExistThenThrowException() {

        assertThrows(IllegalArgumentException.class, () -> medicalRecordRepository.findMedicalRecord("John", "Doe"));
    }


    @Test
    @DisplayName("Should return the medicalRecord when the first name and last name are correct")
    void findMedicalRecordWhenNameExists() {

        MedicalRecord medicalRecord = new MedicalRecord(
                            "John",
                            "Boyd",
                            "03/06/1984",
                            List.of("aznol:350mg", "hydrapermazol:100mg"),
                            List.of("nillacilan"));

        MedicalRecord expected = medicalRecordRepository.findMedicalRecord("John", "Boyd");

        assertEquals(medicalRecord, expected);
    }


    @Test
    @DisplayName("Should return the medications when the first name and last name are correct")
    void findMedicationsByNameWhenFirstNameAndLastNameAreCorrectThenReturnTheMedications() {

        List<String> expectedMedications = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");

        List<String> actualMedications = medicalRecordRepository.findMedicationsByName("John", "Boyd");

        assertEquals(expectedMedications, actualMedications);
    }


    @Test
    @DisplayName("Should return null when the first name and last name are incorrect")
    void findMedicationsByNameWhenFirstNameAndLastNameAreIncorrectThenReturnNull() {

        List<String> medications = medicalRecordRepository.findMedicationsByName("John", "Doe");

        assertNull(medications);
    }


    @Test
    @DisplayName("Should return the allergies when the first name and last name are correct")
    void findAllergiesByNameWhenFirstNameAndLastNameAreCorrectThenReturnTheAllergies() {

        List<String> expectedAllergies = List.of("nillacilan");

        List<String> actualAllergies = medicalRecordRepository.findAllergiesByName("John", "Boyd");

        assertEquals(expectedAllergies, actualAllergies);
    }


    @Test
    @DisplayName("Should return null when the patient does not exist")
    void findAllergiesByNameWhenPatientDoesNotExistThenReturnNull() {

        List<String> allergies = medicalRecordRepository.findAllergiesByName("John", "Wick");

        assertNull(allergies);
    }
}