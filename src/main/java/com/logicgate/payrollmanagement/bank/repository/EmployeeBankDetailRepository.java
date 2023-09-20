package com.logicgate.payrollmanagement.bank.repository;

import com.logicgate.payrollmanagement.bank.model.EmployeeBankDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeBankDetailRepository extends JpaRepository<EmployeeBankDetail,Long>,EmployeeBankDetailCustom {
}
