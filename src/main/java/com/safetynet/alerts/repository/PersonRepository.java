package com.safetynet.alerts.repository;

import com.jsoniter.any.Any;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.util.DataReader;
import com.safetynet.alerts.util.DataWriter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Data
@NoArgsConstructor
public class PersonRepository {


    private static final Logger log = LogManager.getLogger(PersonRepository.class);

    private static DataWriter dataWriter = new DataWriter();



    /**
     * @return a list of persons containing fistName, lastName, address, city, zip, phone and email
     */
    public List<Person> loadPersonsList() {

        Any any = DataReader.jsonReader();
        Any anyPersons = any.get("persons");
        List<Person> personsList = new ArrayList<>();

        for (Any element : anyPersons) {
            Person person = new Person();
            person.setFirstName(String.valueOf(element.get("firstName")));
            person.setLastName(String.valueOf(element.get("lastName")));
            person.setAddress(String.valueOf(element.get("address")));
            person.setCity(String.valueOf(element.get("city")));
            person.setZip(String.valueOf(element.get("zip")));
            person.setPhone(String.valueOf(element.get("phone")));
            person.setEmail(String.valueOf(element.get("email")));
            personsList.add(person);
        }
        log.debug("Trying to return the person's list");
        return personsList;
    }


    /**
     * add a new person
     * @param person person to add
     */
    public void addPerson(Person person) {

        if (dataWriter.getPersonList()
                .stream()
                .anyMatch(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))) {
            log.info("Person already exist !");
            throw new IllegalArgumentException("The contact information of " + person.getFirstName() + " " + person.getLastName() + " already exist");
        } else {
            dataWriter.getPersonList().add(person);
            dataWriter.jsonWriter();
            log.info("Adding the contact information for " + person.getFirstName() + " " + person.getLastName());
        }
    }


    /**
     * update an existing person (all fields except names can be modified)
     * @param person person to update
     */
    public void updatePerson(Person person) {

        Person updatePerson = dataWriter.getPersonList()
                .stream()
                .filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))
                .findAny().orElseThrow(() -> new IllegalArgumentException("The contact information of " + person.getFirstName() + " " + person.getLastName() + " was not found"));
        dataWriter.getPersonList().set(dataWriter.getPersonList().indexOf(updatePerson), person);
        dataWriter.jsonWriter();
        log.info("Updating the contact information for " + person.getFirstName() + " " + person.getLastName());
    }


    /**
     * remove a person (use first and last name as an identifier unique)
     * @param person person to delete
     */
    public void deletePerson(Person person) {

        Person deletePerson = dataWriter.getPersonList()
                .stream()
                .filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))
                .findAny().orElseThrow(() -> new IllegalArgumentException("The contact information of " + person.getFirstName() + " " + person.getLastName() + " was not found"));
        dataWriter.getPersonList().remove(deletePerson);
        dataWriter.jsonWriter();
        log.info("Deleting the contact information for " + person.getFirstName() + " " + person.getLastName());
    }



    public Person findPerson(String firstName, String lastName) {

        return dataWriter.getPersonList()
                .stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Person not found !"));
    }


//    //////////////////////
//    public List<Person> findAllPersons() {
//        return loadPersonsList();
//    }
//    //////////////////////
}

