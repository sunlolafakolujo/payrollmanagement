package com.logicgate.payrollmanagement.bank.service;

import com.logicgate.payrollmanagement.bank.model.EmployeeBankDetail;

import java.util.List;

public interface EmployeeBankDetailService {
    EmployeeBankDetail addBankDetails(String employeeId, EmployeeBankDetail employeeBankDetail);
    EmployeeBankDetail fetchEmployeeBankDetail(String employeeId);
    List<EmployeeBankDetail> fetchEmployeeBankDetails(int pageNumber, int pageSize);
    EmployeeBankDetail editBankDetail(String employeeId, EmployeeBankDetail employee);
    void deleteBankByEmployee(String employeeId);
}
