package com.logicgate.payrollmanagement.image.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logicgate.payrollmanagement.employee.model.Employee;
import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    public Picture(String imageName, String imageType, byte[] picByte) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.picByte = picByte;
    }

    @Id
    @SequenceGenerator(name = "picture_generator",
            sequenceName = "picture_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "picture_generator")
    private Long id;
    private String imageName;
    private String imageType;

    @Column(length = 10000)
    private byte[] picByte;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "picture")
    private Employee employee;
}
