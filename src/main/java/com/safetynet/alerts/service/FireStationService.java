package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;

import java.util.List;

public interface FireStationService {


    FireStation addFireStation(FireStation firestation);

    FireStation updateFireStation(FireStation firestation);

    void deleteFireStation(FireStation firestation);


    List<FireStation> findAllFireStations();



    List<String> findFireStationAddressesByNumber(Integer fireStationNumber);


    List<String> findAddressesByNumber(Integer firestationNumber);

    Integer findStationNumberByAddress(String address);




}
