package com.logicgate.payrollmanagement.userrole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgate.payrollmanagement.userrole.model.Role;
import com.logicgate.payrollmanagement.userrole.service.RoleService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
class RoleControllerTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
    }

    @Test
    @Order(1)
    void testThatWhenYouCallAddRoleMethod_thenRoleIsCreated() throws Exception {
        String roleName = "admin";
        role.setRoleName(roleName);
        role.setRoleDescription("Payroll Administrator");

        this.mockMvc.perform(post("/api/payroll/addRole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(role))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.roleName").value(roleName))
                .andReturn();
    }

    @Test
    @Order(2)
    void testThatWhenYouCallGetRoleByIdMethod_thenRoleIsReturned() throws Exception {
        String roleName = "admin";
        this.mockMvc.perform(get("/api/payroll/findRoleById?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.roleName").value(roleName))
                .andReturn();
    }

    @Test
    @Order(3)
    void testThatWhenYouCallGetRoleByNameMethod_thenRoleIsReturned() throws Exception {
        String roleDescription = "Payroll Administrator";
        this.mockMvc.perform(get("/api/payroll/findRoleName?roleName=payroll_admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.roleDescription").value(roleDescription))
                .andReturn();
    }

    @Test
    @Order(4)
    void testThatWhenYouCallGetAllRolesMethod_thenRoleAreReturned() throws Exception {
        String roleName = "super_admin";
        this.mockMvc.perform(get("/api/payroll/findAllRoles?pageNumber=0&pageSize=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].roleName").value(roleName))
                .andReturn();
    }

    @Test
    @Order(5)
    void testThatWhenYouCallEditRoleMethod_thenRoleIsUpdated() throws Exception {
        Long id = 22L;
        role = roleService.fetchById(id);
        role.setRoleDescription("Administrator");
        roleService.editRole(role, id);

        this.mockMvc.perform(put("/api/payroll/editRole?id=22")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(role))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(role.getId()))
                .andExpect(jsonPath("$.roleName").value("admin"))
                .andReturn();
    }

    @Test
    @Order(6)
    void testThatWhenYouCallDeleteRoleByNameMethod_thenRoleIsDeleted() throws Exception {
        this.mockMvc.perform(delete("/api/payroll/deleteRole?roleName=payroll_admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    @Order(7)
    void testThatWhenYouCallDeleteAllRoles_thenRolesAreDeleted() throws Exception {
        this.mockMvc.perform(delete("/api/payroll/deleteAllRoles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}