package com.logicgate.payrollmanagement.overtime.repository;

import com.logicgate.payrollmanagement.overtime.model.Overtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OvertimeRepository extends JpaRepository<Overtime,Long>, OvertimeRepositoryCustom {
}
