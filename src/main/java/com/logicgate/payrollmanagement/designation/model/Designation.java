package com.logicgate.payrollmanagement.designation.model;

import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.department.model.Department;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Designation extends BaseAudit {
    @Id
    @SequenceGenerator(name = "designation_generator",
            sequenceName = "designation_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "designation_generator")
    private Long id;
    private String designationTitle;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Department department;
}
