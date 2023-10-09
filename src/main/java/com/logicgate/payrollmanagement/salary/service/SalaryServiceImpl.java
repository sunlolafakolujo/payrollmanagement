package com.logicgate.payrollmanagement.salary.service;

import com.logicgate.payrollmanagement.employee.exception.EmployeeNotFoundException;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.employee.repository.EmployeeRepository;
import com.logicgate.payrollmanagement.salary.exception.SalaryNotFoundException;
import com.logicgate.payrollmanagement.salary.model.Salary;
import com.logicgate.payrollmanagement.salary.repository.SalaryRepository;
import com.logicgate.payrollmanagement.staticdata.SalaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Salary addMonthlySalaryEarner(String employeeId, Salary salary) {
        Employee employee = employeeRepository
                .findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId, employeeId, employeeId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID " + employeeId + " does not exist"));
        salary.setEmployee(employee);
        salary.setSalaryType(SalaryType.MONTHLY);
        salary.setAnnulSalaryAmount(totalAnnualSalary(salary.getEmployee().getEmployeeId()));
        return salaryRepository.save(salary);
    }

    @Override
    public Salary addDayRateSalaryEarner(String employeeId, Salary salary) {
        Employee employee = employeeRepository
                .findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId, employeeId, employeeId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID " + employeeId + " does not exist"));
        salary.setEmployee(employee);
        salary.setSalaryType(SalaryType.DAY_RATE);
        salary.setMonthlySalaryAmount(monthlyDayRateSalary(salary.getEmployee().getEmployeeId()));
        salary.setAnnulSalaryAmount(totalDayRateAnnualSalary(salary.getEmployee().getEmployeeId()));
        return salaryRepository.save(salary);
    }

    @Override
    public Salary fetchSalaryById(Long id) {
        return salaryRepository.findById(id).orElseThrow(() -> new SalaryNotFoundException("Salary ID " + id + " not found "));
    }

    @Override
    public Salary fetchEmployeeSalary(String employeeId) {
        Employee employee = employeeRepository
                .findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId, employeeId, employeeId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID " + employeeId + " not found"));
        return salaryRepository.findSalaryByEmployee(employee);
    }

    @Override
    public List<Salary> fetchEmployeesWithinSalaryRange(BigDecimal searchKey1, BigDecimal searchKey2, int pageNumber, int pageSize) {
        return salaryRepository.findEmployeeSalaryWithARange(searchKey1, searchKey2, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public List<Salary> fetchAllSalaries(int pageNumber, int pageSize) {
        return salaryRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Salary editSalary(Salary salary, String employeeId) {
        Employee employee = employeeRepository
                .findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId, employeeId, employeeId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + employeeId + " not found"));
        Salary savedSalary = salaryRepository.findSalaryByEmployee(employee);
        if (Objects.nonNull(salary.getMonthlySalaryAmount()) && !"".equals(salary.getMonthlySalaryAmount())) {
            savedSalary.setMonthlySalaryAmount(salary.getMonthlySalaryAmount());
        }
        if (Objects.nonNull(salary.getDayRateAmount()) && !"".equals(salary.getDayRateAmount())) {
            savedSalary.setDayRateAmount(salary.getDayRateAmount());
        }
        return salaryRepository.save(savedSalary);
    }

    @Override
    public void deleteSalaryByEmployeeId(String employeeId) {
        Employee employee = employeeRepository
                .findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId, employeeId, employeeId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + employeeId + " not found"));
        Salary optionalSalary = salaryRepository.findSalaryByEmployee(employee);
        if (Objects.nonNull(optionalSalary)) {
            deleteSalaryByEmployeeId(employeeId);
        } else throw new SalaryNotFoundException("Salary not found");
    }

    @Override
    public void deleteAllSalaries() {
        salaryRepository.deleteAll();
    }

    private BigDecimal totalAnnualSalary(String employeeId) {
        Employee employee = employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                        employeeId, employeeId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + employeeId + " not found"));
        Salary salary = salaryRepository.findSalaryByEmployee(employee);
        return salary.getMonthlySalaryAmount().multiply(new BigDecimal(12));
    }

    private BigDecimal totalDayRateAnnualSalary(String employeeId) {
        Employee employee = employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                        employeeId, employeeId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + employeeId + " not found"));
        Salary salary = salaryRepository.findSalaryByEmployee(employee);
        return salary.getMonthlySalaryAmount().multiply(new BigDecimal(12));
    }

    private BigDecimal monthlyDayRateSalary(String employeeId) {
        Employee employee = employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                        employeeId, employeeId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + employeeId + " not found"));
        Salary salary = salaryRepository.findSalaryByEmployee(employee);
        return salary.getDayRateAmount().multiply(new BigDecimal(salary.getNumberOfDaysWorkedPerMonth()));
    }
}
