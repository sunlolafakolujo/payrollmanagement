package com.logicgate.payrollmanagement.grantorloan.repository;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.grantorloan.model.GrantLoan;
import org.springframework.data.jpa.repository.Query;

public interface GrantLoanRepositoryCustom {
    @Query("From GrantLoan g Where g.employee=?1")
    GrantLoan findByEmployee(Employee employee);
}
