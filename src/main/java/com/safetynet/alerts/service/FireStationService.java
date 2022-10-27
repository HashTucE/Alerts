package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;

public interface FireStationService {


    /**
     * Create a firestation
     * @param firestation
     * @return firestation
     */
    FireStation addFireStation(FireStation firestation);

    /**
     * Update a firestation
     * @param firestation
     */
    void updateFireStation(FireStation firestation);

    /**
     * Delete a firestation
     * @param firestation
     */
    void deleteFireStation(FireStation firestation);
}
