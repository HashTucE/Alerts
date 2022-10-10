package com.safetynet.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;


    public MedicalRecord(final String birthdate,
                         final List<String> medication,
                         final List<String> allergies) {
        this.birthdate = birthdate;
        this.medications = medication;
        this.allergies = allergies;
    }

    public MedicalRecord(final String birthdate) {
        this.birthdate = birthdate;
    }

}
