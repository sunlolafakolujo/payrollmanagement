package com.logicgate.payrollmanagement.leave.service;

import com.logicgate.payrollmanagement.leave.model.EmployeeLeave;

import java.util.List;

public interface EmployeeLeaveService {
    EmployeeLeave addLeave(EmployeeLeave leave);
    EmployeeLeave fetchEmployeeLeave(String employeeId);
    List<EmployeeLeave> fetchAllLeave(int pageNumber, int pageSize);
    void rejectEmployeeLeave(String employeeId);
}
