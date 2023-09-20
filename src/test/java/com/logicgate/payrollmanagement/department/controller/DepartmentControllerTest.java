package com.logicgate.payrollmanagement.department.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgate.payrollmanagement.AbstractContainerBaseTest;
import com.logicgate.payrollmanagement.department.model.Department;
import com.logicgate.payrollmanagement.department.service.DepartmentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
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
class DepartmentControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Department department;

    @BeforeEach
    void setUp() {
        department=new Department();
    }

    @Test
    @Order(1)
    void testThatWhenAddDepartmentMethodIsCalled_thenDepartmentIsSaved() throws Exception {
        department.setCostCentre("4092");
        department.setDepartmentName("Human Resources");
        department.setDepartmentLocation("Lagos");

        mockMvc.perform(post("/api/payroll/addDepartment")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department))
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.costCentre").value("4092"))
                .andExpect(jsonPath("$.departmentName").value("Human Resources"))
                .andReturn();
    }

    @Test
    @Order(2)
    void testThatWhenYouCallFetchDepartmentMethod_thenDepartmentIsReturned() throws Exception {
        mockMvc.perform(get("/api/payroll/findDepartment?searchKey=Business Services")
                        .contentType(APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.costCentre").value("4072"))
                .andReturn();
    }

    @Test
    @Order(3)
    void testThatWhenYouCallFetchAllDepartments_thenDepartmentsAreReturned() throws Exception {
        mockMvc.perform(get("/api/payroll/allDepartments?pageNumber=0&pageSize=5")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$[2].departmentName").value("Business Services"))
                .andReturn();
    }

    @Test
    @Order(4)
    void testThatWhenEditDepartmentMethodIsCalled_thenDepartmentIsUpdated() throws Exception {
        String dept = "4046";
        department = departmentService.fetchDepartmentByCostCentreOrName(dept);
        department.setDepartmentName("Materials and Logistics");
        departmentService.editDepartment(department, dept);

        mockMvc.perform(put("/api/payroll/editDepartment?searchKey=4046")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department))
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(department.getId()))
                .andExpect(jsonPath("$.departmentName").value("Materials and Logistics"))
                .andReturn();
    }

    @Test
    @Order(5)
    void testThatWhenDeleteDepartmentMethodIsCalled_thenDepartmentIsDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/deleteDepartment?searchKey=Human Resources")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    @Order(6)
    void testThatWhenDeleteAllDepartmentMethodIsCalled_thenDepartmentsAreDeleted() throws Exception {
        mockMvc.perform(delete("/api/payroll/deleteAllDepartment")
                        .contentType(APPLICATION_JSON)
                        .header(AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}