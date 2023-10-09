package com.logicgate.payrollmanagement.pensionmanager.model;

import com.logicgate.payrollmanagement.address.model.Address;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditPensionAdministrator {
    private Long id;
    private String pensionAdministratorCode;
    private String administratorName;
    private String contactPerson;
    private String email;
    private String phone;
    private Address address;
}
