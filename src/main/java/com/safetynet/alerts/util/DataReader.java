package com.safetynet.alerts.util;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.constants.Path;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
@Data
public class DataReader {

    private static final Logger log = LogManager.getLogger(DataReader.class);


    /**
     * Read a json file
     * @return any
     */
    public static Any jsonReader() {


        Any any = null;
        try {
            byte[] bytesFile = Files.readAllBytes(new File(Path.DATA).toPath());
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

