package com.logicgate.payrollmanagement.allowance.model;

import com.logicgate.payrollmanagement.staticdata.AllowanceType;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostAllowance {
    private BigDecimal housingAllowanceAmount;
    private BigDecimal transportationAllowanceAmount;
}
