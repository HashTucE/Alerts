package com.safetynet.alerts.repository;

import com.jsoniter.any.Any;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.util.DataReader;
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

    private List<Person> personsList = loadPersonsList();

    private static Any any = DataReader.jsonReader();


    /**
     * @return a list of persons containing fistName, lastName, address, city, zip, phone and email
     */
    public List<Person> loadPersonsList() {


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
        log.debug("Persons list loaded");
        return personsList;
    }


    /**
     * add a new person
     * @param person person to add
     */
    public void addPerson(Person person) {

        if (personsList
                .stream()
                .anyMatch(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))) {
            log.info("Person already exist !");
            throw new IllegalArgumentException("Person already exist !");
        } else {
            personsList.add(person);
            log.info("Person added");
        }
    }


    /**
     * update an existing person (all fields except names can be modified)
     * @param person person to update
     */
    public void updatePerson(Person person) {

        Person updatePerson = personsList
                .stream()
                .filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Person not found !"));
        personsList.set(personsList.indexOf(updatePerson), person);
        log.info("Person updated");
    }


    /**
     * remove a person (use first and last name as an identifier unique)
     * @param person person to delete
     */
    public void deletePerson(Person person) {

        Person deletePerson = personsList
                .stream()
                .filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Person not found !"));
        personsList.remove(deletePerson);
        log.info("Person deleted");
    }



    public Person findPerson(String firstName, String lastName) {

        return personsList
                .stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Person not found !"));
    }



    public List<Person> findAllPersons() {
        return personsList;
    }

}

