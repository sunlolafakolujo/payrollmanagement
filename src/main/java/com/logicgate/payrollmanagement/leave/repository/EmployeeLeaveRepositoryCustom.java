package com.logicgate.payrollmanagement.leave.repository;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.leave.model.EmployeeLeave;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeLeaveRepositoryCustom {
    @Query("From EmployeeLeave l Where l.employee=?1")
    EmployeeLeave findLeaveByEmployee(Employee employee);

    @Modifying
    @Query("Delete From EmployeeLeave l Where l.employee=?1")
    void rejectLeaveByEmployee(Employee employee);
}
