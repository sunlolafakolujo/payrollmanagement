package com.logicgate.payrollmanagement.employee.model;

import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.image.model.Picture;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.nextofkin.model.NextOfKin;
import com.logicgate.payrollmanagement.staticdata.EmployeeStatus;
import com.logicgate.payrollmanagement.staticdata.Gender;
import com.logicgate.payrollmanagement.staticdata.Relationship;
import com.logicgate.payrollmanagement.staticdata.Title;
import com.logicgate.payrollmanagement.userrole.model.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String employeeId;
    private Title title;
    private String firstName;
    private String lastName;
    private String otherNames;
    private LocalDate dateOfBirth;
    private Integer age;
    private Gender gender;
    private String email;
    private String mobile;
    private String nationalIdentificationNumber;
    private LocalDate nationalIdentificationDateIssued;
    private LocalDate hiredDate;
    private LocalDate retirementDate;
    private EmployeeStatus employeeStatus;
    private String stateOfOrigin;
    private Set<Nationality> nationalities = new HashSet<>();
    private String houseNumber;
    private String streetName;
    private String city;
    private String landmark;
    private String stateName;
    private String countryName;
    private String nextOfKinFirstName;
    private String nextOfKinLastName;
    private String nextOfKinEmail;
    private String nextOfKinMobile1;
    private String nextOfKinMobile2;
    private Relationship relationshipWithNextOfKin;
    private String nextOfKinHouseNumber;
    private String nextOfKinStreetName;
    private String nextOfKinCity;
    private String nextOfKinLandmark;
    private String nextOfKinStateName;
    private String nextOfKinCountryName;
    private Picture picture;
    private Set<Role> roles = new HashSet<>();
}
