package com.safetynet.alerts.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AgeCalculatorTest {


    @Test
    @DisplayName("Should throw an exception when the birthdate is invalid")
    void calculateAgeFromBirthdateWhenBirthdateIsInvalidThenThrowException() {
        AgeCalculator ageCalculator = new AgeCalculator();
        assertThrows(
                DateTimeParseException.class,
                () -> ageCalculator.calculateAgeFromBirthdate("12/12/12"));
    }

    @Test
    @DisplayName("Should return the age when the birthdate is valid")
    void calculateAgeFromBirthdateWhenBirthdateIsValid() {
        LocalDate birthdate = LocalDate.now().minusYears(23);
        String birthdateStr = birthdate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        AgeCalculator ageCalculator = new AgeCalculator();
        short age = ageCalculator.calculateAgeFromBirthdate(birthdateStr);
        assertEquals(23, age);
    }

    @Test
    @DisplayName("Should throw an exception when the person does not exist")
    void calculateAgeFromNameWhenPersonDoesNotExistThenThrowException() {
        AgeCalculator ageCalculator = new AgeCalculator();
        assertThrows(
                IllegalArgumentException.class,
                () -> ageCalculator.calculateAgeFromName("John", "Doe"));
    }
}
