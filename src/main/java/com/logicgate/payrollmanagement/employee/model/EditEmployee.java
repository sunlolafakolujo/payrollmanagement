package com.logicgate.payrollmanagement.employee.model;

import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.image.model.Picture;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.nextofkin.model.NextOfKin;
import com.logicgate.payrollmanagement.staticdata.EmployeeStatus;
import com.logicgate.payrollmanagement.staticdata.Relationship;
import com.logicgate.payrollmanagement.userrole.model.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EditEmployee {
    private Long id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String otherNames;
    private String stateOfOrigin;
    private String email;
    private String mobile;
    private EmployeeStatus employeeStatus;
    private Set<Nationality> nationalities = new HashSet<>();
    private Address address;
    private Designation designation;
    private Picture picture;
    private NextOfKin nextOfKin;
    private Relationship relationshipWithNextOfKin;
//    private Set<Role> roles = new HashSet<>();
}
