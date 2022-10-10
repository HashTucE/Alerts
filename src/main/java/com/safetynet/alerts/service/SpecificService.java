package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.*;

import java.util.List;

public interface SpecificService {


    List<PersonInfo> loadPersonInfoList();

//    List<PersonHealth> loadPersonHealthList();

    FireCoverageByStation getCoverageListFromStation(Integer station);

    ChildByFamily getChildrenListByAddress(String address);

    List<String> getPhoneListFromStation(Integer station);

    FireCoverageByAddress getInhabitantsByAddress(String address);

    List<FloodCoverage> getInhabitantsByStation(List<Integer> stations);

    List<PersonInfo> findPersonInfoByName(String firstName, String lastName);

    List<String> findAllEmailsByCity(String city);
}
