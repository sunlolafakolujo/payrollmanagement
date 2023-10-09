package com.logicgate.payrollmanagement.pensionmanager.service;

import com.logicgate.payrollmanagement.pensionmanager.model.PensionAdministrator;

import java.util.List;

public interface PensionAdministratorService {
    PensionAdministrator addPensionAdministrator(PensionAdministrator pensionAdministrator);
    PensionAdministrator fetchPensionAdministratorByNameOrCode(String searchKey);
    List<PensionAdministrator> fetchAllPensionAdministrators(int pageNumber, int pageSize);
    PensionAdministrator editPensionAdministrator(PensionAdministrator pensionAdministrator, String searchKey);
    void  deletePensionAdministrator(String searchKey);
}
