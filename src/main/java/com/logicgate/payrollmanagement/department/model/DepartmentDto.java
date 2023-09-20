package com.logicgate.payrollmanagement.department.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String costCentre;
    private String departmentName;
    private String departmentLocation;
}
