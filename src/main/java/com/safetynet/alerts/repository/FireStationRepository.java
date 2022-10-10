package com.safetynet.alerts.repository;

import com.jsoniter.any.Any;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.util.DataReader;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
@Data
public class FireStationRepository {


    private static final Logger log = LogManager.getLogger(FireStationRepository.class);

    private List<FireStation> fireStationsList = loadFireStationsList();

    private static Any any = DataReader.jsonReader();



    public List<FireStation> loadFireStationsList() {


        Any anyFireStation = any.get("firestations");
        List<FireStation> fireStationsList = new ArrayList<>();

        for (Any element : anyFireStation) {
            FireStation fireStation = new FireStation();
            fireStation.setAddress(String.valueOf(element.get("address")));
            fireStation.setStation(Integer.valueOf(String.valueOf(element.get("station"))));
            fireStationsList.add(fireStation);
        }
        log.debug("Fire stations loaded");
        return fireStationsList;
    }


    public FireStation addFireStation(FireStation fireStation) {
        if (fireStationsList.stream().anyMatch(f -> f.getAddress().equals(fireStation.getAddress()))) {
            throw new IllegalArgumentException("Fire Station Already Exist !");
        } else {
            fireStationsList.add(fireStation);
            log.info("Fire Station Added !");
            return fireStation;
        }
    }


    public FireStation updateFireStation(FireStation fireStation) {
        FireStation updateFireStation = fireStationsList.stream()
                .filter(f -> f.getAddress().equals(fireStation.getAddress()))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Fire Station not found !"));

        fireStationsList.set(fireStationsList.indexOf(updateFireStation), fireStation);
        log.info("Fire Station modified !");
        return fireStation;
    }


    public void deleteFireStation(FireStation fireStation) {
        FireStation deleteFireStation = fireStationsList.stream()
                .filter(f -> f.getAddress().equals(fireStation.getAddress()))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Fire Station not found !"));

        fireStationsList.remove(deleteFireStation);
        log.info("Fire Station deleted !");
    }


    public List<FireStation> findAll() {
        log.info("Fire Stations loaded !");
        return fireStationsList;
    }
}
