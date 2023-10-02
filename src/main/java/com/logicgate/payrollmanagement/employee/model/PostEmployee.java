package com.logicgate.payrollmanagement.employee.model;

import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.image.model.Picture;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.nextofkin.model.NextOfKin;
import com.logicgate.payrollmanagement.staticdata.EmployeeStatus;
import com.logicgate.payrollmanagement.staticdata.Gender;
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
public class PostEmployee {
    private String employeeId;
    private Title title;
    private String firstName;
    private String lastName;
    private String otherNames;
    private LocalDate dateOfBirth;
    private String stateOfOrigin;
    private Gender gender;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String mobile;
    private String nationalIdentificationNumber;
    private LocalDate nationalIdentificationDateIssued;
    private LocalDate hiredDate;
    private LocalDate retirementDate;
    private EmployeeStatus employeeStatus;
    private Set<Nationality> nationalities = new HashSet<>();
    private Address address;
    private Picture picture;
    private NextOfKin nextOfKin;
    private Set<Role> roles = new HashSet<>();

}
