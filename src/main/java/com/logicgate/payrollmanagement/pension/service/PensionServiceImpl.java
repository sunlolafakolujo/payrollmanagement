package com.logicgate.payrollmanagement.pension.service;

import com.logicgate.payrollmanagement.allowance.model.Allowance;
import com.logicgate.payrollmanagement.employee.exception.EmployeeNotFoundException;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.employee.repository.EmployeeRepository;
import com.logicgate.payrollmanagement.pension.model.Pension;
import com.logicgate.payrollmanagement.pension.repository.PensionRepository;
import com.logicgate.payrollmanagement.salary.model.Salary;
import com.logicgate.payrollmanagement.salary.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class PensionServiceImpl implements PensionService {
    @Autowired
    private PensionRepository pensionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public Pension addPension(String employeeId, Pension pension) {
        Employee employee=employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                        employeeId,employeeId,employeeId)
                .orElseThrow(()->new EmployeeNotFoundException("Employee "+employeeId+" not found"));
        pension.setEmployee(employee);
        pension.setEmployeeContribution(employeePensionContribution(pension.getEmployee().getEmployeeId()));
        pension.setEmployerContribution(employeerPensionContribution(pension.getEmployee().getEmployeeId()));
        pension.setAnnualPensionAmount(pension.getEmployeeContribution().multiply(pension.getEmployerContribution()));
        return pensionRepository.save(pension);
    }

    @Override
    public Pension fetchPensionById(String pensionId) {
        return null;
    }

    @Override
    public Pension fetchPensionByEmployeeId(String employeeId) {
        return null;
    }

    @Override
    public List<Pension> fetchAllPensions(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Pension editPension(Pension pension, String pensionId) {
        return null;
    }

    @Override
    public void deletePensionById(String pensionId) {

    }

    private BigDecimal employeePensionContribution(String employeeId){
        Employee employee=employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                employeeId,employeeId,employeeId)
                .orElseThrow(()->new EmployeeNotFoundException("Employee "+employeeId+" not found"));
        Salary salary=salaryRepository.findSalaryByEmployee(employee);
        BigDecimal employeeContribution=BigDecimal.ZERO;
        for (Allowance allowance: salary.getJobGroup().getAllowances()) {
            employeeContribution=((salary.getAnnulSalaryAmount().add(allowance.getHousingAllowance())
                    .add(allowance.getTransportationAllowance())).multiply(new BigDecimal(8)))
                    .divide(new BigDecimal(100));
        }
        return employeeContribution;
    }

    private BigDecimal employeerPensionContribution(String employeeId){
        Employee employee=employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                        employeeId,employeeId,employeeId)
                .orElseThrow(()->new EmployeeNotFoundException("Employee "+employeeId+" not found"));
        Salary salary=salaryRepository.findSalaryByEmployee(employee);
        BigDecimal employeerContribution=BigDecimal.ZERO;
        for (Allowance allowance: salary.getJobGroup().getAllowances()) {
            employeerContribution=((salary.getAnnulSalaryAmount().add(allowance.getHousingAllowance())
                    .add(allowance.getTransportationAllowance())).multiply(new BigDecimal(10)))
                    .divide(new BigDecimal(100));
        }
        return employeerContribution;
    }
}
