package com.logicgate.payrollmanagement.employeedesignation.model;

import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.employee.model.Employee;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

public class EmployeeDesignation {
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Designation designation;
}
