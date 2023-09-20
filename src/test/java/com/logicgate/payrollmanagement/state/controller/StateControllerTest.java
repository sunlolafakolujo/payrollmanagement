package com.logicgate.payrollmanagement.state.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgate.payrollmanagement.AbstractContainerBaseTest;
import com.logicgate.payrollmanagement.state.model.Province;
import com.logicgate.payrollmanagement.state.service.StateService;
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
class StateControllerTest extends AbstractContainerBaseTest {
    @Autowired
    private StateService stateService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    Province province;

    @BeforeEach
    void setUp() {
        province=new Province();
    }

    @Test
    @Order(1)
    void whenAddStateMethodIsCalled_thenStateIsSaved() throws Exception {
        province = new Province();
        String stateName ="Kebbi";
        province.setStateName(stateName);

        mockMvc.perform(post("/api/payroll/addState")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(province))
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.stateName").value(stateName))
                .andReturn();
    }

    @Test
    @Order(2)
    void whenFetchStateMethodIsCalled_thenStateIsRetrieved() throws Exception {
        mockMvc.perform(get("/api/payroll/findState?stateName=Oyo")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.stateName").value("Oyo"))
                .andReturn();
    }

    @Test
    @Order(3)
    void whenFetchAllStatesMethodIsCalled_thenStatesAreRetrieved() throws Exception {
        mockMvc.perform(get("/api/payroll/allStates?pageNumber=0&pageSize=7")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(7)))
                .andExpect(jsonPath("$[3].stateName").value("Osun"))
                .andReturn();
    }

    @Test
    @Order(4)
    void whenEditStateMethodIsCalled_thenStateIsUpdated() throws Exception {

        Long id=132L;
        province=stateService.fetchState(id);
        province.setStateName("Sun Shine");
        stateService.editState(province, id);

        mockMvc.perform(put("/api/payroll/editProvinceById?id=132")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(province))
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(province.getId()))
                .andExpect(jsonPath("$.stateName").value("Sun Shine"))
                .andReturn();
    }

    @Test
    @Order(5)
    void whenDeleteStateMethodIsCalled_thenStateIsDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/state?id=272")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    @Order(6)
    void whenDeleteAllStateMethodIsCalled_thenStatesAreDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/allStates")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}