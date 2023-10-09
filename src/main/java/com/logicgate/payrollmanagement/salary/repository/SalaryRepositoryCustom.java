package com.logicgate.payrollmanagement.salary.repository;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.salary.model.Salary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SalaryRepositoryCustom {
    @Query("From Salary s Where s.employee=?1")
    Salary findSalaryByEmployee(Employee employee);

    @Query("From Salary s Where s.annulSalaryAmount Between ?1 and ?2")
    List<Salary> findEmployeeSalaryWithARange(BigDecimal key1, BigDecimal key2, PageRequest pageRequest);

    @Modifying
    @Query("From Salary s Where s.employee=?1")
    void deleteSalaryByEmployee(Employee employee);
}
