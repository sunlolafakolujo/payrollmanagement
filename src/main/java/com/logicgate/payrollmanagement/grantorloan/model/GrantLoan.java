package com.logicgate.payrollmanagement.grantorloan.model;

import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.staticdata.ApprovalStatus;
import com.logicgate.payrollmanagement.staticdata.GrantOrLoan;
import com.logicgate.payrollmanagement.staticdata.GrantOrLoanType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GrantLoan extends BaseAudit {
    @Id
    @SequenceGenerator(name="grantLoan_generator",
            sequenceName = "grantLoan_sequence",allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "grantLoan_generator")
    private Long id;
    /**
     * if(grantLoan.getGrantOrLoan==GrantOrLoan.Automobile &&
     * grantLoan.getGrantOrLoanType==GrantOrLoanType.Loan){
     *     grantLoan.setLoanOrGrantAmount(200000000);
     * }.
     */
    @Enumerated(EnumType.STRING)
    private GrantOrLoan grantOrLoan;

    @Enumerated(EnumType.STRING)
    private GrantOrLoanType grantOrLoanType;
    private BigDecimal loanOrGrantAmount;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus=ApprovalStatus.PENDING;
    private Date approvalDate;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Employee employee;

//    private String supervisorApproval;
}
