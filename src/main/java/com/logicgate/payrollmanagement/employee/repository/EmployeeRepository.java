package com.logicgate.payrollmanagement.employee.repository;

import com.logicgate.payrollmanagement.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long>, EmployeeRepositoryCustom {
}
