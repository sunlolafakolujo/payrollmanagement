package com.logicgate.payrollmanagement.salary.model;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostSalary {
    private BigDecimal salaryAmount;
    private JobGroup jobGroup;
    private Employee employee;
}
