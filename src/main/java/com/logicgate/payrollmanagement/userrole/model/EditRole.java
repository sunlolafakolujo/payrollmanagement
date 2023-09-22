package com.logicgate.payrollmanagement.userrole.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EditRole {
    private Long id;
    private String roleName;
    private String roleDescription;
}
