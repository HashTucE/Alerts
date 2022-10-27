package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.*;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
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
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    FireStationRepository fireStationRepository;

    @Autowired
    AgeCalculator ageCalculator;

    private static final Logger log = LogManager.getLogger(PersonServiceImpl.class);


    /**
     * Create a person
     * @param person
     * @return Person
     */
    @Override
    public Person addPerson(Person person) {
        log.debug("addPerson() from repository called");
        personRepository.addPerson(person);
        return person;
    }


    /**
     * Update a person
     * @param person
     */
    @Override
    public void updatePerson(Person person) {
        log.debug("updatePerson() from repository called");
        personRepository.updatePerson(person);
    }


    /**
     * Delete a person
     * @param person
     */
    @Override
    public void deletePerson(Person person) {
        log.debug("deletePerson() from repository called");
        personRepository.deletePerson(person);
    }





    /**
     * @param station station number
     * @return a list of people covered by the corresponding fire station.
     * List content: names, address, telephone number.
     * provides a count of the number of adults and the number of children (<= 18) in the service area.
     */
    @Override
    public FireCoverageByStation getCoverageListFromStation(Integer station) {


        List<String> fireStationAddress = fireStationRepository.findFireStationAddressesByNumber(station);
        List<PersonContact> personContactList = new ArrayList<>();
        int adultsNumber = 0;
        int childrenNumber = 0;

        for (Person person : personRepository.loadPersonsList()) {
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
        log.debug("Trying to return the FireCoverageByStation for the station number " + station);
        return new FireCoverageByStation(personContactList, adultsNumber, childrenNumber);
    }



    /**
     * @param address address of the inhabitants
     * @return a list of children (<= 18) living at this address.
     * The list include each child's first and last name, age, and a list of other household members.
     * If there are no children, return an empty string.
     */
    @Override
    public ChildByFamily getChildrenListByAddress(String address) {

        List<PersonAge> children = new ArrayList<>();
        List<PersonAge> others = new ArrayList<>();
        List<PersonInfo> personInfoList = new ArrayList<>();

        for (Person person : personRepository.loadPersonsList()) {
            personInfoList.add(new PersonInfo(
                    person.getFirstName(),
                    person.getLastName(),
                    person.getAddress(),
                    ageCalculator.calculateAgeFromName(person.getFirstName(), person.getLastName()),
                    person.getEmail(),
                    medicalRecordRepository.findMedicationsByName(person.getFirstName(), person.getLastName()),
                    medicalRecordRepository.findAllergiesByName(person.getFirstName(), person.getLastName())));
        }

        for (PersonInfo personInfo : personInfoList) {
            if (personInfo.getAddress().equals(address)) {
                PersonAge personAge = new PersonAge(personInfo.getFirstName(), personInfo.getLastName(), personInfo.getAge());
                if (personAge.getAge() <= 18) { children.add(personAge);
                } else { others.add(personAge);
                }
            }
        }
        if (children.size() == 0) {
            log.error("Return an empty list, there is no child at the " + address + " or the address does not exist");
            return new ChildByFamily(List.of(), List.of());
        } else {
            log.debug("Trying to return the children list for the address " + address);
            return new ChildByFamily(children, others);
        }
    }



    /**
     * @param station station number
     * @return a list of phone numbers of residents served by the fire station.
     * Used to send emergency text messages to specific households.
     */
    @Override
    public List<String> getPhoneListFromStation(Integer station) {

        List<String> fireStationAddress = fireStationRepository.findFireStationAddressesByNumber(station);
        List<String> phoneList = new ArrayList<>();

        log.debug("Trying to call getPhoneListFromStation()");
        for (Person person : personRepository.loadPersonsList()) {
            if (fireStationAddress.contains(person.getAddress())) {
                phoneList.add(person.getPhone());
            }
        }
        log.debug("Trying to return the phone list for the station number " + station);
        return phoneList
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }



    /**
     * @param address address of the inhabitants
     * @return the list of inhabitants living at the given address and the number of the fire station serving it.
     * List content: names, phone number, age, and medical history (medications, dosages, and allergies).
     */
    @Override
    public FireCoverageByAddress getInhabitantsByAddress(String address) {

        List<PersonHealth> personHealthList = new ArrayList<>();

        for (Person person : personRepository.loadPersonsList()) {
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
        Integer station = fireStationRepository.findFireStationNumberByAddress(address);
        log.debug("Trying to return the FireCoverageByAddress for " + address);
        return new FireCoverageByAddress(station, personHealthList);
    }



    /**
     * @param stations list of station numbers
     * @return a list of all households (group people by address) served by the fire station.
     * List content: names, phone number, age, and medical history alongside.
     */
    @Override
    public List<FloodCoverage> getInhabitantsByStation(List<Integer> stations) {

        List<FloodCoverage> floodCoverageList = new ArrayList<>();

        for (Integer station : stations) {
            List<String> addresses = fireStationRepository.findFireStationAddressesByNumber(station);

            for (String address : addresses) {
                List<Resident> residentList = new ArrayList<>();

                for (Person person : personRepository.loadPersonsList()) {
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
        log.debug("Trying to return the FloodCoverage for station's numbers " + stations);
        return floodCoverageList;
    }



    /**
     * @param firstName first name
     * @param lastName last name
     * @return a list containing name, address, age, email address and medical history
     * (medication, dosage, allergies) of each inhabitant.
     * If multiple people have the same name, they all appear.
     */
    @Override
    public List<PersonInfo> findPersonInfoByName(String firstName, String lastName) {

        List<PersonInfo> personFound = new ArrayList<>();
        List<PersonInfo> personInfoList = new ArrayList<>();

        for (Person person : personRepository.loadPersonsList()) {
            personInfoList.add(new PersonInfo(
                    person.getFirstName(),
                    person.getLastName(),
                    person.getAddress(),
                    ageCalculator.calculateAgeFromName(person.getFirstName(), person.getLastName()),
                    person.getEmail(),
                    medicalRecordRepository.findMedicationsByName(person.getFirstName(), person.getLastName()),
                    medicalRecordRepository.findAllergiesByName(person.getFirstName(), person.getLastName())));
        }

        for (PersonInfo personInfo : personInfoList) {
            if (lastName.equals(personInfo.getLastName())
                    || firstName.equals(personInfo.getFirstName())
                    && lastName.equals(personInfo.getLastName())) {
                personFound.add(personInfo);
            }
        }
        log.debug("Trying to return the PersonInfo for " + firstName + " " + lastName);
        return personFound;
    }



    /**
     * @param city city
     * @return the email list of all the inhabitants of the city
     */
    @Override
    public List<String> findAllEmailsByCity(String city) {

        List<String> emailListByCity = new ArrayList<>();
        for (Person person : personRepository.loadPersonsList()) {
            if (city.equals(person.getCity())) emailListByCity.add(person.getEmail());
        }
        log.debug("Trying to return the emails list for " + city );
        return emailListByCity
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}

