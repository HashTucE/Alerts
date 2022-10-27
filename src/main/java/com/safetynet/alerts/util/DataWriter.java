package com.safetynet.alerts.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.safetynet.alerts.constants.Path;
import com.safetynet.alerts.dto.DataBase;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Data
public class DataWriter {


    private static final Logger log = LogManager.getLogger(DataReader.class);


    PersonRepository personRepository = new PersonRepository();
    List<Person> personList = personRepository.loadPersonsList();

    FireStationRepository fireStationRepository = new FireStationRepository();
    List<FireStation> fireStationList = fireStationRepository.loadFireStationsList();

    MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
    List<MedicalRecord> medicalRecordList = medicalRecordRepository.loadMedicalRecordsList();


    /**
     * Write a json file
     */
    public void jsonWriter() {



        DataBase dataBase = new DataBase(personList, fireStationList, medicalRecordList);

        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(Path.DATA), dataBase);
            log.debug("Json file saved");
        } catch (IOException e) {
            log.error("Json file not saved");
            e.printStackTrace();
        }


    }
}