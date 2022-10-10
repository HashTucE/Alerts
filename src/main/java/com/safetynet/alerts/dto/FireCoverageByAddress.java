package com.safetynet.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireCoverageByAddress {

    private Integer station;
    private List<PersonHealth> personsHealthList;

    public FireCoverageByAddress(List<PersonHealth> personsHealthList) {
        this.personsHealthList = personsHealthList;
    }
}
