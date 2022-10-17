package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest {

    private PersonRepository personRepository = new PersonRepository();



    @Test
    @DisplayName("Should throw an exception when the person is already exist")
    void addPersonWhenPersonIsAlreadyExistThenThrowException() {

        Person person = new Person(
                        "John",
                        "Boyd",
                        "1509 Culver St",
                        "Culver",
                        "97451",
                        "841-874-6512",
                        "jaboyd@email.com");

        assertThrows(IllegalArgumentException.class, () -> personRepository.addPerson(person));
    }


    @Test
    @DisplayName("Should save the person when the person does not exist")
    void addPersonWhenPersonDoesNotExist() {

        Person person = new Person(
                        "John",
                        "Wick",
                        "1509 Culver St",
                        "Culver",
                        "97451",
                        "841-874-6512",
                        "jaboyd@email.com");

        personRepository.addPerson(person);

        assertEquals(person, personRepository.findPerson("John", "Wick"));

        personRepository.deletePerson(person);
    }


    @Test
    @DisplayName("Should throw an exception when the person is not found")
    void updatePersonWhenPersonIsNotFoundThenThrowException() {
        Person person = new Person(
                "John",
                "Hammond",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        assertThrows(IllegalArgumentException.class, () -> personRepository.updatePerson(person));
    }


    @Test
    @DisplayName("Should update the person when the person is found")
    void updatePersonWhenPersonIsFound() {
        Person person = new Person(
                "James",
                "Bond",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        personRepository.addPerson(person);

        Person personUpdated = new Person(
                "James",
                "Bond",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "007@email.com");
        personRepository.updatePerson(personUpdated);

        assertEquals(personUpdated, personRepository.findPerson("James", "Bond"));

        personRepository.deletePerson(personUpdated);
    }


    @Test
    @DisplayName("Should throw an exception when the person is not found")
    void deletePersonWhenPersonIsNotFoundThenThrowException() {

        Person person = new Person(
                        "John",
                        "Doe",
                        "1509 Culver St",
                        "Culver",
                        "97451",
                        "841-874-6512",
                        "jaboyd@email.com");

        assertThrows(IllegalArgumentException.class, () -> personRepository.deletePerson(person));
    }


    @Test
    @DisplayName("Should delete the person when the person is found")
    void deletePersonWhenPersonIsFound() {

        Person person = new Person(
                        "John",
                        "David",
                        "1509 Culver St",
                        "Culver",
                        "97451",
                        "841-874-6512",
                        "jaboyd@email.com");
        personRepository.addPerson(person);
        personRepository.deletePerson(person);
        assertThrows(
                IllegalArgumentException.class, () -> personRepository.findPerson("John", "David"));
    }


    @Test
    @DisplayName("Should throw an exception when the person does not exist")
    void findPersonWhenPersonDoesNotExistThenThrowException() {
        String firstName = "John";
        String lastName = "Doe";

        assertThrows(IllegalArgumentException.class, () -> personRepository.findPerson(firstName, lastName));
    }


    @Test
    @DisplayName("Should return the person when the person exists")
    void findPersonWhenPersonExists() {
        Person person = new Person(
                        "John",
                        "Boyd",
                        "1509 Culver St",
                        "Culver",
                        "97451",
                        "841-874-6512",
                        "jaboyd@email.com");

        Person result = personRepository.findPerson("John", "Boyd");

        assertEquals(person, result);
    }
}