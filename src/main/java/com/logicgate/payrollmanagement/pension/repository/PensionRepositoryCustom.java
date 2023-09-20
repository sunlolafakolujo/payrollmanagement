package com.logicgate.payrollmanagement.pension.repository;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.pension.model.Pension;
import org.springframework.data.jpa.repository.Query;

public interface PensionRepositoryCustom {
    @Query("From Pension p Where p.pensionId=?1")
    Pension findByPensionId(String pensionId);

    @Query("From Pension p Where p.employee=?1")
    Pension findByEmployee(Employee employee);
}
