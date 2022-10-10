package com.safetynet.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonContact {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
}
