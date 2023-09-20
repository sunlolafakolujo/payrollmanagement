package com.logicgate.payrollmanagement.address.model;

import com.logicgate.payrollmanagement.country.model.Country;
import com.logicgate.payrollmanagement.state.model.Province;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostAddress {

    private String houseNumber;
    private String streetName;
    private String city;
    private String landmark;
    private Province province;
    private Country country;
}
