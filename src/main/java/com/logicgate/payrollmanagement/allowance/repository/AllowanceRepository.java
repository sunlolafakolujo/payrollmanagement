package com.logicgate.payrollmanagement.allowance.repository;

import com.logicgate.payrollmanagement.allowance.model.Allowance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllowanceRepository extends JpaRepository<Allowance, Long> {
}
