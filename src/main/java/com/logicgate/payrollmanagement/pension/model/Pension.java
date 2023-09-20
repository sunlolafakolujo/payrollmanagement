package com.logicgate.payrollmanagement.pension.model;

import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.pensionmanager.model.PensionAdministrator;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pension extends BaseAudit {
    @Id
    @SequenceGenerator(name="sequence_generator",
            sequenceName = "pension_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "pension_generator")
    private Long id;
    private String pensionId;
    private BigDecimal pensionAmount;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Employee employee;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private PensionAdministrator pensionAdministrator;
}
