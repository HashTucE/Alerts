package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireStationServiceImpl implements FireStationService {


    @Autowired
    FireStationRepository fireStationRepository = new FireStationRepository();

    private static final Logger log = LogManager.getLogger(FireStationServiceImpl.class);



    @Override
    public FireStation addFireStation(FireStation fireStation) {
        log.debug("addFireStation() from repository called");
        fireStationRepository.addFireStation(fireStation);
        return fireStation;
    }

    @Override
    public void updateFireStation(FireStation fireStation) {
        log.debug("updateFireStation() from repository called");
        fireStationRepository.updateFireStation(fireStation);
    }

    @Override
    public void deleteFireStation(FireStation fireStation) {
        log.debug("deleteFireStation() from repository called");
        fireStationRepository.deleteFireStation(fireStation);
    }
}
