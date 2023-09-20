package com.logicgate.payrollmanagement.department.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EditDepartment {
    private Long id;
    private String costCentre;
    private String departmentName;
    private String departmentLocation;
}
