package com.safetynet.alerts.util;

import com.jsoniter.any.Any;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayName("DataReader test")
class DataReaderTest {

    @Test
    @DisplayName("Should return an any object when the file is loaded")
    void jsonReaderWhenFileIsLoadedThenReturnAnAnyObject() {
        DataReader dataReader = new DataReader();
        Any any = dataReader.jsonReader();
        assertNotNull(any);
    }
}