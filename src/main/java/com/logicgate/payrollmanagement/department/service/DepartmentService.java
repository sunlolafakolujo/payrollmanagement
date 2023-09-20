package com.logicgate.payrollmanagement.department.service;

import com.logicgate.payrollmanagement.department.model.Department;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department department);

    Department fetchDepartmentByCostCentreOrName(String searchKey);

    List<Department> fetchAllDepartments(int pageNumber, int pageSize);

    Department editDepartment(Department department, String key);

    void deleteDepartment(String searchKey);

    void deleteAllDepartments();
}
