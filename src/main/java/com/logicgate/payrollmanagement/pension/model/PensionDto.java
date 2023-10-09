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
public class PensionDto {
    private Long id;
    private BigDecimal annualPensionAmount;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String administratorName;
    private String pensionAdministratorContactPerson;
    private String pensionAdministratorEmail;
    private String pensionAdministratorPhone;
}
