package com.logicgate.payrollmanagement.salary.service;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.salary.model.Salary;

import java.util.List;
import java.util.Optional;

public interface SalaryService {
    Salary addSalary(String employeeId, Salary salary);
    Salary fetchSalaryById(Long id);
    Salary fetchEmployeeSalary(String employeeId);
    List<Salary> fetchAllSalaries(int pageNumber, int pageSize);
    Salary editSalary(Salary salary, String employeeId);
    void deleteSalaryByEmployeeId(String employeeId);
    void deleteAllSalaries();
}
