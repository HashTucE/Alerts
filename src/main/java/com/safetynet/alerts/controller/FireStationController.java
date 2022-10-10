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
    private final FireStationService fireStationService;
   FireStationController(FireStationService fireStationService) {
       this.fireStationService = fireStationService;
   }


    /*
     * Add a mapping fire station/address
     */
    @PostMapping("/firestation")
    public void addFireStation(@RequestBody FireStation fireStation) {
        fireStationService.addFireStation(fireStation);
        log.debug("Object created successfully. Returning status 201. (Ok)");
    }

    /*
     * update the fire station number of an address
     */
    @PutMapping("/firestation")
    public void updateFireStation(@RequestBody FireStation fireStation) {
        fireStationService.updateFireStation(fireStation);
        log.debug("Object updated successfully. Returning status 200 (Ok).");
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


//    /*
//     * @return a list of people covered by the corresponding fire station.
//     * List contains: names, address, telephone number.
//     * provides a count of the number of adults and the number of children (<= 18) in the service area.
//     */
//    @GetMapping("/firestation")
//    public PersonsCoverageStation firestationNumber() {
//
//    }
//
//    /*
//     * @return a list of phone numbers of residents served by the fire station.
//     * Used to send emergency text messages to specific households.
//     */
//    @GetMapping("/phoneAlert")
//    public List<String> phoneAlert() {
//
//    }
//
//
//    /*
//     * @return the list of inhabitants living at the given address and the number of the fire station serving it.
//     * List contains: names, phone number, age, and medical history (medications, dosages, and allergies).
//     */
//    @GetMapping("/fire")
//    public List<FireCase> fire() {
//
//    }
//
//
//    /*
//     * @return a list of all households (group people by address) served by the fire station.
//     * List contains: names, phone number, age, and medical history alongside.
//     */
//    @GetMapping("/flood/stations")
//    public List<FloodCase> flood() {
//
//    }




}
