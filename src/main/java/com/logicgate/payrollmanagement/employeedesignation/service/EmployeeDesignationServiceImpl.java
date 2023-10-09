package com.logicgate.payrollmanagement.employeedesignation.service;

import com.logicgate.payrollmanagement.designation.exception.DesignationNotFoundException;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.designation.repository.DesignationRepository;
import com.logicgate.payrollmanagement.employee.exception.EmployeeNotFoundException;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.employee.repository.EmployeeRepository;
import com.logicgate.payrollmanagement.employeedesignation.exception.EmployeeDesignationNofFoundException;
import com.logicgate.payrollmanagement.employeedesignation.model.EmployeeDesignation;
import com.logicgate.payrollmanagement.employeedesignation.repository.EmployeeDesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class EmployeeDesignationServiceImpl implements EmployeeDesignationService {
    @Autowired
    private EmployeeDesignationRepository employeeDesignationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DesignationRepository designationRepository;

    @Override
    public void addEmployeeDesignation(String employeeId, String designationTitle) {
        Employee employee = employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                employeeId, employeeId, employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee " + employeeId + " not found"));
        Designation designation = designationRepository.findByDesignationTitle(designationTitle)
                .orElseThrow(() -> new DesignationNotFoundException("Designation " + designationTitle + " not found"));
        EmployeeDesignation employeeDesignation = new EmployeeDesignation(employee, designation);
        employeeDesignationRepository.save(employeeDesignation);
    }

    @Override
    public List<EmployeeDesignation> fetchEmployeesDesignation(int pageNumber, int pageSize) {
        return employeeDesignationRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public EmployeeDesignation editEmployeeDesignation(EmployeeDesignation employeeDesignation, Long id) {
        EmployeeDesignation savedEmployeeDesignation = employeeDesignationRepository.findById(id)
                .orElseThrow(() -> new EmployeeDesignationNofFoundException("EmployeeDesignation " + id + " not found"));
        if (Objects.nonNull(employeeDesignation.getDesignation()) && !"".equals(employeeDesignation.getDesignation())) {
            savedEmployeeDesignation.setDesignation(employeeDesignation.getDesignation());
        }
        return employeeDesignationRepository.save(savedEmployeeDesignation);
    }

    @Override
    public void deleteEmployeeDesignation(Long id) {
        if (employeeDesignationRepository.existsById(id)) {
            employeeDesignationRepository.deleteById(id);
        } else throw new EmployeeDesignationNofFoundException("EmployeeDesignation " + id + " does not exist");
    }
}
