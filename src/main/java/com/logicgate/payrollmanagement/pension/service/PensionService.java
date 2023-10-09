package com.logicgate.payrollmanagement.pension.service;

import com.logicgate.payrollmanagement.pension.model.Pension;

import java.util.List;

public interface PensionService {
    Pension addPension(String employeeId, Pension pension);

    Pension fetchPensionById(String pensionId);

    Pension fetchPensionByEmployeeId(String employeeId);

    List<Pension> fetchAllPensions(int pageNumber, int pageSize);

    Pension editPension(Pension pension, String pensionId);

    void deletePensionById(String pensionId);
}
