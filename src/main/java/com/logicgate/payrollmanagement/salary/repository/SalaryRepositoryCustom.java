package com.logicgate.payrollmanagement.salary.repository;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.salary.model.Salary;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SalaryRepositoryCustom {
    @Query("From Salary s Where s.employee=?1")
    Salary findSalaryByEmployee(Employee employee);

    @Modifying
    @Query("From Salary s Where s.employee=?1")
    void deleteSalaryByEmployee(Employee employee);
}
