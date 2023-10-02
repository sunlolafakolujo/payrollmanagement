package com.logicgate.payrollmanagement.employeedesignation.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmpDesignation {
    private String employeeIdOrUsernameOrMobileOEmail;
    private String designationTitle;
}
