package com.logicgate.payrollmanagement.salary.model;

import com.logicgate.payrollmanagement.employee.model.Employee;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDayRateSalary {
    private Long id;
    private BigDecimal dayRateAmount;
    private Integer numberOfDaysWorkedPerMonth;
    private BigDecimal monthlySalaryAmount;
    private BigDecimal annulSalaryAmount;
    private Employee employee;
}
