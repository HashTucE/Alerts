package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataBase {

    List<Person> persons;
    List<FireStation> firestations;
    List<MedicalRecord> medicalrecords;

}
