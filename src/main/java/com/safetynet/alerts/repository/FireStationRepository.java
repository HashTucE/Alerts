package com.safetynet.alerts.repository;

import com.jsoniter.any.Any;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.util.DataReader;
import com.safetynet.alerts.util.DataWriter;
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

    private static DataWriter dataWriter = new DataWriter();



    /**
     * @return a list of fire stations containing address and station number
     */
    public List<FireStation> loadFireStationsList() {

        Any any = DataReader.jsonReader();
        Any anyFireStation = any.get("firestations");
        List<FireStation> fireStationsList = new ArrayList<>();

        for (Any element : anyFireStation) {
            FireStation fireStation = new FireStation();
            fireStation.setAddress(String.valueOf(element.get("address")));
            fireStation.setStation(Integer.valueOf(String.valueOf(element.get("station"))));
            fireStationsList.add(fireStation);
        }
        log.debug("Trying to return the fire station's list");
        return fireStationsList;
    }



    /**
     * Add a mapping fire station/address
     * @param fireStation fire station to add
     */
    public void addFireStation(FireStation fireStation) {
        if (dataWriter.getFireStationList()
                .stream()
                .anyMatch(f -> f.getAddress().equals(fireStation.getAddress()))) {
            throw new IllegalArgumentException("The fire station to the address " + fireStation.getAddress() + " already exist");
        } else {
            dataWriter.getFireStationList().add(fireStation);
            dataWriter.jsonWriter();
            log.info("Adding the fire station at the " + fireStation.getAddress());
        }
    }



    /**
     * update the fire station number of an address
     * @param fireStation fire station to update
     */
    public void updateFireStation(FireStation fireStation) {
        FireStation updateFireStation = dataWriter.getFireStationList()
                .stream()
                .filter(f -> f.getAddress().equals(fireStation.getAddress()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("The fire station to the address " + fireStation.getAddress() + " was not found"));

        dataWriter.getFireStationList().set(dataWriter.getFireStationList().indexOf(updateFireStation), fireStation);
        dataWriter.jsonWriter();
        log.info("Updating the fire station at the " + fireStation.getAddress());
    }



    /**
     * delete the mapping of a fire station or an address
     * @param fireStation fire station to delete
     */
    public void deleteFireStation(FireStation fireStation) {
        FireStation deleteFireStation = dataWriter.getFireStationList()
                .stream()
                .filter(f -> f.getAddress().equals(fireStation.getAddress()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("The fire station to the address " + fireStation.getAddress() + " was not found"));

        dataWriter.getFireStationList().remove(deleteFireStation);
        dataWriter.jsonWriter();
        log.info("Deleting the fire station at the " + fireStation.getAddress());
    }



    public List<FireStation> findAll() {
        log.info("Fire stations list loaded");
        return loadFireStationsList();
    }
}
