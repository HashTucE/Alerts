package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FloodCoverage;
import com.safetynet.alerts.dto.PersonAge;
import com.safetynet.alerts.dto.PersonInfo;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.util.AgeCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SpecificServiceImplTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @Mock
    FireStationService fireStationService;

    @Mock
    AgeCalculator ageCalculator;

    @InjectMocks
    SpecificServiceImpl specificService;

//    @Test
//    @DisplayName("Should return a list of person contact when the station exists")
//    void getCoverageListFromStationWhenStationExistsThenReturnPersonContactList() {
//        Integer station = 1;
//        List<PersonInfo> personInfoList = specificService.loadPersonInfoList();
//        assertNotNull(personInfoList);
//    }

//    @Test
//    @DisplayName("Should return an empty list when there is no children in the family")
//    void getChildrenListByAddressWhenThereIsNoChildrenInTheFamilyThenReturnEmptyList() {
//        String address = "1509 Culver St";
//        List<PersonAge> childrenList =
//                specificService.getChildrenListByAddress(address).getChildren();
//        System.out.println(childrenList);
//        assertTrue(childrenList.isEmpty());
//    }

//    @Test
//    @DisplayName("Should return a list of phone numbers when the station number is valid")
//    void getPhoneListFromStationWhenStationNumberIsValidThenReturnListOfPhoneNumbers() {
//        Integer station = 2;
//        List<String> phoneList = specificService.getPhoneListFromStation(station);
//        assertNotNull(phoneList);
//        assertEquals(2, phoneList.size());
//    }

//    @Test
//    @DisplayName("Should return a list of personhealth when the address exists")
//    void getInhabitantsByAddressWhenAddressExistsThenReturnListOfPersonHealth() {
//        String address = "1509 Culver St";
//        List<PersonInfo> personInfoList = specificService.loadPersonInfoList();
//        assertEquals(2, personInfoList.size());
//    }

    @Test
    @DisplayName("Should return a list of floodcoverage when the stations list is not empty")
    void getInhabitantsByStationWhenStationsListIsNotEmpty() {
        List<Integer> stations = List.of(1, 2, 3);
        List<FloodCoverage> floodCoverageList = specificService.getInhabitantsByStation(stations);
        assertNotNull(floodCoverageList);
    }

    @Test
    @DisplayName("Should return a list of personinfo when the last name is given")
    void findPersonInfoByNameWhenLastNameIsGiven() {
        String firstName = "";
        String lastName = "Boyd";
        List<PersonInfo> personInfoList = specificService.findPersonInfoByName(firstName, lastName);
        assertEquals(6, personInfoList.size());
    }

    @Test
    @DisplayName("Should return a list of emails when the city is found")
    void findAllEmailsByCityWhenCityIsFoundThenReturnListOfEmails() {
        String email1 = "test1@test.test";
        String email2 = "test2@test.test";
        List<String> emails = specificService.findAllEmailsByCity("Paris");
        emails.add(email1);
        emails.add(email2);
        assertNotNull(emails);
        assertEquals(2, emails.size());
    }
}