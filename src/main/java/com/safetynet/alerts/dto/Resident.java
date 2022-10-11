package com.safetynet.alerts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resident {

    private String firstName;
    private String lastName;

    @JsonIgnore
    private String address;
    private String phone;
    private short age;
    private List<String> medications;
    private List<String> allergies;
}
