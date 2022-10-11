package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationController {

    private static final Logger log = LogManager.getLogger(FireStationController.class);

    @Autowired
    private FireStationService fireStationService;


    /*
     * Add a mapping fire station/address
     */
    @PostMapping("/firestation")
    public void addFireStation(@RequestBody FireStation fireStation) {
        log.debug("Object created successfully. Returning status 201. (Ok)");
        fireStationService.addFireStation(fireStation);
    }

    /*
     * update the fire station number of an address
     */
    @PutMapping("/firestation")
    public void updateFireStation(@RequestBody FireStation fireStation) {
        log.debug("Object updated successfully. Returning status 200 (Ok).");
        fireStationService.updateFireStation(fireStation);
    }

    /*
     * delete the mapping of a fire station or an address
     */
    @DeleteMapping("/firestation")
    public void deleteFireStation(@RequestBody FireStation fireStation) {
        fireStationService.deleteFireStation(fireStation);
        log.debug("Object deleted successfully. Returning status 200 (Ok).");
    }

    /*
     * find all fire stations
     */
    @GetMapping("/firestations")
    public List<FireStation> findAll() {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return fireStationService.findAllFireStations();
    }
}
