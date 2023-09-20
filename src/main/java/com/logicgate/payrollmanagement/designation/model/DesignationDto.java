package com.logicgate.payrollmanagement.designation.model;

import com.logicgate.payrollmanagement.department.model.Department;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DesignationDto {
    private Long id;
    private String designationTitle;
    private String costCentre;
}
