package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.*;
import com.safetynet.alerts.service.SpecificService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecificController {



    private static final Logger log = LogManager.getLogger(PersonController.class);


    @Autowired
    private SpecificService specificService;



    @GetMapping("/firestation")
    public FireCoverageByStation getCoverageListFromStation(@RequestParam Integer stationNumber) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return specificService.getCoverageListFromStation(stationNumber);
    }

    @GetMapping("/childAlert")
    public ChildByFamily getChildrenListByAddress(@RequestParam String address) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return specificService.getChildrenListByAddress(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhoneListFromStation(@RequestParam Integer firestation) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return specificService.getPhoneListFromStation(firestation);
    }

    @GetMapping("/fire")
    public FireCoverageByAddress getInhabitantsByAddress(@RequestParam String address) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return specificService.getInhabitantsByAddress(address);
    }

    @GetMapping("/flood/stations")
    public List<FloodCoverage> getInhabitantsByStation(@RequestParam List<Integer> stations) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return specificService.getInhabitantsByStation(stations);
    }

    @GetMapping("/personInfo")
    public List<PersonInfo> findPersonInfoByName(@RequestParam String firstName, @RequestParam String lastName) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return specificService.findPersonInfoByName(firstName, lastName);
    }

    @GetMapping("/communityEmail")
    public List<String> findAllEmailsByCity(@RequestParam String city) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return specificService.findAllEmailsByCity(city);
    }


}
