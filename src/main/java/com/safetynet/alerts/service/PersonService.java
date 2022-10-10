package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;

import java.util.List;

public interface PersonService {


    Person addPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(Person person);


    Person findPerson(String firstName, String lastName);



    List<Person> findAllPersons();





}
