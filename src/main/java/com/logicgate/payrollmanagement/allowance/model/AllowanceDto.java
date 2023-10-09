package com.logicgate.payrollmanagement.allowance.model;

import com.logicgate.payrollmanagement.staticdata.AllowanceType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AllowanceDto {
    private Long id;
    //    private AllowanceType allowanceType;
    private BigDecimal housingAllowance;
    private BigDecimal transportationAllowance;
    private BigDecimal medicalAllowance;
    private BigDecimal lunchAllowance;
}
