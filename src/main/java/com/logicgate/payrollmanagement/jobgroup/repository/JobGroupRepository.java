package com.logicgate.payrollmanagement.jobgroup.repository;

import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobGroupRepository extends JpaRepository<JobGroup, Long>, JobGroupRepositoryCustom {
}
