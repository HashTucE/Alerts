package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalRecordController.class)
class MedicalRecordControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private MedicalRecordService medicalRecordService;


    MedicalRecord medicalRecord = new MedicalRecord("any", "any", "any", List.of("any"),List.of("any"));


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    @DisplayName("addMedicalRecordTest should return 201 object created")
    public void addMedicalRecordTest() throws Exception {

        when(medicalRecordService.addMedicalRecord(medicalRecord)).thenReturn(medicalRecord);

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(medicalRecord)))
                .andExpect(status().isCreated())
                .andReturn();
    }


    @Test
    @DisplayName("updateMedicalRecordTest should return 204 no content")
    public void updateMedicalRecordTest() throws Exception {

        mockMvc.perform(put("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(medicalRecord)))
                .andExpect(status().isNoContent());
    }



    @Test
    @DisplayName("deleteMedicalRecordTest should return 204 no content")
    public void deleteMedicalRecordTest() throws Exception {

        mockMvc.perform(delete("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(medicalRecord)))
                .andExpect(status().isNoContent());
    }


}