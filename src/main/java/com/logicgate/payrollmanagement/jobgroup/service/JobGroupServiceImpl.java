package com.logicgate.payrollmanagement.jobgroup.service;

import com.logicgate.payrollmanagement.jobgroup.exception.JobGroupNotFoundException;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.jobgroup.repository.JobGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class JobGroupServiceImpl implements JobGroupService {
    @Autowired
    private JobGroupRepository jobGroupRepository;

    @Override
    public JobGroup addJobGroup(JobGroup jobGroup) {
        Optional<JobGroup> optionalJobGroup = jobGroupRepository.findJobGroupByCode(jobGroup.getJobGroupCode());
        if (optionalJobGroup.isPresent()) {
            throw new JobGroupNotFoundException("JobGroup " + jobGroup.getJobGroupCode() + " already exists");
        }
        return jobGroupRepository.save(jobGroup);
    }

    @Override
    public JobGroup fetchJobGroupByCode(String jobGroupCode) {
        return jobGroupRepository.findJobGroupByCode(jobGroupCode)
                .orElseThrow(() -> new JobGroupNotFoundException("JobGroup " + jobGroupCode + " not found"));
    }

    @Override
    public List<JobGroup> fetAllJobGroups(int pageNumber, int pageSize) {
        return jobGroupRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public JobGroup editJobGroup(JobGroup jobGroup, String jobGroupCode) {
        JobGroup savedJobGroup = jobGroupRepository.findJobGroupByCode(jobGroupCode)
                .orElseThrow(() -> new JobGroupNotFoundException("JobGroup " + jobGroupCode + " not found"));
        if (Objects.nonNull(jobGroup.getJobGroupCode()) && !"".equalsIgnoreCase(jobGroup.getJobGroupCode())) {
            savedJobGroup.setJobGroupCode(jobGroup.getJobGroupCode());
        }
        if (Objects.nonNull(jobGroup.getJobGroupDescription()) && !"".equalsIgnoreCase(jobGroup.getJobGroupDescription())) {
            savedJobGroup.setJobGroupDescription(jobGroup.getJobGroupDescription());
        }
        return jobGroupRepository.save(savedJobGroup);
    }

    @Override
    public void deleteJobGroup(String jobGroupCode) {
        Optional<JobGroup> savedJobGroup = jobGroupRepository.findJobGroupByCode(jobGroupCode);
        if (savedJobGroup.isPresent()) {
            jobGroupRepository.deleteJobGroupByCode(jobGroupCode);
        } else throw new JobGroupNotFoundException("JobGroup " + jobGroupCode + " not found");

    }

    @Override
    public void deleteAllJobGroups() {
        jobGroupRepository.deleteAll();
    }
}
