package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.Log;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;

@RestController
public class FireStationController {

    private static final Logger log = LogManager.getLogger(FireStationController.class);

    @Autowired
    private FireStationService fireStationService;



    //POST, PUT, DELETE CONTROLLERS//


    /*
     * Add a mapping fire station/address
     */
    @PostMapping("/firestation")
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation fireStation) throws ServerException {
        FireStation newFireStation = fireStationService.addFireStation(fireStation);
        if (newFireStation == null) {
            log.error(Log.OBJECT_NULL);
            throw new ServerException("firestation is null");
        } else {
            log.info(Log.OBJECT_CREATED);
            return new ResponseEntity<>(newFireStation, HttpStatus.CREATED);
        }
    }

    /*
     * update the fire station number of an address
     */
    @PutMapping("/firestation")
    public ResponseEntity<Void> updateFireStation(@RequestBody FireStation fireStation) {
        fireStationService.updateFireStation(fireStation);
        log.info(Log.OBJECT_MODIFIED);
        return ResponseEntity.noContent().build();
    }

    /*
     * delete the mapping of a fire station or an address
     */
    @DeleteMapping("/firestation")
    public ResponseEntity<Void> deleteFireStation(@RequestBody FireStation fireStation) {
        fireStationService.deleteFireStation(fireStation);
        log.info(Log.OBJECT_MODIFIED);
        return ResponseEntity.noContent().build();
    }
}
