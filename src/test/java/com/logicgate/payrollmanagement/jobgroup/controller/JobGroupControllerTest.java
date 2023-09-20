package com.logicgate.payrollmanagement.jobgroup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgate.payrollmanagement.AbstractContainerBaseTest;
import com.logicgate.payrollmanagement.allowance.model.Allowance;
import com.logicgate.payrollmanagement.allowance.service.AllowanceService;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.jobgroup.service.JobGroupService;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JobGroupControllerTest extends AbstractContainerBaseTest {
    @Autowired
    private JobGroupService jobGroupService;
    @Autowired
    private AllowanceService allowanceService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    JobGroup jobGroup;
    Allowance allowance;
    Allowance allowance1;
    Allowance allowance2;
    Allowance allowance3;
    Allowance allowance4;

    @BeforeEach
    void setUp() {
        jobGroup = new JobGroup();
        allowance = new Allowance();
        allowance1 = new Allowance();
        allowance2 = new Allowance();
        allowance3 = new Allowance();
        allowance4 = new Allowance();
    }

    @Test
    void testThatWhenAddJobGroupMethodIsCalled_thenJobGroupIsAdded() throws Exception {

        allowance.setAllowanceType(AllowanceType.HOUSING);
        allowance.setAllowanceAmount(new BigDecimal(3500000));

        allowance1.setAllowanceType(AllowanceType.MEDICAL);
        allowance1.setAllowanceAmount(new BigDecimal(4000000));

        allowance2.setAllowanceType(AllowanceType.LUNCH);
        allowance2.setAllowanceAmount(new BigDecimal(105600));

        allowance3.setAllowanceType(AllowanceType.TRANSPORT);
        allowance3.setAllowanceAmount(new BigDecimal(1300000));

        Set<Allowance> allowances = new HashSet<>();
        allowances.add(allowance);
        allowances.add(allowance1);
        allowances.add(allowance2);
        allowances.add(allowance3);

        jobGroup.setJobGroupCode("6");
        jobGroup.setJobGroupDescription("Job group 6");
        jobGroup.setAllowances(allowances);

        mockMvc.perform(post("/api/payroll/addJobGroup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jobGroup))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.jobGroupCode").value("6"))
                .andReturn();
    }

    @Test
    void testThatWhenGetJobGroupMethodIsCalled_thenJobGroupIsReturned() throws Exception {
        mockMvc.perform(get("/api/payroll/findJobGroup?jobGroupCode=6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobGroupDescription").value("Job group 6"))
                .andReturn();
    }

    @Test
    void testThatWhenYouCallGetAllJobGroupsMethod_thenJobGroupsAreReturned() throws Exception {
        mockMvc.perform(get("/api/payroll/findAllJobGroup?pageNumber=0&pageSize=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].jobGroupCode").value("7"))
                .andReturn();
    }

    @Test
    void testThatWhenEditJobGroupMethodIsCalled_thenJobGroupIsUpdated() throws Exception {
        String jobGroupCode = "7";
        jobGroup = jobGroupService.fetchJobGroupByCode(jobGroupCode);
        jobGroup.setJobGroupDescription("Job group 7");

        jobGroupService.editJobGroup(jobGroup, jobGroupCode);

        mockMvc.perform(put("/api/payroll/editJobGroup?jobGroupCode=7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jobGroupCode))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(jobGroup.getId()))
                .andExpect(jsonPath("$.jobGroupDescription").value("Job group 7"))
                .andReturn();


    }

    @Test
    void testThatWhenYouCallDeleteJobGroupMethod_thenJobGroupIsDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/deleteJobGroup?jobGroupCode=6")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void testThatWhenYouCallDeleteAllJobGroupsMethod_thenJobGroupsAreDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/deleteAllJobGroups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}