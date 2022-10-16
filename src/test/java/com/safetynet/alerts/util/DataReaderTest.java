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

//    @Test
//    @DisplayName("Should throw an exception when the file is not loaded")
//    void jsonReaderWhenFileIsNotLoadedThenThrowException() {
//        DataReader dataReader = mock(DataReader.class);
//        when(dataReader.jsonReader()).thenThrow(IOException.class);
//        assertThrows(IOException.class, () -> dataReader.jsonReader());
//    }
}