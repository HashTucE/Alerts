package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationServiceImpl implements FireStationService {


    @Autowired
    FireStationRepository fireStationRepository = new FireStationRepository();

    private static final Logger log = LogManager.getLogger(FireStationServiceImpl.class);

    List<FireStation> fireStationsList = fireStationRepository.loadFireStationsList();



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




    ///////////
    @Override
    public List<FireStation> findAllFireStations() {
        log.debug("findAll() from repository called");
        return fireStationRepository.findAll();
    }
    ///////////






    @Override
    public List<String> findFireStationAddressesByNumber(Integer fireStationNumber) {
//
        List<String> addressesList = new ArrayList<>();
        for (FireStation fireStation : fireStationsList) {
            if (fireStation.getStation().equals((fireStationNumber))) addressesList.add(fireStation.getAddress());
        }
        log.debug("Trying to return the firestation's adresses list for the station number " + fireStationNumber);
        return addressesList;
    }

    @Override
    public List<String> findAddressesByNumber(Integer firestationNumber) {

        List<String> adressesList = new ArrayList<>();
        for (FireStation fireStation : fireStationsList) {
            if (fireStation.getStation().equals(firestationNumber))
                adressesList.add(fireStation.getAddress());
        }
        log.debug("Trying to return the adresses list for the station number " + firestationNumber);
        return adressesList;
    }

    @Override
    public Integer findStationNumberByAddress(String address) {

        Integer station = 0;
        for (FireStation fireStation : fireStationsList) {
            if (fireStation.getAddress().equals(address)) {station = fireStation.getStation();}
        }
        log.debug("Trying to return the station number for " + address);
        return station;
    }


}
