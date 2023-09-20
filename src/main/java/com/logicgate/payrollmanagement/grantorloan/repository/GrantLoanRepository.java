package com.logicgate.payrollmanagement.grantorloan.repository;

import com.logicgate.payrollmanagement.grantorloan.model.GrantLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrantLoanRepository extends JpaRepository<GrantLoan,Long>,GrantLoanRepositoryCustom {
}
