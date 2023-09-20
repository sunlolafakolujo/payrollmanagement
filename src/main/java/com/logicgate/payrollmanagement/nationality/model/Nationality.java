package com.logicgate.payrollmanagement.nationality.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Nationality {
    @Id
    @SequenceGenerator(name = "nationality_generator",
            sequenceName = "nationality_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "nationality_generator")
    private Long id;
    private String nationality;
}
