package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {


    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    private static final Logger log = LogManager.getLogger(MedicalRecordServiceImpl.class);



    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        log.debug("addMedicalRecord() from repository called !");
        medicalRecordRepository.addMedicalRecord(medicalRecord);
    }

    @Override
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        log.debug("updateMedicalRecord() from repository called !");
        medicalRecordRepository.updateMedicalRecord(medicalRecord);
    }

    @Override
    public void deleteMedicalRecord(MedicalRecord medicalRecord) {
        log.debug("deleteMedicalRecord() from repository called !");
        medicalRecordRepository.deleteMedicalRecord(medicalRecord);
    }



        @Override
    public List<MedicalRecord> findAll(){
        log.debug("findAll() from repository called !");
        return medicalRecordRepository.findAll();
    }
}
