package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;

public interface FireStationService {


    FireStation addFireStation(FireStation firestation);

    void updateFireStation(FireStation firestation);

    void deleteFireStation(FireStation firestation);



}
