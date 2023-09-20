package com.logicgate.payrollmanagement.department.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @SequenceGenerator(name="department_generator",sequenceName = "dept_seq",allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_generator")
    private Long id;
    private String costCentre;
    private String departmentName;
    private String departmentLocation;
}
