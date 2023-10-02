package com.logicgate.payrollmanagement.employeedesignation.model;

import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.employee.model.Employee;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDesignation extends BaseAudit {
    @Id
    @SequenceGenerator(name = "employee_designation_generator",
            sequenceName = "employee_designation_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employee_designation_generator")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Designation designation;

    public EmployeeDesignation(Employee employee, Designation designation) {
        this.employee = employee;
        this.designation = designation;
    }
}
