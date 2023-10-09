package com.logicgate.payrollmanagement.allowance.model;

import com.logicgate.payrollmanagement.staticdata.AllowanceType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EditAllowance {
    private Long id;
    //    private AllowanceType allowanceType;
    private BigDecimal housingAllowanceAmount;
    private BigDecimal transportationAllowanceAmount;
    private BigDecimal medicalAllowance;
    private BigDecimal lunchAllowance;
}
