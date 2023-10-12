package com.logicgate.payrollmanagement.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.image.model.Picture;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.nextofkin.model.NextOfKin;
import com.logicgate.payrollmanagement.staticdata.EmployeeStatus;
import com.logicgate.payrollmanagement.staticdata.Gender;
import com.logicgate.payrollmanagement.staticdata.Relationship;
import com.logicgate.payrollmanagement.staticdata.Title;
import com.logicgate.payrollmanagement.userrole.model.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
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

    @Enumerated(EnumType.STRING)
    private Title title;

    @Pattern(regexp="[A-Za-z\\s]+", message="First Name should contains alphabets only")
    private String firstName;

    @Pattern(regexp="[A-Za-z\\s]+", message="Last Name should contains alphabets only")
    private String lastName;

    @Pattern(regexp="[A-Za-z\\s]+", message="Other Names should contains alphabets only")
    private String otherNames;
    private LocalDate dateOfBirth;
    private Integer age;
    private String stateOfOrigin;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String username;
    @Pattern(regexp="[A-Za-z0-9!@#$%^&*_]{8,15}", message="Please Enter a valid Password")
    private String password;

    @Transient
    private String confirmPassword;

    @Email
    private String email;
    private String mobile;
    @Column(nullable = false)
    private String nationalIdentificationNumber;
    private LocalDate nationalIdentificationDateIssued;
    private LocalDate hiredDate;
    private LocalDate retirementDate;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Set<Nationality> nationalities = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "picture_id", nullable = false)
    private Picture picture;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "next_Of_Kin_id", nullable = false)
    private NextOfKin nextOfKin;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();
}
