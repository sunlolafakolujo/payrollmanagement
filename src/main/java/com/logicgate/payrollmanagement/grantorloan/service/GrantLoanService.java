package com.logicgate.payrollmanagement.grantorloan.service;

import com.logicgate.payrollmanagement.grantorloan.model.GrantLoan;

import java.util.List;

public interface GrantLoanService {
    GrantLoan addLoan(GrantLoan grantLoan);
    GrantLoan fetchEmployeeLoan(String employeeId);
    List<GrantLoan> fetchAllEmployeeLoanOrGrant(String employee);
    void rejectGrantOrLoan(String employeeId);
}
