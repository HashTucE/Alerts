package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.Log;
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
            log.error(Log.OBJECT_NULL);
            throw new ServerException("medical record is null");
        } else {
            log.debug(Log.OBJECT_CREATED);
            return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
        }
    }


    @PutMapping("/medicalRecord")
    public ResponseEntity<Void> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.updateMedicalRecord(medicalRecord);
        log.debug(Log.OBJECT_MODIFIED);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/medicalRecord")
    public ResponseEntity<Void> deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.deleteMedicalRecord(medicalRecord);
        log.debug(Log.OBJECT_MODIFIED);
        return ResponseEntity.noContent().build();
    }
}
