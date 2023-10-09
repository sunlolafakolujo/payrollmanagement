package com.logicgate.payrollmanagement.employeedesignation.controller;

import com.logicgate.payrollmanagement.employee.model.EditEmployee;
import com.logicgate.payrollmanagement.employeedesignation.model.EditEmployeeDesignation;
import com.logicgate.payrollmanagement.employeedesignation.model.EmpDesignation;
import com.logicgate.payrollmanagement.employeedesignation.model.EmployeeDesignation;
import com.logicgate.payrollmanagement.employeedesignation.model.EmployeeDesignationDto;
import com.logicgate.payrollmanagement.employeedesignation.service.EmployeeDesignationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/payroll")
public record EmployeeDesignationController(EmployeeDesignationService employeeDesignationService, ModelMapper modelMapper) {

    @PostMapping("/addDesignationToEmployee")
    public ResponseEntity<?> addDesignationToEmployee(@RequestBody EmpDesignation empDesignation) {
        employeeDesignationService.addEmployeeDesignation(empDesignation.getEmployeeIdOrUsernameOrMobileOEmail(),
                empDesignation.getDesignationTitle());
        return ResponseEntity.ok().body("Designation successfully add to employee");
    }

    @GetMapping("/findAllEmployeeDesignations")
    public ResponseEntity<List<EmployeeDesignationDto>> getAllEmployeeDesignations(@RequestParam("pageNumber") int pageNumber,
                                                                                   @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(employeeDesignationService.fetchEmployeesDesignation(pageNumber, pageSize)
                .stream()
                .map(this::convertEmployeeDesignationToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editEmployeeDesignation")
    public ResponseEntity<EditEmployeeDesignation> editEmployeeDesignation(@RequestBody EditEmployee editEmployee,
                                                                           @RequestParam("id") Long id) {
        EmployeeDesignation employeeDesignation = modelMapper.map(editEmployee, EmployeeDesignation.class);
        EmployeeDesignation post = employeeDesignationService.editEmployeeDesignation(employeeDesignation, id);
        EditEmployeeDesignation posted = modelMapper.map(post, EditEmployeeDesignation.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployeeDesignation")
    public ResponseEntity<?> deleteEmployeeDesignation(@RequestParam("id") Long id) {
        employeeDesignationService.deleteEmployeeDesignation(id);
        return ResponseEntity.noContent().build();
    }


    private EmployeeDesignationDto convertEmployeeDesignationToDto(EmployeeDesignation employeeDesignation) {
        EmployeeDesignationDto employeeDesignationDto = new EmployeeDesignationDto();
        employeeDesignationDto.setEmployeeId(employeeDesignation.getEmployee().getEmployeeId());
        employeeDesignationDto.setEmployeeFirstName(employeeDesignation.getEmployee().getFirstName());
        employeeDesignationDto.setEmployeeLastName(employeeDesignation.getEmployee().getLastName());
        employeeDesignationDto.setEmployeeDesignationTitle(employeeDesignation.getDesignation().getDesignationTitle());
        employeeDesignationDto.setEmployeeDepartmentName(employeeDesignation.getDesignation().getDepartment().getDepartmentName());
        return employeeDesignationDto;
    }

}
