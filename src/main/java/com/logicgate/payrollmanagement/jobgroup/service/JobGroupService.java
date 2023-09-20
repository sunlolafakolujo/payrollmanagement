package com.logicgate.payrollmanagement.jobgroup.service;

import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;

import java.util.List;

public interface JobGroupService {
    JobGroup addJobGroup(JobGroup jobGroup);
    JobGroup fetchJobGroupByCode(String jobGroupCode);
    List<JobGroup> fetAllJobGroups(int pageNumber, int pageSize);
    JobGroup editJobGroup(JobGroup jobGroup, String jobGroupCode);
    void deleteJobGroup(String jobGroupCode);
    void deleteAllJobGroups();
}
