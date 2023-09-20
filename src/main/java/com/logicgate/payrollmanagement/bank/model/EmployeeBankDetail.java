package com.logicgate.payrollmanagement.bank.model;

import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.staticdata.AccountType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBankDetail extends BaseAudit {
    @Id
    @SequenceGenerator(name = "bank_generator",
            sequenceName = "bank_sequence", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "bank_generator")
    private Long id;
    private String bankCode;
    private String bankName;
    private String bankBranch;
    private String sortCode;
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> bankAddress = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;
}
