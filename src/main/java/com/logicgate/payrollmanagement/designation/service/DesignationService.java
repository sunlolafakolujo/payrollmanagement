package com.logicgate.payrollmanagement.designation.service;

import com.logicgate.payrollmanagement.designation.model.Designation;

import java.util.List;

public interface DesignationService {
    Designation addDesignation(Designation designation);

    Designation fetchDesignationById(Long id);

    Designation fetchByDesignationTitle(String designationTitle);

    List<Designation> fetchAllDesignation(int pageNumber, int pageSize);

    Designation editDesignation(Designation designation, Long id);

    void deleteDesignation(String designationTitle);

    void deleteAllDesignations();
}
