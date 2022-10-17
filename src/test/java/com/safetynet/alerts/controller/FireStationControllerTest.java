package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;
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
public class FireStationControllerTest {



    @Mock
    private FireStationService fireStationService;

    @InjectMocks
    private FireStationController fireStationController;


//    @Test
//    @DisplayName("Should return a 500 status code when the FireStation is null")
//    void addFireStationWhenNullThenReturn500() throws ServerException {
//        FireStation fireStation = null;
//        when(fireStationService.addFireStation(fireStation)).thenReturn(null);
//        assertEquals(
//                HttpStatus.INTERNAL_SERVER_ERROR,
//                fireStationController.addFireStation(fireStation).getStatusCode());
//    }

    @Test
    @DisplayName("Should return a 201 status code when the FireStation is created")
    void addFireStationWhenCreatedThenReturn201() throws ServerException {
        FireStation fireStation = new FireStation("135 Test St", 21);
        when(fireStationService.addFireStation(fireStation)).thenReturn(fireStation);
        ResponseEntity<FireStation> responseEntity =
                fireStationController.addFireStation(fireStation);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should update the FireStation")
    void updateFireStation() {
        FireStation fireStation = new FireStation("135 Test St", 21);

        fireStationController.updateFireStation(fireStation);

        verify(fireStationService, times(1)).updateFireStation(fireStation);
    }

    @Test
    @DisplayName("Should delete the FireStation")
    void deleteFireStation() {
        FireStation fireStation = new FireStation("135 Test St", 21);

        fireStationController.deleteFireStation(fireStation);

        verify(fireStationService, times(1)).deleteFireStation(fireStation);
    }
}

