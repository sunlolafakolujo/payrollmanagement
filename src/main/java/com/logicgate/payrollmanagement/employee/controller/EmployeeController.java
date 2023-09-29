package com.logicgate.payrollmanagement.employee.controller;

import com.logicgate.payrollmanagement.employee.model.*;
import com.logicgate.payrollmanagement.employee.service.EmployeeService;
import com.logicgate.payrollmanagement.image.model.Picture;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/payroll")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/addEmployee", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostEmployee> addEmployee(@RequestPart("employee") PostEmployee postEmployee,
                                                    @RequestPart("picture") MultipartFile multipartFile) throws IOException {
        Picture picture = new Picture();
        try {
            picture = pictureUpload(multipartFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        postEmployee.setPicture(picture);
        Employee employee = modelMapper.map(postEmployee, Employee.class);
        Employee post = employeeService.addEmployee(employee);
        PostEmployee posted = modelMapper.map(post, PostEmployee.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @PostMapping("/addRoleToEmployee")
    public ResponseEntity<?> addRoleToEmployee(@RequestParam("searchKey") String searchKey,
                                               @RequestParam("roleName") String roleName) {
        employeeService.addRoleToEmployee(searchKey, roleName);
        return ResponseEntity.ok().body("Role successfully added to Employee");
    }

    @GetMapping("/findEmployeeById")
    public ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(convertEmployeeToDto(employeeService.fetchEmployeeById(id)), HttpStatus.OK);
    }

    @GetMapping("/findByUsernameOrEmailOrMobileOrEmployeeId")
    public ResponseEntity<EmployeeDto> getEmployeeByUsernameOrEmailOrMobileOrEmployeeId(@RequestParam("searchKey") String searchKey) {
        return new ResponseEntity<>(convertEmployeeToDto(employeeService
                .fetchUsernameOrEmailOrMobileOrEmployeeId(searchKey)), HttpStatus.OK);
    }

    @GetMapping("/findAllEmployees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestParam("searchKey") String searchKey,
                                                             @RequestParam("pageNumber") int pageNumber,
                                                             @RequestParam("pageSize") int pageSize) {

        return new ResponseEntity<>(employeeService.fetchAllEmployees(searchKey, pageNumber, pageSize)
                .stream()
                .map(this::convertEmployeeToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/findEmployees")
    public ResponseEntity<List<EmployeeDto2>> getEmployees(@RequestParam("searchKey") String searchKey,
                                                           @RequestParam("pageNumber") int pageNumber,
                                                           @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(employeeService.fetchAllEmployees(searchKey, pageNumber, pageSize)
                .stream()
                .map(this::convertEmployeeToDto2)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/findEmployeesByHiredBetweenADatePeriod")
    public ResponseEntity<List<EmployeeDto>> getEmployeesBetweenADatePeriod(@RequestParam("date1") LocalDate date1,
                                                                            @RequestParam("date2") LocalDate date2,
                                                                            @RequestParam("pageNumber") int pageNumber,
                                                                            @RequestParam("pageSize") int pageSize) {

        return new ResponseEntity<>(employeeService.fetchEmployeeHiredDateBetweenAPeriod(date1, date2,
                        pageNumber, pageSize).stream()
                .map(this::convertEmployeeToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editEmployee")
    public ResponseEntity<EditEmployee> editEmployee(@RequestBody EditEmployee editEmployee,
                                                     @RequestParam("employeeId") String employeeId) {

        Employee employee = modelMapper.map(editEmployee, Employee.class);
        Employee post = employeeService.editEmployee(employee, employeeId);
        EditEmployee posted = modelMapper.map(post, EditEmployee.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<?> deleteEmployee(@RequestParam("employeeId") String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllEmployees")
    public ResponseEntity<?> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return ResponseEntity.noContent().build();
    }


    private Picture pictureUpload(MultipartFile multipartFile) throws IOException {
        Picture picture = new Picture(multipartFile.getOriginalFilename(),
                multipartFile.getContentType(), multipartFile.getBytes());
        return picture;
    }

    private EmployeeDto convertEmployeeToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setOtherNames(employee.getOtherNames());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setAge(employee.getAge());
        employeeDto.setStateOfOrigin(employee.getStateOfOrigin());
        employeeDto.setGender(employee.getGender());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setMobile(employee.getMobile());
        employeeDto.setNationalIdentificationNumber(employee.getNationalIdentificationNumber());
        employeeDto.setNationalIdentificationDateIssued(employee.getNationalIdentificationDateIssued());
        employeeDto.setHiredDate(employee.getHiredDate());
        employeeDto.setRetirementDate(employee.getRetirementDate());
        employeeDto.setEmployeeStatus(employee.getEmployeeStatus());
        employeeDto.setNationalities(employee.getNationalities());
        employeeDto.setHouseNumber(employee.getAddress().getHouseNumber());
        employeeDto.setStreetName(employee.getAddress().getStreetName());
        employeeDto.setCity(employee.getAddress().getCity());
        employeeDto.setLandmark(employee.getAddress().getLandmark());
        employeeDto.setStateName(employee.getAddress().getProvince().getStateName());
        employeeDto.setCountryName(employee.getAddress().getCountry().getCountryName());
        employeeDto.setPicture(employee.getPicture());
        employeeDto.setNextOfKins(employee.getNextOfKins());
        return employeeDto;
    }

    private EmployeeDto2 convertEmployeeToDto2(Employee employee) {
        EmployeeDto2 employeeDto = new EmployeeDto2();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setOtherNames(employee.getOtherNames());
        employeeDto.setGender(employee.getGender());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setMobile(employee.getMobile());
        employeeDto.setPicture(employee.getPicture());
        return employeeDto;
    }

}
