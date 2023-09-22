package com.logicgate.payrollmanagement.userrole.model;

import com.logicgate.payrollmanagement.employee.model.Employee;
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
@Table(name = "roles")
public class Role {
    @Id
    @SequenceGenerator(name = "role_generator",
            sequenceName = "role_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "role_generator")
    private Long id;
    private String roleName;
    private String roleDescription;

    @ManyToMany(mappedBy = "roles")
    private Set<Employee> employees = new HashSet<>();
}
