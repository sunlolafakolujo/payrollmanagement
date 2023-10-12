package com.logicgate.payrollmanagement.overtime.model;

import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.image.model.Picture;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Overtime extends BaseAudit {
    @Id
    @SequenceGenerator(name = "overtime_generator",
            sequenceName = "overtime_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "overtime_generator")
    private Long id;
    private String overtimeId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Picture emailApproval;
    private Integer numberOfHourWorked;
    private static final BigDecimal ratePerHour = new BigDecimal(20000);
//    private static final maximumOvertime=

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;

//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private Employee supervisorApproval;

}
