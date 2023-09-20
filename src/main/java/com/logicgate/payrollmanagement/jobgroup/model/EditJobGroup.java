package com.logicgate.payrollmanagement.jobgroup.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EditJobGroup {
    private Long id;
    private String jobGroupCode;
    private String jobGroupDescription;
}
