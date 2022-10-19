package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FireStationRepositoryTest {


        private final FireStationRepository fireStationRepository = new FireStationRepository();



    @Test
    @DisplayName("Should throw an exception when the FireStation is already existing")
    void addFireStationWhenFireStationIsAlreadyExistingThenThrowException() {

        FireStation fireStation = new FireStation("1509 Culver St", 3);

        assertThrows(IllegalArgumentException.class, () -> fireStationRepository.addFireStation(fireStation));
    }


    @Test
    @DisplayName("Should save the FireStation when the FireStation does not exist")
    void addFireStationWhenFireStationDoesNotExist() {

        FireStation fireStation = new FireStation("23 Culver S", 23);
        List<String> address = Collections.singletonList(fireStation.getAddress());

        fireStationRepository.addFireStation(fireStation);

        assertEquals(fireStation.getStation(), fireStationRepository.findFireStationNumberByAddress("23 Culver S"));
        assertEquals(address, fireStationRepository.findFireStationAddressesByNumber(23));
        fireStationRepository.deleteFireStation(fireStation);
    }


    @Test
    @DisplayName("Should throw an exception when the FireStation is not found")
    void updateFireStationWhenFireStationIsNotFoundThenThrowException() {
        FireStation fireStation = new FireStation("123 Test St", 6);

        assertThrows(IllegalArgumentException.class, () -> fireStationRepository.updateFireStation(fireStation));
    }


    @Test
    @DisplayName("Should update the FireStation when the FireStation is found")
    void updateFireStationWhenFireStationIsFound() {

        FireStation fireStation = new FireStation("124 Test St", 7);
        fireStationRepository.addFireStation(fireStation);

        FireStation fireStationUpdated = new FireStation("124 Test St", 8);
        fireStationRepository.updateFireStation(fireStationUpdated);

        assertEquals(fireStationUpdated.getStation(), fireStationRepository.findFireStationNumberByAddress("124 Test St"));
        fireStationRepository.deleteFireStation(fireStation);
    }


    @Test
    @DisplayName("Should throw an exception when the FireStation is not found")
    void deleteFireStationWhenFireStationIsNotFoundThenThrowException() {

        FireStation fireStation = new FireStation("125 Test St", 7);

        assertThrows(IllegalArgumentException.class, () -> fireStationRepository.deleteFireStation(fireStation));
    }


    @Test
    @DisplayName("Should delete the FireStation when the FireStation is found")
    void deleteFireStationWhenFireStationIsFound() {

        FireStation fireStation = new FireStation("126 Test St", 8);

        fireStationRepository.addFireStation(fireStation);
        fireStationRepository.deleteFireStation(fireStation);
        assertThrows(IllegalArgumentException.class, () -> fireStationRepository.deleteFireStation(fireStation));
    }


    @Test
    @DisplayName("Should return an empty list when the number does not exist")
    void findFireStationAddressesByNumberWhenNumberDoesNotExistThenReturnEmptyList() {

        List<String> addresses = fireStationRepository.findFireStationAddressesByNumber(30);

        assertTrue(addresses.isEmpty());
    }


    @Test
    @DisplayName("Should return the FireStation addresses when the number is correct")
    void findFireStationAddressesByNumberWhenNumberExists() {

        FireStation fireStation1 = new FireStation("127 Test St", 10);
        fireStationRepository.addFireStation(fireStation1);
        FireStation fireStation2 = new FireStation("128 Test St", 10);
        fireStationRepository.addFireStation(fireStation2);
        List<String> expected = fireStationRepository.findFireStationAddressesByNumber(10);

        assertEquals(Arrays.asList("127 Test St", "128 Test St"), expected);

        fireStationRepository.deleteFireStation(fireStation1);
        fireStationRepository.deleteFireStation(fireStation2);
    }


    @Test
    @DisplayName("Should return null number when the address does not exist")
    void findFireStationNumberByAddressWhenAddressDoesNotExistThenReturnNullNumber() {

        Integer number = fireStationRepository.findFireStationNumberByAddress("130 Test St");

        assertNull(number);
    }


    @Test
    @DisplayName("Should return the FireStation number when the address is correct")
    void findFireStationNumberByAddressWhenAddressExists() {

        FireStation fireStation = new FireStation("131 Test St", 10);
        fireStationRepository.addFireStation(fireStation);

        Integer expected = fireStationRepository.findFireStationNumberByAddress("131 Test St");

        assertEquals(10 , expected);

        fireStationRepository.deleteFireStation(fireStation);
    }

}
