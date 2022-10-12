package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.*;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private static final Logger log = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;




    @PostMapping("/person")
    public void addPerson(@RequestBody Person person) {
        log.debug("Object created successfully. Returning status 201. (Ok)");
        personService.addPerson(person);
    }


    @PutMapping("/person")
    public void updatePerson(@RequestBody Person person) {
        log.debug("Object updated successfully. Returning status 200 (Ok).");
        personService.updatePerson(person);
    }


    @DeleteMapping("/person")
    public void deletePerson(@RequestBody Person person) {
        log.debug("Object deleted successfully. Returning status 200 (Ok).");
        personService.deletePerson(person);
    }


    @GetMapping("/person")
    public Person getPerson(@RequestParam String firstName, @RequestParam String lastName) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return personService.findPerson(firstName, lastName);
    }


    @GetMapping("/persons")
    public List<Person> getPersons() {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return personService.findAllPersons();
    }





    @GetMapping("/firestation")
    public ResponseEntity<FireCoverageByStation> getCoverageListFromStation(@RequestParam Integer stationNumber) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
//        return specificService.getCoverageListFromStation(stationNumber);
        return new ResponseEntity<FireCoverageByStation>(personService.getCoverageListFromStation(stationNumber), HttpStatus.OK);
    }

    @GetMapping("/childAlert")
    public ChildByFamily getChildrenListByAddress(@RequestParam String address) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return personService.getChildrenListByAddress(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhoneListFromStation(@RequestParam Integer firestation) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return personService.getPhoneListFromStation(firestation);
    }

    @GetMapping("/fire")
    public FireCoverageByAddress getInhabitantsByAddress(@RequestParam String address) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return personService.getInhabitantsByAddress(address);
    }

    @GetMapping("/flood/stations")
    public List<FloodCoverage> getInhabitantsByStation(@RequestParam List<Integer> stations) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return personService.getInhabitantsByStation(stations);
    }

    @GetMapping("/personInfo")
    public List<PersonInfo> findPersonInfoByName(@RequestParam String firstName, @RequestParam String lastName) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return personService.findPersonInfoByName(firstName, lastName);
    }

    @GetMapping("/communityEmail")
    public List<String> findAllEmailsByCity(@RequestParam String city) {
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return personService.findAllEmailsByCity(city);
    }

}
