package com.logicgate.payrollmanagement.department.repository;

import com.logicgate.payrollmanagement.department.model.Department;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DepartmentRepositoryCustom {
    @Query("From Department d Where d.costCentre=?1 Or d.departmentName=?2 ")
    Optional<Department> findByCostCentreOrDepartmentName(String key1, String key2);

    @Modifying
    @Query("Delete from Department d Where d.costCentre=?1 Or d.departmentName=?2")
    void deleteDepartmentByCostCentreOrDepartmentName(String key1, String key2);
}
