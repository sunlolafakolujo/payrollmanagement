package com.logicgate.payrollmanagement.country.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgate.payrollmanagement.AbstractContainerBaseTest;
import com.logicgate.payrollmanagement.country.model.Country;
import com.logicgate.payrollmanagement.country.service.CountryService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryControllerTest extends AbstractContainerBaseTest {
    @Autowired
    private CountryService countryService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    Country country;

    @BeforeEach
    void setUp() {
        country = new Country();
    }

    @Test
    @Order(1)
    void whenAddCountryMethodIsCalled_thenCountryIsSaved() throws Exception {
        String countryName = "Algeria";
        country = new Country();
        country.setCountryName(countryName);

        mockMvc.perform(post("/api/payroll/addCountry")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(country))
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.countryName").value(countryName))
                .andReturn();
    }

    @Test
    @Order(2)
    void whenFetchCountryMethodIsCalled_thenCountryIsRetrieved() throws Exception {
        mockMvc.perform(get("/api/payroll/country?countryName=Federal Republic of Nigeria")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.countryName").value("Federal Republic of Nigeria"))
                .andReturn();
    }

    @Test
    @Order(3)
    void whenFetchAllCountryMethodIsCalled_thenCountriesAreRetrieved() throws Exception {
        mockMvc.perform(get("/api/payroll/allCountries?pageNumber=0&pageSize=4")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$[0].countryName").value("Ghana"))
                .andReturn();
    }

    @Test
    @Order(4)
    void whenEditCountryMethodIsCalled_thenCountryIsUpdated() throws Exception {

        Long id = 802L;
        country = countryService.fetchCountry(id);
        country.setCountryName("Nigeria");
        countryService.editCountry(country, id);

        mockMvc.perform(put("/api/payroll/editCountry?id=802")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(country))
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.countryName").value("Nigeria"))
                .andReturn();
    }

    @Test
    @Order(5)
    void whenDeleteCountryMethodIsCalled_thenCountryIsDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/deleteCountry?id=1052")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    @Order(6)
    void whenDeleteAllCountryMethodIsCalled_thenCountriesAreDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}