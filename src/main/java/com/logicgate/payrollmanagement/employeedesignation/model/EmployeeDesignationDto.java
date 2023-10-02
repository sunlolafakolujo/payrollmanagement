package com.logicgate.payrollmanagement.employeedesignation.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDesignationDto {
    private Long id;
    private String employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeDesignationTitle;
    private String employeeDepartmentName;
}
