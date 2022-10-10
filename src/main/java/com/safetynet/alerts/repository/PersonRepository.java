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
        log.debug("Persons loaded");
        return personsList;
    }


    // CRUD

    public Person addPerson(Person person) {
        if (personsList.stream().anyMatch(eachP -> eachP.getFirstName().equals(person.getFirstName())
                && eachP.getLastName().equals(person.getLastName()))) {
            throw new IllegalArgumentException("Person not found !");
        } else {
            personsList.add(person);
            log.info("Person added");
            return person;
        }
    }


    public Person findPerson(String firstName, String lastName) {
        return personsList.stream().filter(eachP -> eachP.getFirstName().equals(firstName)
                        && eachP.getLastName().equals(lastName))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Person not found !"));
    }


    public Person updatePerson(Person person) {
        Person updatePerson = personsList.stream()
                .filter(p -> p.getFirstName().equals(person.getFirstName())
                        && p.getLastName().equals(person.getLastName()))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Person not found !"));
        personsList.set(personsList.indexOf(updatePerson), person);
        log.info("Person updated");
        return person;
    }


    public void deletePerson(Person person) {
        Person deletePerson = personsList.stream()
                .filter(eachP -> eachP.getFirstName().equals(person.getFirstName())
                        && eachP.getLastName().equals(person.getLastName()))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Person not found !"));
        personsList.remove(deletePerson);
        log.info("Person deleted");
    }


    // FIND

    public List<Person> findAllPersons() {
        return personsList;
    }


//    public List<Person> findAllPersonsByAddress(String address) {
//        List<Person> listByAddress = new ArrayList<>();
//        for (Person person : personsList) {
//            if (person.getAddress().equals((address))) listByAddress.add(person);
//        }
//        log.debug("Persons found by address");
//        return listByAddress;
//    }
//
//
//    public List<Person> findAllPersonsByCity(String city) {
//        List<Person> listByCity = new ArrayList<>();
//        for (Person person : personsList) {
//            if (city.equals(person.getCity())) listByCity.add(person);
//        }
//        log.debug("Persons found by city");
//        return listByCity;
//    }

//    public List<String> findAllEmailsByCity(String city) {
//        List<String> emailListByCity = new ArrayList<>();
//        for (Person person : personsList) {
//            if (city.equals(person.getCity())) emailListByCity.add(person.getEmail());
//        }
//        List<String> listWithoutDuplication = emailListByCity.stream().distinct().collect(Collectors.toList());
//        log.debug("Emails found by city");
//        return listWithoutDuplication;
//    }
}

