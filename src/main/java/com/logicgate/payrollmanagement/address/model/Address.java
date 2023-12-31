package com.logicgate.payrollmanagement.address.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.country.model.Country;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.nextofkin.model.NextOfKin;
import com.logicgate.payrollmanagement.state.model.Province;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseAudit {
    @Id
    @SequenceGenerator(name = "address_generator",
            sequenceName = "address_sequence", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "address_generator")
    private Long id;
    private String houseNumber;
    private String streetName;
    private String city;
    private String landmark;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    Province province;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    Country country;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "address")
    private Employee employee;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "address")
    private NextOfKin nextOfKin;
}
