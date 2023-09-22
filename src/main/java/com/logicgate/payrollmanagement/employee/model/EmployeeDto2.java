package com.logicgate.payrollmanagement.employee.model;

import com.logicgate.payrollmanagement.image.model.Picture;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto2 {
    private String firstName;
    private String lastName;
    private String otherNames;
    private String email;
    private String mobile;
    private Picture picture;
}
