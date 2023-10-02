package com.logicgate.payrollmanagement.employeedesignation.controller;

import com.logicgate.payrollmanagement.employeedesignation.model.EmpDesignation;
import com.logicgate.payrollmanagement.employeedesignation.service.EmployeeDesignationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/payroll")
//@AllArgsConstructor
public record EmployeeDesignationController(EmployeeDesignationService employeeDesignationService,ModelMapper modelMapper ) {
//    private final EmployeeDesignationService employeeDesignationService;
//    private final ModelMapper modelMapper;

    @PostMapping("/addDesignationToEmployee")
    public ResponseEntity<?> addDesignationToEmployee(@RequestBody EmpDesignation empDesignation){
        employeeDesignationService.addEmployeeDesignation(empDesignation.getEmployeeIdOrUsernameOrMobileOEmail(),
                empDesignation.getDesignationTitle());
        return ResponseEntity.ok().body("Designation successfully add to employee");
    }

}
