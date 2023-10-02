package com.logicgate.payrollmanagement.employeedesignation.service;

import com.logicgate.payrollmanagement.employeedesignation.model.EmployeeDesignation;

import java.util.List;

public interface EmployeeDesignationService {
    void addEmployeeDesignation(String employeeId, String designationTitle);
    List<EmployeeDesignation> fetchEmployeesDesignation(int pageNumber, int pageSize);
    EmployeeDesignation editEmployeeDesignation(EmployeeDesignation employeeDesignation, Long id);
    void deleteEmployeeDesignation(Long id);
}
