package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;

import java.util.List;

public interface PersonService {



    void addPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Person person);

    Person findPerson(String firstName, String lastName);



    List<Person> findAllPersons();





}
