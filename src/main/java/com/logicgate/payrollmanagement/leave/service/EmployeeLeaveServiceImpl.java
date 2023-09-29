package com.logicgate.payrollmanagement.leave.service;

import com.logicgate.payrollmanagement.employee.exception.EmployeeNotFoundException;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.employee.repository.EmployeeRepository;
import com.logicgate.payrollmanagement.leave.exception.LeaveNotFoundException;
import com.logicgate.payrollmanagement.leave.model.EmployeeLeave;
import com.logicgate.payrollmanagement.leave.repository.EmployeeLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;
import java.util.List;

@Service
@Transactional
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {
    @Autowired
    private EmployeeLeaveRepository employeeLeaveRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeLeave addLeave(EmployeeLeave leave) {
        String searchKey = "JWT.xxxx";
        Employee employee = employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(searchKey,
                        searchKey, searchKey, searchKey)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + searchKey + " not found"));
        leave.setEmployee(employee);
        leave.setNumberOfDays(Period.between(leave.getStartDate(), leave.getEndDate()).getDays());
        if (calculateEmployeeLeave(leave.getEmployee().getEmployeeId()) > leave.getEligibleLeaveDays()) {
            throw new LeaveNotFoundException("your Leave days exceeds approved annual leave days");
        }
        return employeeLeaveRepository.save(leave);
    }

    @Override
    public EmployeeLeave fetchEmployeeLeave(String employeeId) {
        Employee employee=employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId, employeeId,
                employeeId, employeeId)
                .orElseThrow(()->new EmployeeNotFoundException("Employ "+employeeId+" does not exist"));
        return employeeLeaveRepository.findLeaveByEmployee(employee);
    }

    @Override
    public List<EmployeeLeave> fetchAllLeave(int pageNumber, int pageSize) {
        return employeeLeaveRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public void rejectEmployeeLeave(String employeeId) {

    }

    private int calculateEmployeeLeave(String employeeId) {
        int total = 0;
        Employee employee = employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                        employeeId, employeeId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + employeeId + " not found"));
        EmployeeLeave employeeLeave = employeeLeaveRepository.findLeaveByEmployee(employee);

        if (employeeLeave.getEmployee().getEmployeeId() != null) {
            total += employeeLeave.getNumberOfDays();
        }
        return total;
    }
}
