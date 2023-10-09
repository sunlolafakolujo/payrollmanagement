package com.logicgate.payrollmanagement.pension.model;

import com.logicgate.payrollmanagement.pensionmanager.model.PensionAdministrator;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditPension {
    private Long id;
    private String employeePersonalEmail;
    private PensionAdministrator pensionAdministrator;
}
