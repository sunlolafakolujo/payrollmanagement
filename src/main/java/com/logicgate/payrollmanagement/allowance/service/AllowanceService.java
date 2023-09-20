package com.logicgate.payrollmanagement.allowance.service;

import com.logicgate.payrollmanagement.allowance.model.Allowance;

import java.util.List;

public interface AllowanceService {
    Allowance addAllowance(Allowance allowance);

    Allowance fetchAllowanceById(Long id);

    List<Allowance> fetchAllowances(int pageNumber, int pageSize);

    Allowance editAllowance(Allowance allowance, Long id);

    void deleteAllowance(Long id);

    void deleteAllowances();
}
