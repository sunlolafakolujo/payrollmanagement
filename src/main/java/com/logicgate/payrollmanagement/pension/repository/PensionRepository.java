package com.logicgate.payrollmanagement.pension.repository;

import com.logicgate.payrollmanagement.pension.model.Pension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PensionRepository extends JpaRepository<Pension,Long>, PensionRepositoryCustom {
}
