package com.logicgate.payrollmanagement.employee.model;

import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.image.model.Picture;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.staticdata.EmployeeStatus;
import com.logicgate.payrollmanagement.staticdata.Gender;
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
    private String firstName;
    private String lastName;
    private String otherNames;
    private LocalDate dateOfBirth;
    private Integer age;
    private String stateOfOrigin;
    private Gender gender;
    private String email;
    private String mobile;
    private LocalDate hiredDate;
    private LocalDate retirementDate;
    private EmployeeStatus employeeStatus;
    private Set<Nationality> nationalities = new HashSet<>();
    private Address address;
    private String houseNumber;
    private String streetName;
    private String city;
    private String landmark;
    private String stateName;
    private String countryName;
    private JobGroup jobGroup;
    private Designation designation;
    private Picture picture;
    private Set<Role> roles = new HashSet<>();
}
