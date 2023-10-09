package com.logicgate.payrollmanagement.salary.controller;

import com.logicgate.payrollmanagement.salary.model.*;
import com.logicgate.payrollmanagement.salary.service.SalaryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/payroll")
public record SalaryController(SalaryService salaryService, ModelMapper modelMapper) {

    @PostMapping("/addMonthlySalaryEarner")
    public ResponseEntity<PostSalary> addSalary(@RequestParam("employeeId") String employeeId,
                                                @RequestBody PostSalary postSalary) {
        Salary salary = modelMapper.map(postSalary, Salary.class);
        Salary post = salaryService.addMonthlySalaryEarner(employeeId, salary);
        PostSalary posted = modelMapper.map(post, PostSalary.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @PostMapping("/addDayRateSalaryEarner")
    public ResponseEntity<PostDayRateSalary> addDayRateSalary(@RequestParam("employeeId") String employeeId,
                                                @RequestBody PostDayRateSalary postSalary) {
        Salary salary = modelMapper.map(postSalary, Salary.class);
        Salary post = salaryService.addDayRateSalaryEarner(employeeId, salary);
        PostDayRateSalary posted = modelMapper.map(post, PostDayRateSalary.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/findSalaryById")
    public ResponseEntity<SalaryDto> getSalaryById(@RequestParam("id") Long id) throws Exception {
        return new ResponseEntity<>(convertSalaryToDto(salaryService.fetchSalaryById(id)), HttpStatus.OK);
    }

    @GetMapping("findSalaryByEmployeeId")
    public ResponseEntity<SalaryDto> getEmployeeSalary(@RequestParam("employeeId") String employeeId) {
        return new ResponseEntity<>(convertSalaryToDto(salaryService.fetchEmployeeSalary(employeeId)), HttpStatus.OK);
    }

    @GetMapping("/findEmployeeSalaryWithARange")
    public ResponseEntity<List<SalaryDto>> getEmployeeAnnualSalaryWithARange(@RequestParam("key1") BigDecimal salary1,
                                                                             @RequestParam("key2") BigDecimal salary2,
                                                                             @RequestParam("pageNumber") int pageNumber,
                                                                             @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(salaryService.fetchEmployeesWithinSalaryRange(salary1, salary2, pageNumber, pageSize)
                .stream()
                .map(this::convertSalaryToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("findAllEmployeeSalary")
    public ResponseEntity<List<SalaryDto>> getAllEmployeeSalary(@RequestParam("pageNumber") int pageNumber,
                                                                @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(salaryService.fetchAllSalaries(pageNumber, pageSize)
                .stream()
                .map(this::convertSalaryToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editSalary")
    public ResponseEntity<EditSalary> editSalary(@RequestBody EditSalary editSalary,
                                                 @RequestParam("employeeId") String employeeId) {
        Salary salary = modelMapper.map(editSalary, Salary.class);
        Salary post = salaryService.editSalary(salary, employeeId);
        EditSalary posted = modelMapper.map(post, EditSalary.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployeeSalary")
    public ResponseEntity<?> deleteEmployeeSalary(@RequestParam("employeeId") String employeeId) {
        salaryService.deleteSalaryByEmployeeId(employeeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllEmployeesSalaries")
    public ResponseEntity<?> deleteAllEmployeesSalaries() {
        salaryService.deleteAllSalaries();
        return ResponseEntity.noContent().build();
    }


    private SalaryDto convertSalaryToDto(Salary salary) {
        SalaryDto salaryDto = new SalaryDto();
        salaryDto.setId(salary.getId());
        salaryDto.setEmployeeId(salary.getEmployee().getEmployeeId());
        salaryDto.setFirstName(salary.getEmployee().getFirstName());
        salaryDto.setLastName(salary.getEmployee().getLastName());
        salaryDto.setJobGroupCode(salary.getJobGroup().getJobGroupCode());
        salaryDto.setDayRateAmount(salary.getDayRateAmount());
        salaryDto.setNumberOfDaysWorkedPerMonth(salary.getNumberOfDaysWorkedPerMonth());
        salaryDto.setMonthlySalaryAmount(salary.getMonthlySalaryAmount());
        salaryDto.setAnnualSalaryAmount(salary.getAnnulSalaryAmount());
        salaryDto.setAllowances(salary.getJobGroup().getAllowances());
        return salaryDto;
    }
}
