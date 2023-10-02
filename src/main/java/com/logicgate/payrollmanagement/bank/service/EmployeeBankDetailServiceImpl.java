package com.logicgate.payrollmanagement.bank.service;

import com.logicgate.payrollmanagement.bank.exception.EmployeeBankDetailNotFoundException;
import com.logicgate.payrollmanagement.bank.model.EmployeeBankDetail;
import com.logicgate.payrollmanagement.bank.repository.EmployeeBankDetailRepository;
import com.logicgate.payrollmanagement.employee.exception.EmployeeNotFoundException;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class EmployeeBankDetailServiceImpl implements EmployeeBankDetailService {
    @Autowired
    private EmployeeBankDetailRepository employeeBankDetailRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeBankDetail addBankDetails(String employeeId, EmployeeBankDetail employeeBankDetail) {
        Employee employee=employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                employeeId,employeeId,employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee "+employeeId+" not found"));
        employeeBankDetail.setEmployee(employee);
        return employeeBankDetailRepository.save(employeeBankDetail);
    }

    @Override
    public EmployeeBankDetail fetchEmployeeBankDetail(String employeeId) {
        Employee employee=employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                        employeeId,employeeId,employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee "+employeeId+" not found"));
        return employeeBankDetailRepository.findByEmployee(employee);
    }

    @Override
    public List<EmployeeBankDetail> fetchEmployeeBankDetails(int pageNumber, int pageSize) {
        return employeeBankDetailRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public EmployeeBankDetail editBankDetail(String employeeId, EmployeeBankDetail bankDetail) {
        Employee employee=employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                        employeeId,employeeId,employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee "+employeeId+" not found"));
        EmployeeBankDetail savedBank=employeeBankDetailRepository.findByEmployee(employee);
        if (Objects.nonNull(bankDetail.getBankName()) && !"".equalsIgnoreCase(bankDetail.getBankName())){
            savedBank.setBankName(bankDetail.getBankName());
        }if (Objects.nonNull(bankDetail.getAccountNumber()) && !"".equalsIgnoreCase(bankDetail.getAccountNumber())){
            savedBank.setAccountNumber(bankDetail.getAccountNumber());
        }if (Objects.nonNull(bankDetail.getAccountType()) && !"".equals(bankDetail.getAccountType())){
            savedBank.setAccountType(bankDetail.getAccountType());
        }if (Objects.nonNull(bankDetail.getSortCode()) && !"".equalsIgnoreCase(bankDetail.getSortCode())){
            savedBank.setSortCode(bankDetail.getSortCode());
        }if (Objects.nonNull(bankDetail.getBankBranch()) && !"".equalsIgnoreCase(bankDetail.getBankBranch())){
            savedBank.setBankBranch(bankDetail.getBankBranch());
        }if (Objects.nonNull(bankDetail.getBankCode()) && !"".equalsIgnoreCase(bankDetail.getBankCode())){
            savedBank.setBankCode(bankDetail.getBankCode());
        }if (Objects.nonNull(bankDetail.getBankAddress()) && !"".equals(bankDetail.getBankAddress())){
            savedBank.setBankAddress(bankDetail.getBankAddress());
        }
        return employeeBankDetailRepository.save(savedBank);
    }

    @Override
    public void deleteBankByEmployee(String employeeId) {
        Employee employee=employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                        employeeId,employeeId,employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee "+employeeId+" not found"));
        EmployeeBankDetail employeeBankDetail=employeeBankDetailRepository.findByEmployee(employee);
        if (Objects.nonNull(employeeBankDetail)){
            employeeBankDetailRepository.deleteBankDetailsByEmployee(employee);
        }else throw new EmployeeBankDetailNotFoundException("Bank details not found");
    }
}
