package com.logicgate.payrollmanagement.jobgroup.repository;

import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JobGroupRepositoryCustom {
    @Query("From JobGroup j Where j.jobGroupCode=?1")
    Optional<JobGroup> findJobGroupByCode(String jobGroupCode);

    @Modifying
    @Query("Delete From JobGroup j WHERE j.jobGroupCode=?1")
    void deleteJobGroupByCode(String jobGroupCode);
}
