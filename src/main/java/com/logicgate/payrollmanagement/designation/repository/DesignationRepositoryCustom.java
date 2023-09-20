package com.logicgate.payrollmanagement.designation.repository;

import com.logicgate.payrollmanagement.designation.model.Designation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DesignationRepositoryCustom {
    @Query("From Designation d Where d.designationTitle=?1")
    Optional<Designation> findByDesignationTitle(String designationTitle);

    @Modifying
    @Query("Delete From Designation d Where d.designationTitle=?1")
    void deleteByDesignationCodeOrTitle(String designationTitle);
}
