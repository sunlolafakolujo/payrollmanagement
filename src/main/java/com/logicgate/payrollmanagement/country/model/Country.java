package com.logicgate.payrollmanagement.country.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseAudit {
    @Id
    @SequenceGenerator(name = "country",
            sequenceName = "country_generator",
            allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "country_seq")
    private Long id;
    private String countryName;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "country")
    private List<Address> address = new ArrayList<>();
}
