package com.logicgate.payrollmanagement.salary.repository;

import com.logicgate.payrollmanagement.salary.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long>, SalaryRepositoryCustom {
}
