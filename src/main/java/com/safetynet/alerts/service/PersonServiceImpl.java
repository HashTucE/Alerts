package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.util.AgeCalculator;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository = new PersonRepository();

    @Autowired
    MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();

    @Autowired
    AgeCalculator ageCalculator = new AgeCalculator();





    private static final Logger log = LogManager.getLogger(PersonServiceImpl.class);



    @Override
    public Person addPerson(Person person) {
        log.debug("addPerson() from repository called !");
        return personRepository.addPerson(person);
    }

    @Override
    public Person updatePerson(Person person) {
        log.debug("updatePerson() from repository called !");
        return personRepository.updatePerson(person);
    }

    @Override
    public void deletePerson(Person person) {
        log.debug("deletePerson() from repository called !");
        personRepository.deletePerson(person);
    }



    @Override
    public Person findPerson(String firstName, String lastName){
        log.debug("findPerson() from repository called !");
        return personRepository.findPerson(firstName,lastName);
    }



    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAllPersons();
    }




}

