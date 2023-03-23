package com.safetynet.alerts.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireCoverageByStation {

    private List<PersonContact> personContactList;
    private int adultsNumber;
    private int childrenNumber;
}
