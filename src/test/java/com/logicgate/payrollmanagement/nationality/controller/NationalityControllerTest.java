package com.logicgate.payrollmanagement.nationality.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgate.payrollmanagement.AbstractContainerBaseTest;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.nationality.service.NationalityService;
import org.junit.jupiter.api.*;
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
class NationalityControllerTest extends AbstractContainerBaseTest {
    @Autowired
    private NationalityService nationalityService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    Nationality nationality;

    @BeforeEach
    void setUp() {
        nationality = new Nationality();
    }

    @Test
    @Order(1)
    void testThatWhenYouCallAddNationalityMethod_thenNationalityIsSaved() throws Exception {
        String nationalityName = "Cameroonian";
        nationality.setNationality(nationalityName);
        this.mockMvc.perform(post("/api/payroll/addNationality")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nationality))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.nationality").value(nationalityName))
                .andReturn();
    }

    @Test
    @Order(2)
    void testThatWhenGetNationalityByIdMethodIsCalled_thenNationalityIsReturned() throws Exception {
        this.mockMvc.perform(get("/api/payroll/findNationalityById?id=12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.nationality").value("American"))
                .andReturn();
    }

    @Test
    @Order(3)
    void getNationalityByName() {
    }

    @Test
    @Order(4)
    void findAllNationality() {
    }

    @Test
    @Order(5)
    void editNationality() {
    }

    @Test
    @Order(6)
    void deleteNational() {
    }

    @Test
    @Order(7)
    void deleteAllNationality() {
    }
}