package com.safetynet.alerts.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        String birthdate = "01/01/2000";
        AgeCalculator ageCalculator = new AgeCalculator();
        short age = ageCalculator.calculateAgeFromBirthdate(birthdate);
        assertEquals(22, age);
    }

    @Test
    @DisplayName("Should throw an exception when the person does not exist")
    void calculateAgeFromNameWhenPersonDoesNotExistThenThrowException() {
        AgeCalculator ageCalculator = new AgeCalculator();
        assertThrows(
                IllegalArgumentException.class,
                () -> ageCalculator.calculateAgeFromName("John", "Doe"));
    }

    @Test
    @DisplayName("Should return the age when the person exists")
    void calculateAgeFromNameWhenPersonExists() {
        AgeCalculator ageCalculator = new AgeCalculator();
        short age = ageCalculator.calculateAgeFromName("John", "Boyd");
        assertEquals(38, age);
    }
}