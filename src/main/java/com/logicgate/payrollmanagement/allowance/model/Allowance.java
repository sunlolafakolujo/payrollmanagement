package com.logicgate.payrollmanagement.allowance.model;


import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.staticdata.AllowanceType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Allowance extends BaseAudit {
    @Id
    @SequenceGenerator(name = "allowance_generator",
            sequenceName = "allowance_seq",allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "allowance_generator")
    private Long id;

    @Enumerated(EnumType.STRING)
    private AllowanceType allowanceType;
    private BigDecimal allowanceAmount;
}
