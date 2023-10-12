package com.logicgate.payrollmanagement.overtime.repository;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.overtime.model.Overtime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface OvertimeRepositoryCustom {
    @Query("From Overtime o Where o.overtimeId=?1")
    Optional<Overtime> findOvertimeById(String overtimeId);

    @Query("From Overtime o Where o.employee=?1")
    List<Overtime> findEmployeeOvertime(Employee employee);

    @Modifying
    @Query("Delete From Overtime o Where o.overtimeId=?1")
    void deleteOverTimeById(String overtimeId);
}
