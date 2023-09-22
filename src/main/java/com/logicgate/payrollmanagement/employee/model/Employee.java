package com.logicgate.payrollmanagement.employee.model;

import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.image.model.Picture;
import com.logicgate.payrollmanagement.jobgroup.model.JobGroup;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.state.model.Province;
import com.logicgate.payrollmanagement.staticdata.EmployeeStatus;
import com.logicgate.payrollmanagement.staticdata.Gender;
import com.logicgate.payrollmanagement.userrole.model.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private LocalDate dateOfBirth;
    private Integer age;
    private String stateOfOrigin;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String username;
    private String password;

    @Transient
    @NotNull(message = "Confirm Password")
    private String confirmPassword;
    private String email;
    private String mobile;
    private LocalDate hiredDate;
    private LocalDate retirementDate;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Nationality> nationalities = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private JobGroup jobGroup;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Designation designation;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Picture picture;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

}
