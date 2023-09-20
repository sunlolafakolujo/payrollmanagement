package com.logicgate.payrollmanagement.designation.repository;

import com.logicgate.payrollmanagement.designation.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository extends JpaRepository<Designation,Long>,DesignationRepositoryCustom {

}
