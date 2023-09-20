package com.logicgate.payrollmanagement.department.service;

import com.logicgate.payrollmanagement.department.exception.DepartmentNotFoundException;
import com.logicgate.payrollmanagement.department.model.Department;
import com.logicgate.payrollmanagement.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(Department department) {
        Optional<Department> optionalDepartment = departmentRepository
                .findByCostCentreOrDepartmentName(department.getCostCentre(), department.getDepartmentName());
        if (optionalDepartment.isPresent()) {
            throw new DepartmentNotFoundException(department.getDepartmentName()+" Department or Cost Centre "+
                    department.getCostCentre()+" already exist");
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department fetchDepartmentByCostCentreOrName(String searchKey) {
        return departmentRepository.findByCostCentreOrDepartmentName(searchKey, searchKey)
                .orElseThrow(() -> new DepartmentNotFoundException("Department " + searchKey + " not found"));
    }

    @Override
    public List<Department> fetchAllDepartments(int pageNumber, int pageSize) {
        return departmentRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Department editDepartment(Department department, String key) {
        Department savedDepartment = departmentRepository.findByCostCentreOrDepartmentName(key, key)
                .orElseThrow(() -> new DepartmentNotFoundException("Department " + key + " not found"));
        if (Objects.nonNull(department.getCostCentre()) && !"".equalsIgnoreCase(department.getCostCentre())) {
            savedDepartment.setCostCentre(department.getCostCentre());
        }
        if (Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
            savedDepartment.setDepartmentName(department.getDepartmentName());
        }
        if (Objects.nonNull(department.getDepartmentLocation()) && !"".equalsIgnoreCase(department.getDepartmentLocation())) {
            savedDepartment.setDepartmentLocation(department.getDepartmentLocation());
        }
        return departmentRepository.save(savedDepartment);
    }

    @Override
    public void deleteDepartment(String searchKey) {
        Optional<Department> department = departmentRepository.findByCostCentreOrDepartmentName(searchKey, searchKey);
        if (department.isPresent()) {
            departmentRepository.deleteDepartmentByCostCentreOrDepartmentName(searchKey, searchKey);
        } else throw new DepartmentNotFoundException("Department " + searchKey + " not found");
    }

    @Override
    public void deleteAllDepartments() {
        departmentRepository.deleteAll();
    }
}
