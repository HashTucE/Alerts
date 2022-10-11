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



    @PostMapping("/medicalRecord")
    public void addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.debug("Object created successfully. Returning status 201. (Ok)");
        medicalRecordService.addMedicalRecord(medicalRecord);
    }


    @PutMapping("/medicalRecord")
    public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.debug("Object updated successfully. Returning status 200 (Ok).");
        medicalRecordService.updateMedicalRecord(medicalRecord);
    }


    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.debug("Object deleted successfully. Returning status 200 (Ok).");
        medicalRecordService.deleteMedicalRecord(medicalRecord);
    }


    @GetMapping("/medicalRecords")
    public List<MedicalRecord> findAll() {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return medicalRecordService.findAll();

    }
}
