package com.logicgate.payrollmanagement.address.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String houseNumber;
    private String streetName;
    private String city;
    private String landmark;
    private String stateName;
    private String countryName;
}
