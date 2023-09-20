package com.logicgate.payrollmanagement.jobgroup.model;

import com.logicgate.payrollmanagement.allowance.model.Allowance;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostJoGroup {
    private String jobGroupCode;
    private String jobGroupDescription;
    private Set<Allowance> allowances=new HashSet<>();
}
