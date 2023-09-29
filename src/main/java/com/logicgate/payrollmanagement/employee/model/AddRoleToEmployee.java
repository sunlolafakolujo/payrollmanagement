package com.logicgate.payrollmanagement.employee.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleToEmployee {
    private String usernameOrEmailOrMobileOrEmployeeId;
    private String roleName;
}
