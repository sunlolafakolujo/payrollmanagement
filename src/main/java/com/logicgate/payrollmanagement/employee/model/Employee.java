package com.logicgate.payrollmanagement.employee.model;

import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.image.model.Picture;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.state.model.Province;
import com.logicgate.payrollmanagement.staticdata.EmployeeStatus;
import com.logicgate.payrollmanagement.staticdata.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseAudit {
    @Id
    @SequenceGenerator(name = "employee_generator",
            sequenceName = "employee_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employee_generator")
    private Long id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String otherNames;
    private String username;
    private String password;

    @Transient
    private String confirmPassword;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate hiredDate;
    private LocalDate retirementDate;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private JobGroup jobGroup;
    private String stateOfOrigin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Designation designation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Nationality> nationalities = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Picture picture;
}
