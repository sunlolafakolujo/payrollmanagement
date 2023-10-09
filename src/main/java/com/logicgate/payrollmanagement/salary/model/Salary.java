package com.logicgate.payrollmanagement.salary.model;

import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.staticdata.SalaryType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Salary extends BaseAudit {
    @Id
    @SequenceGenerator(name = "salary_generator",
            sequenceName = "salary_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="salary_generator")
    private Long id;

    @Enumerated(EnumType.STRING)
    private SalaryType salaryType;
    private BigDecimal dayRateAmount;
    private Integer numberOfDaysWorkedPerMonth;
    private BigDecimal monthlySalaryAmount;
    private BigDecimal annulSalaryAmount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private JobGroup jobGroup;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Employee employee;

}
