package com.logicgate.payrollmanagement.employeedesignation.model;

import com.logicgate.payrollmanagement.designation.model.Designation;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditEmployeeDesignation {
    private Long id;
    private Designation designation;
}
