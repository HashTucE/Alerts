package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {

    private static final Logger log = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    private MedicalRecordService medicalRecordService;
    MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }


    /*
     * Add a medical record
     */
    @PostMapping("/medicalRecord")
    public void addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.addMedicalRecord(medicalRecord);
        log.debug("Object created successfully. Returning status 201. (Ok)");

    }


    /*
     * Update an existing medical record (all fields except names can be modified)
     */
    @PutMapping("/medicalRecord")
    public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.updateMedicalRecord(medicalRecord);
        log.debug("Object updated successfully. Returning status 200 (Ok).");
    }


    /*
     * remove a medical record (use first and last name as an identifier unique)
     */
    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.deleteMedicalRecord(medicalRecord);
        log.debug("Object deleted successfully. Returning status 200 (Ok).");
    }


    /*
     * find all medical records
     */
    @GetMapping("/medicalRecords")
    public List<MedicalRecord> findAll() {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return medicalRecordService.findAll();

    }
}
