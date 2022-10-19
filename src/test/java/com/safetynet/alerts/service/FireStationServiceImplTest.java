package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FireStationServiceImplTest {

    @Mock
    FireStationRepository fireStationRepository;

    @InjectMocks
    FireStationServiceImpl fireStationService;


    private final FireStation fireStation = new FireStation("any", 1);



    @Test
    @DisplayName("Should add the fireStation")
    void addFireStationTest() {

        fireStationService.addFireStation(fireStation);

        verify(fireStationRepository, times(1)).addFireStation(fireStation);
    }


    @Test
    @DisplayName("Should update the fireStation")
    void updateFireStationTest() {

        fireStationService.updateFireStation(fireStation);

        verify(fireStationRepository, times(1)).updateFireStation(fireStation);
    }


    @Test
    @DisplayName("Should delete the fireStation")
    void deleteFireStationTest() {

        fireStationService.deleteFireStation(fireStation);

        verify(fireStationRepository, times(1)).deleteFireStation(fireStation);
    }
}