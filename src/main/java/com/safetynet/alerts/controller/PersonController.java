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

import java.rmi.ServerException;
import java.util.List;

@RestController
public class PersonController {

    private static final Logger log = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;



    //POST, PUT, DELETE CONTROLLERS//


    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) throws ServerException {
        Person newPerson = personService.addPerson(person);
        if (newPerson == null) {
            log.error("Object null cannot be created. Returning status 500. (Internal Server)");
            throw new ServerException("person is null");
        } else {
            log.debug("Object created successfully. Returning status 201. (Ok)");
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        }
    }



    @PutMapping("/person")
    public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
        log.debug("Object updated successfully. Returning status 200 (Ok).");
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/person")
    public ResponseEntity<Void> deletePerson(@RequestBody Person person) {
        personService.deletePerson(person);
        log.debug("Object deleted successfully. Returning status 200 (Ok).");
        return ResponseEntity.noContent().build();
    }



    //SPECIFIC CONTROLLERS//


    @GetMapping("/firestation")
    public ResponseEntity<FireCoverageByStation> getCoverageListFromStation(@RequestParam Integer stationNumber) {
        FireCoverageByStation fireCoverageByStation = personService.getCoverageListFromStation(stationNumber);
        if (fireCoverageByStation.getPersonContactList().isEmpty()) {
            log.error("Object not founded. Returning status 404 (Not Found).");
            return ResponseEntity.notFound().build();
        }
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return new ResponseEntity<>(fireCoverageByStation, HttpStatus.OK);

    }



    @GetMapping("/childAlert")
    public ResponseEntity<ChildByFamily> getChildrenListByAddress(@RequestParam String address) {
        ChildByFamily childByFamily = personService.getChildrenListByAddress(address);
        if (childByFamily.getChildren().isEmpty()) {
            log.error("Object not founded. Returning status 404 (Not Found).");
            return ResponseEntity.notFound().build();
        }
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return new ResponseEntity<>(childByFamily, HttpStatus.OK);
    }



    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getPhoneListFromStation(@RequestParam Integer firestation) {
        List<String> phoneList = personService.getPhoneListFromStation(firestation);
        if (phoneList.isEmpty()) {
            log.error("Object not founded. Returning status 404 (Not Found).");
            return ResponseEntity.notFound().build();
        }
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return new ResponseEntity<>(phoneList, HttpStatus.OK);
    }



    @GetMapping("/fire")
    public ResponseEntity<FireCoverageByAddress> getInhabitantsByAddress(@RequestParam String address) {
        FireCoverageByAddress fireCoverageByAddress = personService.getInhabitantsByAddress(address);
        if (fireCoverageByAddress.getPersonsHealthList().isEmpty()) {
            log.error("Object not founded. Returning status 404 (Not Found).");
            return ResponseEntity.notFound().build();
        }
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return new ResponseEntity<>(fireCoverageByAddress, HttpStatus.OK);
    }



    @GetMapping("/flood/stations")
    public ResponseEntity<List<FloodCoverage>> getInhabitantsByStation(@RequestParam List<Integer> stations) {
        List<FloodCoverage> inhabitantsList = personService.getInhabitantsByStation(stations);
        if (inhabitantsList.isEmpty()) {
            log.error("Object not founded. Returning status 404 (Not Found).");
            return ResponseEntity.notFound().build();
        }
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return new ResponseEntity<>(inhabitantsList, HttpStatus.OK);
    }



    @GetMapping("/personInfo")
    public ResponseEntity<List<PersonInfo>> findPersonInfoByName(@RequestParam String firstName, @RequestParam String lastName) {
        List<PersonInfo> personInfoList = personService.findPersonInfoByName(firstName, lastName);
        if (personInfoList.isEmpty()) {
            log.error("Object not founded. Returning status 404 (Not Found).");
            return ResponseEntity.notFound().build();
        }
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return new ResponseEntity<>(personInfoList, HttpStatus.OK);
    }



    @GetMapping("/communityEmail")
    public ResponseEntity<List<String>> findAllEmailsByCity(@RequestParam String city) {
        List<String> emailsList = personService.findAllEmailsByCity(city);
        if (emailsList.isEmpty()) {
            log.error("Object not founded. Returning status 404 (Not Found).");
            return ResponseEntity.notFound().build();
        }
        log.debug("Object founded successfully. Returning status 200 (Ok).");
        return new ResponseEntity<>(emailsList, HttpStatus.OK);
    }
}
