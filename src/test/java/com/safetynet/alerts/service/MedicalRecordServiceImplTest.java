package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceImplTest {

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    MedicalRecordServiceImpl medicalRecordService;


    private final MedicalRecord medicalRecord = new MedicalRecord("any", "any", "any",
            List.of("any"), List.of("any"));



    @Test
    @DisplayName("Should add the medical record")
    void addMedicalRecordTest() {

        medicalRecordService.addMedicalRecord(medicalRecord);

        verify(medicalRecordRepository, times(1)).addMedicalRecord(medicalRecord);
    }


    @Test
    @DisplayName("Should update the medical record")
    void updateMedicalRecordTest() {

        medicalRecordService.updateMedicalRecord(medicalRecord);

        verify(medicalRecordRepository, times(1)).updateMedicalRecord(medicalRecord);
    }


    @Test
    @DisplayName("Should delete the medical record")
    void deleteMedicalRecordTest() {

        medicalRecordService.deleteMedicalRecord(medicalRecord);

        verify(medicalRecordRepository, times(1)).deleteMedicalRecord(medicalRecord);
    }
}