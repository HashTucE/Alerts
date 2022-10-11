package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.*;

import java.util.List;

public interface SpecificService {


    /**
     * @param station station number
     * @return a list of people covered by the corresponding fire station.
     * List content: names, address, telephone number.
     * provides a count of the number of adults and the number of children (<= 18) in the service area.
     */
    FireCoverageByStation getCoverageListFromStation(Integer station);



    /**
     * @param address address of the inhabitants
     * @return a list of children (<= 18) living at this address.
     * The list include each child's first and last name, age, and a list of other household members.
     * If there are no children, return an empty string.
     */
    ChildByFamily getChildrenListByAddress(String address);



    /**
     * @param station station number
     * @return a list of phone numbers of residents served by the fire station.
     * Used to send emergency text messages to specific households.
     */
    List<String> getPhoneListFromStation(Integer station);



    /**
     * @param address address of the inhabitants
     * @return the list of inhabitants living at the given address and the number of the fire station serving it.
     * List content: names, phone number, age, and medical history (medications, dosages, and allergies).
     */
    FireCoverageByAddress getInhabitantsByAddress(String address);



    /**
     * @param stations list of station numbers
     * @return a list of all households (group people by address) served by the fire station.
     * List content: names, phone number, age, and medical history alongside.
     */
    List<FloodCoverage> getInhabitantsByStation(List<Integer> stations);



    /**
     * @param firstName first name
     * @param lastName last name
     * @return a list containing name, address, age, email address and medical history
     * (medication, dosage, allergies) of each inhabitant.
     * If multiple people have the same name, they all appear.
     */
    List<PersonInfo> findPersonInfoByName(String firstName, String lastName);



    /**
     * @param city city
     * @return the email list of all the inhabitants of the city
     */
    List<String> findAllEmailsByCity(String city);
}
