package com.logicgate.payrollmanagement.designation.model;

import com.logicgate.payrollmanagement.department.model.Department;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EditDesignation {
    private Long id;
    private String designationTitle;
    private Department department;
}
