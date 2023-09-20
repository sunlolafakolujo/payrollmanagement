package com.logicgate.payrollmanagement.department.repository;

import com.logicgate.payrollmanagement.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long>, DepartmentRepositoryCustom {
}
