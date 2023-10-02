package com.logicgate.payrollmanagement.bank.repository;

import com.logicgate.payrollmanagement.bank.model.EmployeeBankDetail;
import com.logicgate.payrollmanagement.employee.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeBankDetailCustom {
    @Query("From EmployeeBankDetail b Where b.employee=?1")
    EmployeeBankDetail findByEmployee(Employee employee);

    @Modifying
    @Query("Delete From EmployeeBankDetail b Where b.employee=?1")
    void deleteBankDetailsByEmployee(Employee employee);

}
