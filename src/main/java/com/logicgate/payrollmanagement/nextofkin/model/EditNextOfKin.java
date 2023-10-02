package com.logicgate.payrollmanagement.nextofkin.model;

import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.staticdata.Relationship;
import com.logicgate.payrollmanagement.staticdata.Title;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditNextOfKin {
    private Long id;
    private Title title;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile1;
    private String mobile2;
    private Relationship relationship;
    private Address address;
}
