package com.logicgate.payrollmanagement.allowance.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgate.payrollmanagement.AbstractContainerBaseTest;
import com.logicgate.payrollmanagement.allowance.model.Allowance;
import com.logicgate.payrollmanagement.allowance.service.AllowanceService;
import com.logicgate.payrollmanagement.staticdata.AllowanceType;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AllowanceControllerTest extends AbstractContainerBaseTest {
    @Autowired
    private AllowanceService allowanceService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    Allowance allowance;

    @BeforeEach
    void setUp() {
        allowance = new Allowance();
    }

    @Test
    void testThatWhenAddAllowanceMethodIsCalled_thenAllowanceIsAdded() throws Exception {
//        allowance.setAllowanceType(AllowanceType.BUSH);
        allowance.setTransportationAllowance(new BigDecimal(250000));

        mockMvc.perform(post("/api/payroll/addAllowance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(allowance))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.allowanceType").value("BUSH"))
                .andExpect(jsonPath("$.allowanceAmount").value(250000))
                .andReturn();
    }

    @Test
    void testThatWhenYouCallGetAllowancesMethod_thenAllowancesAreReturned() throws Exception {
        mockMvc.perform(get("/api/payroll/?pageNumber=0&pageSize=5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$[1].allowanceType").value("HOUSING"))
                .andExpect(jsonPath("$[0].allowanceAmount").value(3500000))
                .andReturn();
    }

    @Test
    void testThatWhenGetAllowanceMethodIsCalled_thenAllowanceIsReturned() throws Exception {
        mockMvc.perform(get("/api/payroll/allowance?id=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allowanceAmount").value(3500000))
                .andReturn();
    }

    @Test
    void testThatWhenYouCallEditAllowanceMethod_thenAllowanceIsUpdated() throws Exception {
        Long id = 22L;
        allowance = allowanceService.fetchAllowanceById(id);
        allowance.setLunchAllowance(new BigDecimal(1056000));
        allowanceService.editAllowance(allowance, id);

        mockMvc.perform(put("/api/payroll/edit?id=22")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(allowance))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allowanceAmount").value(1056000))
                .andReturn();
    }

    @Test
    void testThatWhenYouCallDeleteAllowance_thenAllowanceIsDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/deleteAllowance?id=72")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void testThatWhenYouCallDeleteAllowanceMethod_thenAllowancesAreDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}