package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private static final Logger log = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;



    /*
     * add a new person
     */
    @PostMapping("/person")
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        log.debug("Object created successfully. Returning status 201. (Ok)");
    }


    /*
     * update an existing person (all fields except names can be modified)
     */
    @PutMapping("/person")
    public void updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
        log.debug("Object updated successfully. Returning status 200 (Ok).");
    }


    /*
     * remove a person (use first and last name as an identifier unique)
     */
    @DeleteMapping("/person")
    public void deletePerson(@RequestBody Person person) {
        personService.deletePerson(person);
        log.debug("Object deleted successfully. Returning status 200 (Ok).");
    }


    @GetMapping("/person")
    public Person getPerson(@RequestParam String firstName, @RequestParam String lastName) {
        return personService.findPerson(firstName, lastName);
//        log.debug("Object founded successfully. Returning status 200 (Ok).");
    }


    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personService.findAllPersons();
//        log.debug("Object founded successfully. Returning status 200 (Ok).");
    }




//    /*
//     * @return a list of children (<= 18) living at this address.
//     * The list include each child's first and last name, age, and a list of other household members.
//     * If there are no children, return an empty string.
//     */
//    @GetMapping("/childAlert")
//    public List<ChildAlert> getChildAlert() {
//
//    }
//
//    /*
//     * @return the name, address, age, email address and medical history (medication, dosage, allergies) of each inhabitant.
//     * If multiple people have the same name, they all appear.
//     */
//    @GetMapping("/personInfo")
//    public List<PersonInfo> getPersonInfo() {
//
//    }
//
//    /*
//     * @return the email addresses of all the inhabitants of the city.
//     */
//    @GetMapping("/communityEmail")
//    public List<String> getCommunityEmail() {
//
//    }
//
}
