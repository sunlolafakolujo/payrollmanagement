package com.logicgate.payrollmanagement.address.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.country.model.Country;
import com.logicgate.payrollmanagement.country.service.CountryService;
import com.logicgate.payrollmanagement.state.model.Province;
import com.logicgate.payrollmanagement.state.service.StateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceImplTest {
    @Autowired
    private AddressService addressService;
    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    Address address;
    Province province;
    Country country;

    @BeforeEach
    void setUp() {
        address = new Address();
        province = new Province();
        country = new Country();
    }

    @Test
    void testThatWhenYouCallAddAddressMethod_thenAddressIsSaved() throws Exception {
        province = stateService.fetchStateByName("Ekiti");
        country = countryService.fetchCountryByName("Nigeria");
        address.setHouseNumber("24");
        address.setStreetName("Aladelola Street Ikosi");
        address.setCity("Kosofe");
        address.setLandmark("Motoways");
        address.setProvince(province);
        address.setCountry(country);
        this.mockMvc.perform(post("/api/payroll/addAddress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(address))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.streetName").value("Aladelola Street Ikosi"))
                .andReturn();
    }

    @Test
    void testThatWhenYouCallFetAddressByIdMethod_thenAddressIsReturned() throws Exception {

        this.mockMvc.perform(get("/api/payroll/findAddressId?id=102")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.houseNumber").value("24"))
                .andReturn();
    }

    @Test
    void fetchAllAddresses() {
    }

    @Test
    void editAddress() {
    }

    @Test
    void deleteAddress() {
    }

    @Test
    void deleteAllAddresses() {
    }
}