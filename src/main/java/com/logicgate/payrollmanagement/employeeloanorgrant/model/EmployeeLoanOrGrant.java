package com.logicgate.payrollmanagement.employeeloanorgrant.model;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.grantorloan.model.GrantLoan;
import com.logicgate.payrollmanagement.staticdata.ApprovalStatus;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.util.Date;

public class EmployeeLoanOrGrant {
//    private Long id;
//    @OneToOne(cascade = CascadeType.ALL)
//    private GrantLoan grantLoan;
//
//    //HTTPSERVELET to the controller post request
//    @Enumerated(EnumType.STRING)
//    private ApprovalStatus approvalStatus=ApprovalStatus.PENDING;
//
//    private Date approvalDate;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Employee employee;
}
