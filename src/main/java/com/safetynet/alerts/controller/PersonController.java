package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.Log;
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


    /**
     * Create a person
     * @param person
     * @return Person with HTTP code 201 created
     * @throws ServerException
     */
    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) throws ServerException {
        Person newPerson = personService.addPerson(person);
        if (newPerson == null) {
            log.error(Log.OBJECT_NULL);
            throw new ServerException("person is null");
        } else {
            log.info(Log.OBJECT_CREATED);
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        }
    }


    /**
     * Update a person
     * @param person
     * @return HTTP code 204 no content
     */
    @PutMapping("/person")
    public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
        log.info(Log.OBJECT_MODIFIED);
        return ResponseEntity.noContent().build();
    }


    /**
     * Delete a person
     * @param person
     * @return HTTP code 204 no content
     */
    @DeleteMapping("/person")
    public ResponseEntity<Void> deletePerson(@RequestBody Person person) {
        personService.deletePerson(person);
        log.info(Log.OBJECT_MODIFIED);
        return ResponseEntity.noContent().build();
    }



    //SPECIFIC CONTROLLERS//


    /**
     * get a json of fireCoverageByStation
     * @param stationNumber
     * @return fireCoverageByStation with HTTP code 200 ok
     */
    @GetMapping("/firestation")
    public ResponseEntity<FireCoverageByStation> getCoverageListFromStation(@RequestParam Integer stationNumber) {
        FireCoverageByStation fireCoverageByStation = personService.getCoverageListFromStation(stationNumber);
        if (fireCoverageByStation.getPersonContactList().isEmpty()) {
            log.error(Log.OBJECT_NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        log.info(Log.OBJECT_FOUND);
        return new ResponseEntity<>(fireCoverageByStation, HttpStatus.OK);

    }


    /**
     * get a json of childByFamily
     * @param address
     * @return childByFamily with HTTP code 200 ok
     */
    @GetMapping("/childAlert")
    public ResponseEntity<ChildByFamily> getChildrenListByAddress(@RequestParam String address) {
        ChildByFamily childByFamily = personService.getChildrenListByAddress(address);
        if (childByFamily.getChildren().isEmpty()) {
            log.error(Log.OBJECT_NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        log.info(Log.OBJECT_FOUND);
        return new ResponseEntity<>(childByFamily, HttpStatus.OK);
    }


    /**
     * get a json of phoneList
     * @param firestation
     * @return phoneList with HTTP code 200 ok
     */
    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getPhoneListFromStation(@RequestParam Integer firestation) {
        List<String> phoneList = personService.getPhoneListFromStation(firestation);
        if (phoneList.isEmpty()) {
            log.error(Log.OBJECT_NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        log.info(Log.OBJECT_FOUND);
        return new ResponseEntity<>(phoneList, HttpStatus.OK);
    }


    /**
     * get a json of fireCoverageByAddress
     * @param address
     * @return fireCoverageByAddress with HTTP code 200 ok
     */
    @GetMapping("/fire")
    public ResponseEntity<FireCoverageByAddress> getInhabitantsByAddress(@RequestParam String address) {
        FireCoverageByAddress fireCoverageByAddress = personService.getInhabitantsByAddress(address);
        if (fireCoverageByAddress.getPersonsHealthList().isEmpty()) {
            log.error(Log.OBJECT_NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        log.info(Log.OBJECT_FOUND);
        return new ResponseEntity<>(fireCoverageByAddress, HttpStatus.OK);
    }


    /**
     * get a json of inhabitantsList
     * @param stations
     * @return inhabitantsList with HTTP code 200 ok
     */
    @GetMapping("/flood/stations")
    public ResponseEntity<List<FloodCoverage>> getInhabitantsByStation(@RequestParam List<Integer> stations) {
        List<FloodCoverage> inhabitantsList = personService.getInhabitantsByStation(stations);
        if (inhabitantsList.isEmpty()) {
            log.error(Log.OBJECT_NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        log.info(Log.OBJECT_FOUND);
        return new ResponseEntity<>(inhabitantsList, HttpStatus.OK);
    }


    /**
     * get a json of personInfo
     * @param firstName
     * @param lastName
     * @return personInfoList with HTTP code 200 ok
     */
    @GetMapping("/personInfo")
    public ResponseEntity<List<PersonInfo>> findPersonInfoByName(@RequestParam String firstName, @RequestParam String lastName) {
        List<PersonInfo> personInfoList = personService.findPersonInfoByName(firstName, lastName);
        if (personInfoList.isEmpty()) {
            log.error(Log.OBJECT_NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        log.info(Log.OBJECT_FOUND);
        return new ResponseEntity<>(personInfoList, HttpStatus.OK);
    }


    /**
     * get a json emailList
     * @param city
     * @return emailsList with HTTP code 200 ok
     */
    @GetMapping("/communityEmail")
    public ResponseEntity<List<String>> findAllEmailsByCity(@RequestParam String city) {
        List<String> emailsList = personService.findAllEmailsByCity(city);
        if (emailsList.isEmpty()) {
            log.error(Log.OBJECT_NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        log.info(Log.OBJECT_FOUND);
        return new ResponseEntity<>(emailsList, HttpStatus.OK);
    }
}
