package com.logicgate.payrollmanagement.pensionmanager.model;

import com.logicgate.payrollmanagement.address.model.Address;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PensionAdministratorDto {
    private String pensionAdministratorCode;
    private String administratorName;
    private String contactPerson;
    private String email;
    private String phone;
    private String officeNumber;
    private String officeStreetName;
    private String officeCity;
    private String officeLandmark;
    private String stateName;
    private String countryName;
}
