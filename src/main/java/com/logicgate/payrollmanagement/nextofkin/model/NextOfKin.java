package com.logicgate.payrollmanagement.nextofkin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.staticdata.Relationship;
import com.logicgate.payrollmanagement.staticdata.Title;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NextOfKin {
    @Id
    @SequenceGenerator(name = "nextOfKin_generator",
            sequenceName = "nextOfKin_sequence", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "nextOfKin_generator")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Title title;
    private String firstName;
    private String lastName;

    @Email
    private String email;
    private String mobile1;
    private String mobile2;

    @Enumerated(EnumType.STRING)
    private Relationship relationshipWithNextOfKin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "nextOfKin")
    private Employee employee;

}
