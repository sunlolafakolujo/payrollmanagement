package com.logicgate.payrollmanagement.jobgroup.model;

import com.logicgate.payrollmanagement.allowance.model.Allowance;
import com.logicgate.payrollmanagement.staticdata.AllowanceType;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobGroupDto {
    private Long id;
    private String jobGroupCode;
    private String jobGroupDescription;
    private Set<Allowance> allowances=new HashSet<>();
}
