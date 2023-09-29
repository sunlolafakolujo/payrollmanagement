package com.logicgate.payrollmanagement.employee.service;

import com.logicgate.payrollmanagement.employee.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    void addRoleToEmployee(String searchKey, String roleName);

    Employee fetchEmployeeById(Long id);

    Employee fetchUsernameOrEmailOrMobileOrEmployeeId(String searchKey);

    List<Employee> fetchAllEmployees(String searchKey, int pageNumber, int pageSize);

    List<Employee> fetchEmployeeHiredDateBetweenAPeriod(LocalDate date1, LocalDate date2, int pageNumber, int pageSize);

    Employee editEmployee(Employee employee, String employeeId);

    void deleteEmployee(String employeeId);

    void deleteAllEmployees();
}
