package com.logicgate.payrollmanagement.leave.repository;

import com.logicgate.payrollmanagement.leave.model.EmployeeLeave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long> {
}
