package com.logicgate.payrollmanagement.bank.model;

import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.staticdata.AccountType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostEmployeeBankDetail {
    private String bankCode;
    private String bankName;
    private String bankBranch;
    private String sortCode;
    private String accountNumber;
    private AccountType accountType;
    private Set<Address> bankAddress = new HashSet<>();
    private Employee employee;
}
