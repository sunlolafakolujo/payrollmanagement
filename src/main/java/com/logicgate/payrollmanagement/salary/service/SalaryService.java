package com.logicgate.payrollmanagement.salary.service;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.salary.model.Salary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SalaryService {
    Salary addMonthlySalaryEarner(String employeeId, Salary salary);
    Salary addDayRateSalaryEarner(String employeeId, Salary salary);
    Salary fetchSalaryById(Long id);
    Salary fetchEmployeeSalary(String employeeId);
    List<Salary> fetchEmployeesWithinSalaryRange(BigDecimal searchKey1, BigDecimal searchKey2, int pageNumber, int pageSize);
    List<Salary> fetchAllSalaries(int pageNumber, int pageSize);
    Salary editSalary(Salary salary, String employeeId);
    void deleteSalaryByEmployeeId(String employeeId);
    void deleteAllSalaries();
}
