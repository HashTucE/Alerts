package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.dto.*;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;


    Person person = new Person("any", "any", "any", "any", "any", "any", "any");


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    @DisplayName("addPersonTest should return 201 object created")
    public void addPersonTest() throws Exception {

        when(personService.addPerson(person)).thenReturn(person);

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(person)))
                .andExpect(status().isCreated())
                .andReturn();
    }


    @Test
    @DisplayName("updatePersonTest should return 204 no content")
    public void updatePersonTest() throws Exception {

        mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(person)))
                .andExpect(status().isNoContent());
    }



    @Test
    @DisplayName("deletePersonTest should return 204 no content")
    public void deletePersonTest() throws Exception {

        mockMvc.perform(delete("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(person)))
                .andExpect(status().isNoContent());
    }





    @Test
    @DisplayName("getCoverageListFromStationTest should return 200 object found")
    public void getCoverageListFromStationTest() throws Exception {

        when(personService.getCoverageListFromStation(3)).thenReturn(
                new FireCoverageByStation(List.of(new PersonContact()), 1, 2));

        mockMvc.perform(get("/firestation?stationNumber=3"))
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName("getChildrenListByAddressTest should return 200 object found")
    public void getChildrenListByAddressTest() throws Exception {

        when(personService.getChildrenListByAddress("any")).thenReturn(
                new ChildByFamily(List.of(new PersonAge()), List.of(new PersonAge())));

        mockMvc.perform(get("/childAlert?address=any"))
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName("getPhoneListFromStationTest should return 200 object found")
    public void getPhoneListFromStationTest() throws Exception {

        when(personService.getPhoneListFromStation(3)).thenReturn(List.of("any"));

        mockMvc.perform(get("/phoneAlert?firestation=3"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getPhoneListFromStationTest should return 404 object not found")
    public void getPhoneListFromStationNegativeTest() throws Exception {

        mockMvc.perform(get("/phoneAlert?firestation=3"))
                .andExpect(status().isNotFound());
    }



    @Test
    @DisplayName("getInhabitantsByAddressTest should return 200 object found")
    public void getInhabitantsByAddressTest() throws Exception {

        when(personService.getInhabitantsByAddress("any")).thenReturn(
                new FireCoverageByAddress(anyInt(), List.of(new PersonHealth())));

        mockMvc.perform(get("/fire?address=any"))
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName("getInhabitantsByStationTest should return 200 object found")
    public void getInhabitantsByStationTest() throws Exception {

        when(personService.getInhabitantsByStation(List.of(1, 2))).thenReturn(List.of(
                new FloodCoverage("any", List.of(new Resident()))));

        mockMvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getInhabitantsByStationTest should return 404 object not found")
    public void getInhabitantsByStationNegativeTest() throws Exception {

        mockMvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isNotFound());
    }



    @Test
    @DisplayName("findPersonInfoByNameTest should return 200 object found")
    public void findPersonInfoByNameTest() throws Exception {

        when(personService.findPersonInfoByName("any", "any")).thenReturn(List.of(
                new PersonInfo()));

        mockMvc.perform(get("/personInfo?firstName=any&lastName=any"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("findPersonInfoByNameTest should return 404 object not found")
    public void findPersonInfoByNameNegativeTest() throws Exception {

        mockMvc.perform(get("/personInfo?firstName=any&lastName=any"))
                .andExpect(status().isNotFound());
    }



    @Test
    @DisplayName("findAllEmailsByCityTest should return 200 object found")
    public void findAllEmailsByCityTest() throws Exception {

        when(personService.findAllEmailsByCity("any")).thenReturn(List.of("anyEmails"));

        mockMvc.perform(get("/communityEmail?city=any"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("findAllEmailsByCityTest should return 404 object not found")
    public void findAllEmailsByCityNegativeTest() throws Exception {

        mockMvc.perform(get("/communityEmail?city=any"))
                .andExpect(status().isNotFound());
    }
}