package com.logicgate.payrollmanagement.salary.model;

import com.logicgate.payrollmanagement.employee.model.Employee;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditSalary {
    private Long id;
    private BigDecimal monthlySalaryAmount;
    private BigDecimal dayRateAmount;
    private Employee employee;
}
