package com.safetynet.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonHealth {


    private String firstName;
    private String lastName;
    private String phone;
    private short age;
    private List<String> medications;
    private List<String> allergies;


}
