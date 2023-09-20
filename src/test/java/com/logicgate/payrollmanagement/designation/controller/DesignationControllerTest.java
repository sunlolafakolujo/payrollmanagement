package com.logicgate.payrollmanagement.designation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgate.payrollmanagement.AbstractContainerBaseTest;
import com.logicgate.payrollmanagement.department.model.Department;
import com.logicgate.payrollmanagement.department.service.DepartmentService;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.designation.service.DesignationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
class DesignationControllerTest extends AbstractContainerBaseTest {
    @Autowired
    private DesignationService designationService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    Designation designation;
    Department department;

    @BeforeEach
    void setUp() {
        designation = new Designation();
        designation = new Designation();
    }

    @Test
    void testThatWhenAddDesignationMethodIsCalled_thenDesignationIsSafe() throws Exception {
        department = departmentService.fetchDepartmentByCostCentreOrName("Business Services");
        designation = new Designation();
        designation.setDesignationTitle("Head Treasury");
        designation.setDepartment(department);

        mockMvc.perform(post("/api/payroll/addDesignation")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(designation))
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.designationTitle").value("Head Treasury"))
                .andReturn();
    }

    @Test
    void testThatWhenFetchDesignationIsCalled_thenDesignationIsReturned() throws Exception {
        mockMvc.perform(get("/api/payroll/designation?designationTitle=Treasury Officer")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.designationTitle").value("Treasury Officer"))
                .andReturn();
    }

    @Test
    void testThatWhenFetchAllDesignationMethodIsCalled_thenDesignationsAreReturned() throws Exception {
        mockMvc.perform(get("/api/payroll/allDesignations?pageNumber=0&pageSize=4")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$[1].designationTitle").value("Finance Supervisor"))
                .andReturn();
    }

    @Test
    void testThatWhenEditDesignationIsCalled_thenDesignationIsUpdated() throws Exception {
        Long id = 102L;
        designation = designationService.fetchDesignationById(id);

        designation.setDesignationTitle("Senior Treasury Officer");
        designationService.editDesignation(designation, id);

        mockMvc.perform(put("/api/payroll/editDesignation?id=102")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(designation.getId()))
                .andExpect(jsonPath("$.designationTitle").value("Senior Treasury Officer"))
                .andReturn();
    }

    @Test
    void testThatWhenDeleteDesignationIsCalled_thenDesignationIsDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/deleteDesignation?designationTitle=Senior Treasury Officer")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void testThatWhenDeleteAllDesignationsIsCalled_thenDesignationsAreDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/deleteAllDesignations")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}