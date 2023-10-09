package com.logicgate.payrollmanagement.pension.repository;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.pension.model.Pension;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PensionRepositoryCustom {
    @Query("From Pension p Where p.pensionId=?1")
    Optional<Pension> findByPensionId(String pensionId);

    @Query("From Pension p Where p.employee=?1")
    Pension findByEmployee(Employee employee);

    @Modifying
    @Query("Delete From Pension p Where p.pensionId=?1")
    void deletePensionById(String pensionId);
}
