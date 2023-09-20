package com.logicgate.payrollmanagement.jobgroup.model;

import com.logicgate.payrollmanagement.allowance.model.Allowance;
import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobGroup extends BaseAudit {
    @Id
    @SequenceGenerator(name = "jobGroup_generator",
            sequenceName = "jobGroup_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "jobGroup_generator")
    private Long id;
    private String jobGroupCode;
    private String jobGroupDescription;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Allowance> allowances = new HashSet<>();
}
