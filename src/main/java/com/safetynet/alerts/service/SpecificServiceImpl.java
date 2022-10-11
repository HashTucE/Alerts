package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.*;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.util.AgeCalculator;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class SpecificServiceImpl implements SpecificService{



    @Autowired
    PersonRepository personRepository = new PersonRepository();

    @Autowired
    MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();

    @Autowired
    FireStationService fireStationService = new FireStationServiceImpl();

    @Autowired
    AgeCalculator ageCalculator = new AgeCalculator();



    private static final Logger log = LogManager.getLogger(PersonRepository.class);

    private List<Person> personsList = personRepository.loadPersonsList();



    /**
     * @return a list of persons info containing fistName, lastName, address, age, email and medical records
     */
    public List<PersonInfo> loadPersonInfoList() {

        List<PersonInfo> personInfoList = new ArrayList<>();
        log.debug("trying to call findPersonInfoByName()");

        for (Person person : personsList) {
            personInfoList.add(new PersonInfo(
                    person.getFirstName(),
                    person.getLastName(),
                    person.getAddress(),
                    ageCalculator.calculateAgeFromName(person.getFirstName(), person.getLastName()),
                    person.getEmail(),
                    medicalRecordRepository.findMedicationsByName(person.getFirstName(), person.getLastName()),
                    medicalRecordRepository.findAllergiesByName(person.getFirstName(), person.getLastName())));
        }
        log.debug("personInfoList loaded !");
        return personInfoList;
    }



    @Override
    public FireCoverageByStation getCoverageListFromStation(Integer station) {

        List<String> fireStationAddress = fireStationService.findFireStationAddressesByNumber(station);
        List<PersonContact> personContactList = new ArrayList<>();
        int adultsNumber = 0;
        int childrenNumber = 0;

        for (Person person : personsList) {
            if (fireStationAddress.contains(person.getAddress())) {
                PersonContact personContact = new PersonContact();
                personContact.setFirstName(person.getFirstName());
                personContact.setLastName(person.getLastName());
                personContact.setAddress(person.getAddress());
                personContact.setPhoneNumber(person.getPhone());
                personContactList.add(personContact);

                if (ageCalculator.calculateAgeFromName(person.getFirstName(), person.getLastName()) > 18)
                    adultsNumber++;
                if (ageCalculator.calculateAgeFromName(person.getFirstName(), person.getLastName()) <= 18)
                    childrenNumber++;
            }
        }
        return new FireCoverageByStation(personContactList, adultsNumber, childrenNumber);
    }



    @Override
    public ChildByFamily getChildrenListByAddress(String address) {

        List<PersonAge> children = new ArrayList<>();
        List<PersonAge> others = new ArrayList<>();
        List<PersonInfo> personInfoList = loadPersonInfoList();

        for (PersonInfo personInfo : personInfoList) {
            if (personInfo.getAddress().equals(address)) {
                PersonAge personAge = new PersonAge(personInfo.getFirstName(), personInfo.getLastName(), personInfo.getAge());
                if (personAge.getAge() <= 18) { children.add(personAge);
                } else { others.add(personAge);
                }
            }
        }
        if (children.size() == 0) {
            return new ChildByFamily(List.of(), List.of());
        } else { return new ChildByFamily(children, others);
        }
    }



    @Override
    public List<String> getPhoneListFromStation(Integer station) {

        List<String> fireStationAddress = fireStationService.findFireStationAddressesByNumber(station);
        List<String> phoneList = new ArrayList<>();

        log.debug("Trying to call getPhoneListFromStation()");
        for (Person person : personRepository.loadPersonsList()) {
            if (fireStationAddress.contains(person.getAddress())) {
                phoneList.add(person.getPhone());
            }
        }
        log.debug("Phone list generated from station" + station);
        return phoneList.stream().distinct().collect(Collectors.toList());
    }



    @Override
    public FireCoverageByAddress getInhabitantsByAddress(String address) {

        List<PersonHealth> personHealthList = new ArrayList<>();

        for (Person person : personsList) {
            if (person.getAddress().equals(address)) {
                PersonHealth personHealth = new PersonHealth();
                personHealth.setFirstName(person.getFirstName());
                personHealth.setLastName(person.getLastName());
                personHealth.setPhone(person.getPhone());
                personHealth.setAge(ageCalculator.calculateAgeFromName(person.getFirstName(), person.getLastName()));
                personHealth.setMedications(medicalRecordRepository.findMedicationsByName(person.getFirstName(), person.getLastName()));
                personHealth.setAllergies(medicalRecordRepository.findAllergiesByName(person.getFirstName(), person.getLastName()));
                personHealthList.add(personHealth);
            }
        }
        Integer station = fireStationService.findStationNumberByAddress(address);
        return new FireCoverageByAddress(station, personHealthList);
    }



    @Override
    public List<FloodCoverage> getInhabitantsByStation(List<Integer> stations) {

        List<FloodCoverage> floodCoverageList = new ArrayList<>();

        for (Integer station : stations) {
            List<String> addresses = fireStationService.findAddressesByNumber(station);

            for (String address : addresses) {
                List<Resident> residentList = new ArrayList<>();

                for (Person person : personsList) {
                    Resident resident = new Resident();
                    resident.setFirstName(person.getFirstName());
                    resident.setLastName(person.getLastName());
                    resident.setAddress(person.getAddress());
                    resident.setPhone(person.getPhone());
                    resident.setAge(ageCalculator.calculateAgeFromName(person.getFirstName(), person.getLastName()));
                    resident.setMedications(medicalRecordRepository.findMedicationsByName(person.getFirstName(), person.getLastName()));
                    resident.setAllergies(medicalRecordRepository.findAllergiesByName(person.getFirstName(), person.getLastName()));
                    residentList.add(resident);
                }

                List<Resident> residentListByAddresses = new ArrayList<>();

                for (Resident resident : residentList) {
                    if (resident.getAddress().equals(address))
                        residentListByAddresses.add(resident);
                }
                FloodCoverage floodCoverage = new FloodCoverage(address, residentListByAddresses);
                floodCoverageList.add(floodCoverage);
            }
        }
        return floodCoverageList;
    }



    @Override
    public List<PersonInfo> findPersonInfoByName(String firstName, String lastName) {

        List<PersonInfo> personFound = new ArrayList<>();
        List<PersonInfo> personInfoList = loadPersonInfoList();
        for (PersonInfo personInfo : personInfoList) {
            if (lastName.equals(personInfo.getLastName())
                    || firstName.equals(personInfo.getFirstName())
                    && lastName.equals(personInfo.getLastName())) {
                personFound.add(personInfo);
            }
        }
        log.debug("Person info founded for" + firstName + lastName);
        return personFound;
    }



    @Override
    public List<String> findAllEmailsByCity(String city) {

        List<String> emailListByCity = new ArrayList<>();
        for (Person person : personsList) {
            if (city.equals(person.getCity())) emailListByCity.add(person.getEmail());
        }
        log.debug("Emails found by city");
        return emailListByCity.stream().distinct().collect(Collectors.toList());
    }
}
