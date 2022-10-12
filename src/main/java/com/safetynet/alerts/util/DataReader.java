package com.safetynet.alerts.util;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.model.Person;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Data
public class DataReader {

    private static final Logger log = LogManager.getLogger(DataReader.class);

    private static Any any;

    private Person person;


    public static Any jsonReader() {


        try {
            byte[] bytesFile = Files.readAllBytes(new File("src/main/resources/data.json").toPath());
            JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
            any = jsonIterator.readAny();
            jsonIterator.close();
            log.debug("Json file loaded");
        } catch (IOException e) {
            log.error("Json file not loaded");
            e.printStackTrace();
        }
        return any;
    }
}

