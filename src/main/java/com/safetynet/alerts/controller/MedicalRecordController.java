package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;

@RestController
public class MedicalRecordController {

    private static final Logger log = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    private MedicalRecordService medicalRecordService;



    //POST, PUT, DELETE CONTROLLERS//


    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws ServerException {
        MedicalRecord newMedicalRecord = medicalRecordService.addMedicalRecord(medicalRecord);
        if (newMedicalRecord == null) {
            log.error("Object null cannot be created. Returning status 500. (Internal Server)");
            throw new ServerException("medical record is null");
        } else {
            log.debug("Object created successfully. Returning status 201. (Ok)");
            return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
        }
    }


    @PutMapping("/medicalRecord")
    public ResponseEntity<Void> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.updateMedicalRecord(medicalRecord);
        log.debug("Object updated successfully. Returning status 200 (Ok).");
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/medicalRecord")
    public ResponseEntity<Void> deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.deleteMedicalRecord(medicalRecord);
        log.debug("Object deleted successfully. Returning status 200 (Ok).");
        return ResponseEntity.noContent().build();
    }
}
