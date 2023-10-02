package com.logicgate.payrollmanagement.employeedesignation.model;

import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.employee.model.Employee;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostEmployeeDesignation {
    private Employee employee;
    private Designation designation;
}
