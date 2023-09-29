package com.logicgate.payrollmanagement.supervisor.model;

import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.employeedesignation.model.EmployeeDesignation;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

public class Supervisor extends BaseAudit {
    private Long id;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private EmployeeDesignation employeeDesignation;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Employee supervisor;
}
