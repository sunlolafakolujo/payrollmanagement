package com.logicgate.payrollmanagement.jobgroup.controller;

import com.logicgate.payrollmanagement.allowance.model.Allowance;
import com.logicgate.payrollmanagement.jobgroup.model.EditJobGroup;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroupDto;
import com.logicgate.payrollmanagement.jobgroup.model.PostJoGroup;
import com.logicgate.payrollmanagement.jobgroup.service.JobGroupService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/payroll")
public record JobGroupController(JobGroupService jobGroupService, ModelMapper modelMapper) {

    @PostMapping("/addJobGroup")
    public ResponseEntity<PostJoGroup> addJobGroup(@RequestBody PostJoGroup postJoGroup) {
        JobGroup jobGroup = modelMapper.map(postJoGroup, JobGroup.class);
        JobGroup post = jobGroupService.addJobGroup(jobGroup);
        PostJoGroup posted = modelMapper.map(post, PostJoGroup.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/findJobGroup")
    public ResponseEntity<JobGroupDto> getJobGroup(@RequestParam("jobGroupCode") String jobGroupCode) {
        JobGroup jobGroup = jobGroupService.fetchJobGroupByCode(jobGroupCode);
        return new ResponseEntity<>(convertJobGroupToDto(jobGroup), HttpStatus.OK);
    }

    @GetMapping("/findAllJobGroup")
    public ResponseEntity<List<JobGroupDto>> getAllJobGroups(@RequestParam("pageNumber") int pageNumber,
                                                             @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(jobGroupService.fetAllJobGroups(pageNumber, pageSize)
                .stream().map(this::convertJobGroupToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editJobGroup")
    public ResponseEntity<EditJobGroup> editJobGroup(@RequestBody EditJobGroup editJobGroup,
                                                     @RequestParam("jobGroupCode") String jobGroupCode) {
        JobGroup jobGroup = modelMapper.map(editJobGroup, JobGroup.class);
        JobGroup post = jobGroupService.editJobGroup(jobGroup, jobGroupCode);
        EditJobGroup posted = modelMapper.map(post, EditJobGroup.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteJobGroup")
    public ResponseEntity<?> deleteJobGroup(@RequestParam("jobGroupCode") String jobGroupCode) {
        jobGroupService.deleteJobGroup(jobGroupCode);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllJobGroups")
    public ResponseEntity<?> deleteAllJobGroups() {
        jobGroupService.deleteAllJobGroups();
        return ResponseEntity.noContent().build();
    }


















    private JobGroupDto convertJobGroupToDto(JobGroup jobGroup) {
        JobGroupDto jobGroupDto = new JobGroupDto();
        jobGroupDto.setId(jobGroup.getId());
        jobGroupDto.setJobGroupCode(jobGroup.getJobGroupCode());
        jobGroupDto.setJobGroupDescription(jobGroup.getJobGroupDescription());
        jobGroupDto.setAllowances(jobGroup.getAllowances());
        return jobGroupDto;
    }
}
