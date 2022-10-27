package com.safetynet.alerts.util;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.service.PersonServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class AgeCalculator {


    private static final Logger log = LogManager.getLogger(PersonServiceImpl.class);


    /**
     * Calculate the age from a birthdate
     * @param birthdate
     * @return age
     */
    public short calculateAgeFromBirthdate(final String birthdate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate personBirthdate = LocalDate.parse(birthdate, formatter);
        LocalDate currentDate = LocalDate.now();
        short age = (short) Period.between(personBirthdate, currentDate).getYears();

        log.debug("Trying to return the age from the birthdate " + birthdate);
        return age;
    }


    /**
     * Calculate the age from a name
     * @param firstName
     * @param lastName
     * @return age
     */
    public short calculateAgeFromName(String firstName, String lastName) {

        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.loadMedicalRecordsList();
        MedicalRecord medicalRecord = medicalRecordList.stream()
                .filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
                .findAny().orElseThrow(()-> new IllegalArgumentException("not found"));
        log.debug("Trying to return the age of " + firstName + " " + lastName);
        return calculateAgeFromBirthdate(medicalRecord.getBirthdate());

    }
}
