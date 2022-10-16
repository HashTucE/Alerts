package com.safetynet.alerts.util;

import com.safetynet.alerts.constants.Path;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class DataWriterTest {



    @Test
    @DisplayName("Should save the json file when the data is correct")
    void jsonWriterWhenDataIsCorrectThenSaveJsonFile() {

        DataWriter dataWriter = new DataWriter();
        dataWriter.setPersonList(
                List.of(
                        new Person(
                                "John",
                                "Boyd",
                                "1509 Culver St",
                                "Culver",
                                "97451",
                                "841-874-6512",
                                "jaboyd@email.com")));
        dataWriter.setFireStationList(List.of(new FireStation("1509 Culver St", 3)));
        dataWriter.setMedicalRecordList(
                List.of(
                        new MedicalRecord(
                                "John",
                                "Boyd",
                                "03/06/1984",
                                List.of("aznol:350mg", "hydrapermazol:100mg"),
                                List.of("nillacilan"))));

        dataWriter.jsonWriter();
        System.out.println(dataWriter);

        assertTrue(Files.exists(Paths.get(Path.DATA)));
    }




    //    @Test
//    @DisplayName("Should not save the json file when the data is incorrect")
//    void jsonWriterWhenDataIsIncorrectThenNotSaveJsonFile() {
//
//        DataWriter dataWriter = new DataWriter();
//        dataWriter.setPersonList(null);
//        dataWriter.setFireStationList(null);
//        dataWriter.setMedicalRecordList(null);
//
//        dataWriter.jsonWriter();
//
//        assertFalse(Files.exists(Paths.get(Path.DATA)));
//    }
}