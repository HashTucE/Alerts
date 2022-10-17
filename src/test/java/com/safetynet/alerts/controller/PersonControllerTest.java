package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.rmi.ServerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {


    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;


//    @Test
//    @DisplayName("Should return a 500 status code when the Person is null")
//    void addPersonWhenNullThenReturn500() throws ServerException {
//        Person person = null;
//        when(personService.addPerson(person)).thenReturn(null);
//        assertEquals(
//                HttpStatus.INTERNAL_SERVER_ERROR,
//                personController.addPerson(person).getStatusCode());
//    }

    @Test
    @DisplayName("Should return a 201 status code when the Person is created")
    void addPersonWhenCreatedThenReturn201() throws ServerException {
        Person person = new Person(
                        "Danny",
                        "Boyle",
                        "1509 Culver St",
                        "Culver",
                        "97451",
                        "841-874-6512",
                        "jaboyd@email.com");
        when(personService.addPerson(person)).thenReturn(person);
        ResponseEntity<Person> responseEntity =
                personController.addPerson(person);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should update the Person")
    void updatePerson() {
        Person person = new Person(
                        "Danny",
                        "Boyle",
                        "15 Culver St",
                        "Culver",
                        "97451",
                        "841-874-6512",
                        "jaboyd@email.com");

        personController.updatePerson(person);

        verify(personService, times(1)).updatePerson(person);
    }

    @Test
    @DisplayName("Should delete the Person")
    void deletePerson() {
        Person person = new Person(
                        "Danny",
                        "Boyle",
                        "15 Culver St",
                        "Culver",
                        "97451",
                        "841-874-6512",
                        "jaboyd@email.com");

        personController.deletePerson(person);

        verify(personService, times(1)).deletePerson(person);
    }
}
