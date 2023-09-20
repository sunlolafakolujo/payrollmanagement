package com.logicgate.payrollmanagement.pensionmanager.model;

import com.logicgate.payrollmanagement.address.model.Address;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PensionAdministrator {
    @Id
    @SequenceGenerator(name = "pensionAdministrator_generator",
            sequenceName = "pensionAdministrator_generator",allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pensionAdministrator_generator")
    private Long id;
    private String administratorName;
    private String contactPerson;
    private String email;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> address=new HashSet<>();
}
