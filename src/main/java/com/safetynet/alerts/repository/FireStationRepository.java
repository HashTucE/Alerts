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



    /**
     * @return a list of fire stations containing address and station number
     */
    public List<FireStation> loadFireStationsList() {


        Any anyFireStation = any.get("firestations");
        List<FireStation> fireStationsList = new ArrayList<>();

        for (Any element : anyFireStation) {
            FireStation fireStation = new FireStation();
            fireStation.setAddress(String.valueOf(element.get("address")));
            fireStation.setStation(Integer.valueOf(String.valueOf(element.get("station"))));
            fireStationsList.add(fireStation);
        }
        log.debug("Fire stations list loaded");
        return fireStationsList;
    }



    /**
     * Add a mapping fire station/address
     * @param fireStation fire station to add
     */
    public void addFireStation(FireStation fireStation) {
        if (fireStationsList
                .stream()
                .anyMatch(f -> f.getAddress().equals(fireStation.getAddress()))) {
            throw new IllegalArgumentException("Fire Station Already Exist !");
        } else {
            fireStationsList.add(fireStation);
            log.info("Fire Station Added");
        }
    }



    /**
     * update the fire station number of an address
     * @param fireStation fire station to update
     */
    public void updateFireStation(FireStation fireStation) {
        FireStation updateFireStation = fireStationsList
                .stream()
                .filter(f -> f.getAddress().equals(fireStation.getAddress()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Fire Station not found !"));

        fireStationsList.set(fireStationsList.indexOf(updateFireStation), fireStation);
        log.info("Fire station updated");
    }



    /**
     * delete the mapping of a fire station or an address
     * @param fireStation fire station to delete
     */
    public void deleteFireStation(FireStation fireStation) {
        FireStation deleteFireStation = fireStationsList
                .stream()
                .filter(f -> f.getAddress().equals(fireStation.getAddress()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Fire Station not found !"));

        fireStationsList.remove(deleteFireStation);
        log.info("Fire station deleted");
    }



    public List<FireStation> findAll() {
        log.info("Fire stations list loaded");
        return fireStationsList;
    }
}
