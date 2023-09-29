package com.logicgate.payrollmanagement.grantorloan.model;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.staticdata.ApprovalStatus;
import com.logicgate.payrollmanagement.staticdata.GrantOrLoan;
import com.logicgate.payrollmanagement.staticdata.GrantOrLoanType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostGrant {
    private GrantOrLoan grantOrLoan;
    private GrantOrLoanType grantOrLoanType;
    private BigDecimal loanOrGrantAmount;
    private ApprovalStatus approvalStatus;
    private Date approvalDate;
    private Employee employee;
//    private String supervisorApproval;
}
