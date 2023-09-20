package com.logicgate.payrollmanagement.state.model;

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
@Table(name = "states")
public class Province extends BaseAudit {
    @Id
    @SequenceGenerator(name = "state_generator",
            sequenceName = "state_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "state_generator")
    private Long id;
    private String stateName;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "province")
    private List<Address> address = new ArrayList<>();
}
