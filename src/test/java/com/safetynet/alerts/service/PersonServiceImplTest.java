package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.*;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.util.AgeCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @Mock
    FireStationRepository fireStationRepository;

    @Mock
    AgeCalculator ageCalculator;

    @InjectMocks
    PersonServiceImpl personService;




    private final Person person = new Person("any", "any", "any", "any", "any", "any", "any");



    @Test
    @DisplayName("Should add the person")
    void addPersonTest() {

        personService.addPerson(person);

        verify(personRepository, times(1)).addPerson(person);
    }


    @Test
    @DisplayName("Should update the person")
    void updatePersonTest() {

        personService.updatePerson(person);

        verify(personRepository, times(1)).updatePerson(person);
    }


    @Test
    @DisplayName("Should delete the person")
    void deletePersonTest() {

        personService.deletePerson(person);

        verify(personRepository, times(1)).deletePerson(person);
    }








    @Test
    @DisplayName("Should return an empty list when the station does not exist")
    void getCoverageListFromStationWhenStationDoesNotExistThenReturnEmptyList() {

        when(fireStationRepository.findFireStationAddressesByNumber(1)).thenReturn(List.of());

        FireCoverageByStation fireCoverageByStation = personService.getCoverageListFromStation(1);

        assertEquals(new FireCoverageByStation(List.of(), 0, 0), fireCoverageByStation);
    }



    @Test
    @DisplayName("Should return a FireCoverageByStation when the station exists")
    void getCoverageListFromStationWhenStationExistsThenReturnFireCoverageByStation() {

        List<String> fireStationAddress = List.of("1509 Culver St");
        when(fireStationRepository.findFireStationAddressesByNumber(1)).thenReturn(fireStationAddress);

        when(personRepository.loadPersonsList()).thenReturn(
                List.of(new Person("John", "Boyd", "1509 Culver St",
                                "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
                        new Person("Jacob", "Boyd", "1509 Culver St",
                                "Culver", "97451", "841-874-6513", "drk@email.com"),
                        new Person("Tenley", "Boyd", "1509 Culver St",
                                "Culver", "97451", "841-874-6512", "tenz@email.com")));

        when(ageCalculator.calculateAgeFromName("John", "Boyd")).thenReturn((short) 10);
        when(ageCalculator.calculateAgeFromName("Jacob", "Boyd")).thenReturn((short) 19);
        when(ageCalculator.calculateAgeFromName("Tenley", "Boyd")).thenReturn((short) 10);

        List<PersonContact> personContactList =
                List.of(new PersonContact("John", "Boyd", "1509 Culver St", "841-874-6512"),
                        new PersonContact("Jacob", "Boyd", "1509 Culver St", "841-874-6513"),
                        new PersonContact("Tenley", "Boyd", "1509 Culver St", "841-874-6512"));

        FireCoverageByStation fireCoverageByStation = personService.getCoverageListFromStation(1);

        assertEquals(new FireCoverageByStation(personContactList, 1, 2), fireCoverageByStation);
    }



    @Test
    @DisplayName("Should return an empty list when there is no child at the address")
    void getChildrenListByAddressWhenThereIsNoChildAtTheAddressThenReturnEmptyList() {

        when(personRepository.loadPersonsList()).thenReturn(
                List.of(new Person("John", "Boyd", "1509 Culver St",
                                "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
                        new Person("Jacob", "Boyd", "1509 Culver St",
                                "Culver", "97451", "841-874-6513", "drk@email.com")));

        when(ageCalculator.calculateAgeFromName("John", "Boyd")).thenReturn((short) 19);
        when(ageCalculator.calculateAgeFromName("Jacob", "Boyd")).thenReturn((short) 37);

        assertTrue(personService.getChildrenListByAddress("1509 Culver St").getChildren().isEmpty());
        assertTrue(personService.getChildrenListByAddress("1509 Culver St").getOthers().isEmpty());
    }



    @Test
    @DisplayName("Should return a list of children and adults when the address exists")
    void getChildrenListByAddressWhenTheAddressExistsThenReturnAListOfChildrenAndAdults() {

        when(personRepository.loadPersonsList()).thenReturn(
                List.of(new Person("John", "Boyd", "1509 Culver St",
                                    "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
                        new Person("Jacob", "Boyd", "1509 Culver St",
                                    "Culver", "97451", "841-874-6513", "drk@email.com"),
                        new Person("Tenley", "Boyd", "1509 Culver St",
                                    "Culver", "97451", "841-874-6512", "tenz@email.com")));

        when(ageCalculator.calculateAgeFromName("John", "Boyd")).thenReturn((short) 10);
        when(ageCalculator.calculateAgeFromName("Jacob", "Boyd")).thenReturn((short) 19);
        when(ageCalculator.calculateAgeFromName("Tenley", "Boyd")).thenReturn((short) 10);

        List<PersonAge> children = List.of(new PersonAge("John", "Boyd", (short) 10),
                                            new PersonAge("Tenley", "Boyd", (short) 10));

        List<PersonAge> others = List.of(new PersonAge("Jacob", "Boyd", (short) 19));

        ChildByFamily childByFamily = new ChildByFamily(children, others);

        assertEquals(childByFamily, personService.getChildrenListByAddress("1509 Culver St"));
    }



    @Test
    @DisplayName("Should return an empty list when the station number does not exist")
    void getPhoneListFromStationWhenStationNumberDoesNotExistThenReturnEmptyList() {

        Integer station = 1;
        when(fireStationRepository.findFireStationAddressesByNumber(station)).thenReturn(List.of());

        List<String> phoneList = personService.getPhoneListFromStation(station);

        assertTrue(phoneList.isEmpty());
    }



    @Test
    @DisplayName("Should return a list of phone numbers when the station number exists")
    void getPhoneListFromStationWhenStationNumberExistsThenReturnListOfPhoneNumbers() {

        Integer station = 1;
        List<String> phoneList = List.of("0612345678", "0612345679");

        when(fireStationRepository.findFireStationAddressesByNumber(station))
                .thenReturn(List.of("1 rue de la paix", "2 rue de la paix"));
        when(personRepository.loadPersonsList()).thenReturn(
                List.of(new Person("John", "Doe", "1 rue de la paix",
                                "Paris", "75000", "0612345678", "john.doe@gmail.com"),
                        new Person("Jane", "Doe", "2 rue de la paix",
                                "Paris", "75000", "0612345679", "jane.doe@gmail.com")));

        assertEquals(phoneList, personService.getPhoneListFromStation(station));
    }



    @Test
    @DisplayName("Should return an empty list when the address does not exist")
    void getInhabitantsByAddressWhenAddressDoesNotExistThenReturnEmptyList() {

        FireCoverageByAddress fireCoverageByAddress = personService.getInhabitantsByAddress("any");

        assertEquals(0, fireCoverageByAddress.getPersonsHealthList().size());
    }



    @Test
    @DisplayName("Should return a list of inhabitants when the address exists")
    void getInhabitantsByAddressWhenAddressExistsThenReturnListOfInhabitants() {

        when(personRepository.loadPersonsList()).thenReturn(
                List.of(new Person("John", "Doe", "1 rue de la paix",
                                "Paris", "75000", "0612345678", "john.doe@gmail.com"),
                        new Person("Jane", "Doe", "1 rue de la paix",
                                "Paris", "75000", "0612345678", "john.doe@gmail.com"),
                        new Person("Jane", "Boyd", "1 rue de la paix",
                                "Paris", "75000", "0612345679", "jane.doe@gmail.com")));

        when(ageCalculator.calculateAgeFromName(anyString(), anyString())).thenReturn((short) 30);
        when(medicalRecordRepository.findMedicationsByName(anyString(), anyString())).thenReturn(List.of());
        when(medicalRecordRepository.findAllergiesByName(anyString(), anyString())).thenReturn(List.of());
        when(fireStationRepository.findFireStationNumberByAddress(anyString())).thenReturn(9);

        FireCoverageByAddress fireCoverageByAddress = personService.getInhabitantsByAddress("1 rue de la paix");

        assertEquals(3, fireCoverageByAddress.getPersonsHealthList().size());
    }



    @Test
    @DisplayName("Should return an empty list when the station does not exist")
    void getInhabitantsByStationWhenStationDoesNotExistThenReturnEmptyList() {


        List<String> addresses = List.of();
        when(fireStationRepository.findFireStationAddressesByNumber(any())).thenReturn(addresses);

        List<FloodCoverage> floodCoverageList = personService.getInhabitantsByStation(List.of(10));

        assertEquals(0, floodCoverageList.size());
    }



    @Test
    @DisplayName("Should return a list of floodcoverage when the station exists")
    void getInhabitantsByStationWhenStationExistsThenReturnListOfFloodCoverage() {

        List<String> addresses = List.of("123 Test St", "124 Test St", "125 Test St");
        when(fireStationRepository.findFireStationAddressesByNumber(any())).thenReturn(addresses);

        when(personRepository.loadPersonsList()).thenReturn(
                List.of(new Person("John", "Boyd", "123 Test St",
                                "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
                        new Person("Jacob", "Boyd", "124 Test St",
                                "Culver", "97451", "841-874-6513", "drk@email.com"),
                        new Person("Tenley", "Boyd", "125 Test St",
                                "Culver", "97451", "841-874-6512", "tenz@email.com")));

        when(ageCalculator.calculateAgeFromName(any(), any())).thenReturn((short) 30);
        when(medicalRecordRepository.findMedicationsByName(any(), any())).thenReturn(List.of());
        when(medicalRecordRepository.findAllergiesByName(any(), any())).thenReturn(List.of());

        List<FloodCoverage> floodCoverageList = personService.getInhabitantsByStation(List.of(10));

        assertEquals(3, floodCoverageList.size());
    }



    @Test
    @DisplayName("Should return a list with one person when the person is found")
    void findPersonInfoByNameWhenPersonIsFoundThenReturnListWithOnePerson() {

        when(personRepository.loadPersonsList()).thenReturn(
                List.of(new Person("John", "Doe", "1 rue de la paix",
                                "Paris", "75000", "0612345678", "john.doe@gmail.com"),
                        new Person("Jane", "Doe", "2 rue de la paix",
                                "Paris", "75000", "0612345678", "john.doe@gmail.com"),
                        new Person("Jane", "Boyd", "3 rue de la paix",
                                "Paris", "75000", "0612345679", "jane.doe@gmail.com")));

        when(ageCalculator.calculateAgeFromName(any(), any())).thenReturn((short) 30);
        when(medicalRecordRepository.findMedicationsByName(any(), any())).thenReturn(List.of());
        when(medicalRecordRepository.findAllergiesByName(any(), any())).thenReturn(List.of());

        List<PersonInfo> personInfoList = personService.findPersonInfoByName("John", "Doe");
        List<PersonInfo> personInfoList1 = personService.findPersonInfoByName("Jane", "Boyd");

        assertEquals(2, personInfoList.size());
        assertEquals(1, personInfoList1.size());
    }



    @Test
    @DisplayName("Should return an empty list when the person is not found")
    void findPersonInfoByNameWhenPersonIsNotFoundThenReturnEmptyList() {

        when(personRepository.loadPersonsList()).thenReturn(
                List.of(new Person("John", "Doe", "1 rue de la paix",
                                "Paris", "75000", "0612345678", "john.doe@gmail.com"),
                        new Person("Jane", "Boyd", "3 rue de la paix",
                                "Paris", "75000", "0612345679", "jane.doe@gmail.com")));

        when(ageCalculator.calculateAgeFromName(any(), any())).thenReturn((short) 30);
        when(medicalRecordRepository.findMedicationsByName(any(), any())).thenReturn(List.of());
        when(medicalRecordRepository.findAllergiesByName(any(), any())).thenReturn(List.of());

        List<PersonInfo> actual = personService.findPersonInfoByName("John", "McLane");

        assertTrue(actual.isEmpty());
    }



    @Test
    @DisplayName("Return an empty list when the city is not found")
    void findAllEmailsByCityWhenNotFoundThenReturnAnEmptyList() {

        List<Person> personList = new ArrayList<>();
        when(personRepository.loadPersonsList()).thenReturn(personList);

        List<String> emailsList = personService.findAllEmailsByCity("Culver");

        assertTrue(emailsList.isEmpty());
    }



    @Test
    @DisplayName("Should return an emails list when the city is found")
    void findAllEmailsByCityWhenFoundThenReturnEmailsList() {

        when(personRepository.loadPersonsList()).thenReturn(
                List.of(new Person("Jacques", "Chirac", "1509 Culver St",
                            "Culver", "97451", "841-874-6512", "jchirac@email.com"),
                        new Person("Bernadette", "Chirac", "1509 Culver St",
                            "Culver", "97451", "841-874-6512", "bchirac@email.com")));

        List<String> emailsList = personService.findAllEmailsByCity("Culver");

        assertEquals(List.of("jchirac@email.com", "bchirac@email.com"), emailsList);
    }
}