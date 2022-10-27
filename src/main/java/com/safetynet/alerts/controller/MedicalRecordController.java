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


    /**
     * Create a medicalRecord
     * @param medicalRecord
     * @return medicalRecord with HTTP code 201 created
     * @throws ServerException
     */
    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws ServerException {
        MedicalRecord newMedicalRecord = medicalRecordService.addMedicalRecord(medicalRecord);
        if (newMedicalRecord == null) {
            log.error(Log.OBJECT_NULL);
            throw new ServerException("medical record is null");
        } else {
            log.info(Log.OBJECT_CREATED);
            return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
        }
    }


    /**
     * update the medicalRecord
     * @param medicalRecord
     * @return HTTP code 204 no content
     */
    @PutMapping("/medicalRecord")
    public ResponseEntity<Void> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.updateMedicalRecord(medicalRecord);
        log.info(Log.OBJECT_MODIFIED);
        return ResponseEntity.noContent().build();
    }


    /**
     * Delete the medicalRecord
     * @param medicalRecord
     * @return HTTP code 204 no content
     */
    @DeleteMapping("/medicalRecord")
    public ResponseEntity<Void> deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.deleteMedicalRecord(medicalRecord);
        log.info(Log.OBJECT_MODIFIED);
        return ResponseEntity.noContent().build();
    }
}
