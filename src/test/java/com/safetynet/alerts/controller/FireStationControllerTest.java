package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;


    FireStation fireStation = new FireStation("any", 1);


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    @DisplayName("addFireStationTest should return 201 object created")
    public void addFireStationTest() throws Exception {

        when(fireStationService.addFireStation(fireStation)).thenReturn(fireStation);

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(fireStation)))
                .andExpect(status().isCreated())
                .andReturn();
    }


    @Test
    @DisplayName("updateFireStationTest should return 204 no content")
    public void updateFireStationTest() throws Exception {

        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(fireStation)))
                .andExpect(status().isNoContent());
    }



    @Test
    @DisplayName("deleteFireStationTest should return 204 no content")
    public void deleteFireStationTest() throws Exception {

        mockMvc.perform(delete("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(fireStation)))
                .andExpect(status().isNoContent());
    }
}

