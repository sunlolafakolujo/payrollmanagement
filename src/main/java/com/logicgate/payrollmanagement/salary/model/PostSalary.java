package com.logicgate.payrollmanagement.salary.model;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.staticdata.SalaryType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostSalary {
    private SalaryType salaryType;
    private BigDecimal monthlySalaryAmount;
    private BigDecimal annulSalaryAmount;
    private JobGroup jobGroup;
    private Employee employee;
}
