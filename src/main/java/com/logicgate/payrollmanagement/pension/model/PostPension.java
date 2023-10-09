package com.logicgate.payrollmanagement.pension.model;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.pensionmanager.model.PensionAdministrator;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostPension {
    private BigDecimal employeeContribution;
    private BigDecimal employerContribution;
    private BigDecimal pensionAmount;
    private Employee employee;
    private String employeePersonalEmail;
    private PensionAdministrator pensionAdministrator;
}
