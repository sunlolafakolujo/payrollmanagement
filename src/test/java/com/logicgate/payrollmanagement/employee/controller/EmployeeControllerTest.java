package com.logicgate.payrollmanagement.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgate.payrollmanagement.AbstractContainerBaseTest;
import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.address.service.AddressService;
import com.logicgate.payrollmanagement.country.model.Country;
import com.logicgate.payrollmanagement.country.service.CountryService;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.designation.service.DesignationService;
import com.logicgate.payrollmanagement.employee.model.AddRoleToEmployee;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.employee.service.EmployeeService;
import com.logicgate.payrollmanagement.image.model.Picture;
import com.logicgate.payrollmanagement.image.repository.PictureRepository;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.jobgroup.service.JobGroupService;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.nationality.service.NationalityService;
import com.logicgate.payrollmanagement.state.model.Province;
import com.logicgate.payrollmanagement.state.service.StateService;
import com.logicgate.payrollmanagement.staticdata.EmployeeStatus;
import com.logicgate.payrollmanagement.staticdata.Gender;
import com.logicgate.payrollmanagement.userrole.model.Role;
import com.logicgate.payrollmanagement.userrole.service.RoleService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.bouncycastle.util.Bytes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest extends AbstractContainerBaseTest {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JobGroupService jobGroupService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private NationalityService nationalityService;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    Employee employee;
    JobGroup jobGroup;
    Address address;
    Nationality nationality;
    Nationality nationality2;
    Picture picture;
    Province province;
    Country country;
    Role role;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        jobGroup = new JobGroup();
        address = new Address();
        nationality = new Nationality();
        nationality2 = new Nationality();
        picture = new Picture();
        province = new Province();
        country = new Country();
        role = new Role();
    }

    @Test
    @Order(1)
    void testThatWhenYouCallAddEmployeeMethod_thenEmployeeIsSaved() throws Exception {
        country = countryService.fetchCountryByName("Nigeria");
        province = stateService.fetchStateByName("Bayelsa");
        province = stateService.fetchState(402L);
        country = countryService.fetchCountry(802L);
        address.setHouseNumber("5");
        address.setStreetName("Osanyin Street, Alagomeji");
        address.setCity("Yaba");
        address.setLandmark("Tokunbo Street");
        address.setProvince(province);
        address.setCountry(country);
        jobGroup = jobGroupService.fetchJobGroupByCode("6");
        nationality.setNationality("Colombian");
        nationality2.setNationality("British");
        Set<Nationality> nationalities = new HashSet<>();
        nationalities.add(nationality);
        nationalities.add(nationality2);
        picture.setImageName("file");
        picture.setImageType("test-image.png");
        picture.setPicByte(new byte[Bytes.SIZE]);

        employee.setFirstName("John");
        employee.setLastName("Tony");
        employee.setOtherNames("Ojay");
        employee.setDateOfBirth(LocalDate.parse("2004-11-14"));
        employee.setStateOfOrigin("Lagos");
        employee.setGender(Gender.MALE);
        employee.setUsername("username");
        employee.setPassword("password");
        employee.setConfirmPassword("password");
        employee.setEmail("john.tony@companyname.com");
        employee.setMobile("08097654311");
        employee.setNationalIdentificationNumber("123456");
        employee.setHiredDate(LocalDate.parse("2023-06-01"));
        employee.setEmployeeStatus(EmployeeStatus.REGULAR);
        employee.setNationalities(nationalities);
        employee.setAddress(address);
        employee.setPicture(picture);

        this.mockMvc.perform(post("/api/payroll/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.email").value("john.tony@companyname.com"))
                .andExpect(jsonPath("$.mobile").value("08097654311"))
                .andExpect(jsonPath("$.hiredDate").value("2023-06-01"))
                .andReturn();


    }

    @Test
    @Order(2)
    void testThatWhenYouAddRoleToEmployeeMethod_RoleIsAddedToEmployee() throws Exception {
        Role role = roleService.fetchByRoleName("admin");
        employee = employeeService.fetchUsernameOrEmailOrMobileOrEmployeeId("username");
        AddRoleToEmployee addRoleToEmployee = new AddRoleToEmployee();
        addRoleToEmployee.setUsernameOrEmailOrMobileOrEmployeeId(employee.getUsername());
        addRoleToEmployee.setRoleName(role.getRoleName());

        this.mockMvc.perform(post("/api/payroll/addRoleToEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addRoleToEmployee))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    void getEmployeeById() {
    }

    @Test
    @Order(4)
    void getEmployeeByUsernameOrEmailOrMobileOrEmployeeId() {
    }

    @Test
    @Order(5)
    void getAllEmployees() {
    }

    @Test
    @Order(6)
    void getEmployees() {
    }

    @Test
    @Order(7)
    void getEmployeesBetweenADatePeriod() {
    }

    @Test
    @Order(8)
    void editEmployee() {
    }

    @Test
    @Order(9)
    void deleteEmployee() {
    }

    @Test
    @Order(10)
    void deleteAllEmployees() {
    }
}