package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest {

//    @Mock
    private PersonRepository personRepository;

//    @Test
//    @DisplayName("Should throw an exception when the person does not exist")
//    void findPersonWhenPersonDoesNotExistThenThrowException() {
//        when(personRepository.findPerson("John", "Doe")).thenThrow(IllegalArgumentException.class);
//        assertThrows(
//                IllegalArgumentException.class, () -> personRepository.findPerson("John", "Doe"));
//    }

//    @Test
//    @DisplayName("Should return the person when the person exists")
//    void findPersonWhenPersonExists() {
//        Person person =
//                new Person(
//                        "John",
//                        "Boyd",
//                        "1509 Culver St",
//                        "Culver",
//                        "97451",
//                        "841-874-6512",
//                        "jaboyd@email.com");
////        when(personRepository.findPerson("John", "Boyd")).thenReturn(person);
//        Person result = personRepository.findPerson("John", "Boyd");
//        assertEquals(person, result);
//    }
}