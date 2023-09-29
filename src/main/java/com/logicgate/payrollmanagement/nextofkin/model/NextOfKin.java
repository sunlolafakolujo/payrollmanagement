package com.logicgate.payrollmanagement.nextofkin.model;

import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.staticdata.Relationship;
import lombok.*;

import javax.persistence.*;

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
    private String firstName;
    private String lastName;
    private String email;
    private String mobile1;
    private String mobile2;
    @Enumerated(EnumType.STRING)
    private Relationship relationship;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;
}
