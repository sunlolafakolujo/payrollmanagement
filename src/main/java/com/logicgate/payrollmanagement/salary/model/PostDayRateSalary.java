package com.logicgate.payrollmanagement.salary.model;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.staticdata.SalaryType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDayRateSalary {
    private SalaryType salaryType;
    private BigDecimal dayRateAmount;
    private Integer numberOfDaysWorkedPerMonth;
    private BigDecimal monthlySalaryAmount;
    private BigDecimal annulSalaryAmount;
    private Employee employee;
}
