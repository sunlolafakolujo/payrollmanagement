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

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        String nationalityName = "American";
        this.mockMvc.perform(get("/api/payroll/findNationalityById?id=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.nationality").value(nationalityName))
                .andReturn();
    }

    @Test
    @Order(3)
    void testThatWhenYouCallGetNationalityByNameMethod_thenNationalityIsReturned() throws Exception {
        Long id = 12L;
        this.mockMvc.perform(get("/api/payroll/findNationalityByName?nationality=British")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(id))
                .andReturn();
    }

    @Test
    @Order(4)
    void testThatWhenYouCallGetAllNationalityMethod_thenNationalitiesAreReturned() throws Exception {
        String nationalityName = "American";
        this.mockMvc.perform(get("/api/payroll/findAllNationalities?pageNumber=0&pageSize=8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(8)))
                .andExpect(jsonPath("$[1].nationality").value(nationalityName))
                .andReturn();
    }

    @Test
    @Order(5)
    void testThatWhenYouCallEditNationalityMethod_thenNationalityIsUpdated() throws Exception {
        Long id = 1L;
        nationality = nationalityService.fetchById(id);
        nationality.setNationality("Nigeria");
        nationalityService.editNationality(nationality, id);

        this.mockMvc.perform(put("/api/payroll/editNationality?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .content(objectMapper.writeValueAsString(nationality)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(nationality.getId()))
                .andExpect(jsonPath("$.nationality").value(nationality.getNationality()))
                .andReturn();
    }

    @Test
    @Order(6)
    void testThatWhenYouCallDeleteNationalMethod_thenNationalityIsDeleted() throws Exception {
        this.mockMvc.perform(delete("/api/payroll/deleteNationality?nationality=Cameroonian")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    @Order(7)
    void testThatWhenYouCallDeleteAllNationalityMethod_thenNationalityAreDeleted() throws Exception {
        this.mockMvc.perform(delete("/api/payroll/deleteAllNationality")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}