package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.rmi.ServerException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalRecordControllerTest {

    @Mock
    private MedicalRecordService medicalRecordService;

    @InjectMocks
    private MedicalRecordController medicalRecordController;


//    @Test
//    @DisplayName("Should return a 500 status code when the medical record is null")
//    void addMedicalRecordWhenNullThenReturn500() throws ServerException {
//        MedicalRecord medicalRecord = null;
//        when(medicalRecordService.addMedicalRecord(medicalRecord)).thenReturn(null);
//        assertEquals(
//                HttpStatus.INTERNAL_SERVER_ERROR,
//                medicalRecordController.addMedicalRecord(medicalRecord).getStatusCode());
//    }

    @Test
    @DisplayName("Should return a 201 status code when the medical record is created")
    void addMedicalRecordWhenCreatedThenReturn201() throws ServerException {
        MedicalRecord medicalRecord =
                new MedicalRecord(
                        "John",
                        "Boyd",
                        "03/06/1984",
                        Arrays.asList("aznol:350mg", "hydrapermazol:100mg"),
                        Arrays.asList("nillacilan"));
        when(medicalRecordService.addMedicalRecord(medicalRecord)).thenReturn(medicalRecord);
        ResponseEntity<MedicalRecord> responseEntity =
                medicalRecordController.addMedicalRecord(medicalRecord);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should update the medical record")
    void updateMedicalRecord() {
        MedicalRecord medicalRecord =
                new MedicalRecord(
                        "John",
                        "Boyd",
                        "03/06/1984",
                        Arrays.asList("aznol:350mg", "hydrapermazol:100mg"),
                        Arrays.asList("nillacilan"));

        medicalRecordController.updateMedicalRecord(medicalRecord);

        verify(medicalRecordService, times(1)).updateMedicalRecord(medicalRecord);
    }

    @Test
    @DisplayName("Should delete the medical record")
    void deleteMedicalRecord() {
        MedicalRecord medicalRecord =
                new MedicalRecord(
                        "John",
                        "Doe",
                        "01/01/2000",
                        Arrays.asList("medication1", "medication2"),
                        Arrays.asList("allergy1", "allergy2"));

        medicalRecordController.deleteMedicalRecord(medicalRecord);

        verify(medicalRecordService, times(1)).deleteMedicalRecord(medicalRecord);
    }
}