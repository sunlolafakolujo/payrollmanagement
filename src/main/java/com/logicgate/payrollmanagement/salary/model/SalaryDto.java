package com.logicgate.payrollmanagement.salary.model;

import com.logicgate.payrollmanagement.allowance.model.Allowance;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
    private Long id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String jobGroupCode;
    private BigDecimal salaryAmount;
    private BigDecimal annualSalaryAmount;
    private Set<Allowance> allowances;
}
