package com.logicgate.payrollmanagement.employee.service;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.employee.repository.EmployeeRepository;
import com.logicgate.payrollmanagement.employee.exception.EmployeeNotFoundException;
import com.logicgate.payrollmanagement.userrole.exception.RoleNotFoundException;
import com.logicgate.payrollmanagement.userrole.model.Role;
import com.logicgate.payrollmanagement.userrole.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public Employee addEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(
                employee.getUsername(), employee.getEmail(), employee.getMobile(), employee.getEmployeeId());
        if (optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Employee " + employee.getEmail() + " or" + employee.getUsername()
                    + " or" + employee.getMobile() + " already exists");
        }
        if (!employee.getPassword().equals(employee.getConfirmPassword())) {
            throw new EmployeeNotFoundException("Password does not match");
        }
        if (!validatePhoneNumber(employee)) {
            throw new EmployeeNotFoundException("Mobile phone must be in one of these formats: " +
                    "10 or 11 digit, 0000 000 0000, 000 000 0000, 000-000-0000, 000-000-0000 ext0000");
        }
        employee.setRetirementDate(employee.getDateOfBirth().plusYears(60));
        employee.setEmployeeId(employee.getHiredDate()
                .toString().substring(0, 8).concat(String.valueOf(new Random().nextInt(1000))));
//        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setAge(Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears());
        employee.setRetirementDate(employee.getDateOfBirth().plusYears(60));
        return employeeRepository.save(employee);
    }

    @Override
    public void addRoleToEmployee(String searchKey, String roleName) {
        Employee employee = employeeRepository
                .findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(searchKey, searchKey, searchKey, searchKey)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + searchKey + " not found"));
        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role " + roleName + " not found"));
        employee.getRoles().add(role);
        employeeRepository.save(employee);
    }

    @Override
    public Employee fetchEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID " + id + " not found"));
    }

    @Override
    public Employee fetchUsernameOrEmailOrMobileOrEmployeeId(String searchKey) {
        return employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(searchKey, searchKey,
                        searchKey, searchKey)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + searchKey + " not found"));
    }

    @Override
    public List<Employee> fetchAllEmployees(String searchKey, int pageNumber, int pageSize) {
        if (searchKey.equals("")) {
            return employeeRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
        } else return employeeRepository.findEmployeeByFirstNameOrLastNameOrOtherNames(searchKey,
                searchKey, searchKey);
    }

    @Override
    public List<Employee> fetchEmployeeHiredDateBetweenAPeriod(LocalDate date1, LocalDate date2, int pageNumber, int pageSize) {
        return employeeRepository.findByHiredDateBetweenTwoDates(date1, date2, PageRequest.of(pageNumber, pageSize));

    }

    @Override
    public Employee editEmployee(Employee employee, String employeeId) {
        Employee savedEmployee = employeeRepository.findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId,
                        employeeId, employeeId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID " + employeeId + " not found"));
        if (Objects.nonNull(employee.getFirstName()) && !"".equalsIgnoreCase(employee.getFirstName())) {
            savedEmployee.setFirstName(employee.getFirstName());
        }
        if (Objects.nonNull(employee.getLastName()) && !"".equalsIgnoreCase(employee.getLastName())) {
            savedEmployee.setLastName(employee.getLastName());
        }
        if (Objects.nonNull(employee.getOtherNames()) && !"".equalsIgnoreCase(employee.getOtherNames())) {
            savedEmployee.setOtherNames(employee.getOtherNames());
        }
        if (Objects.nonNull(employee.getStateOfOrigin()) && !"".equalsIgnoreCase(employee.getStateOfOrigin())) {
            savedEmployee.setStateOfOrigin(employee.getStateOfOrigin());
        }
        if (Objects.nonNull(employee.getEmail()) && !"".equalsIgnoreCase(employee.getEmail())) {
            savedEmployee.setEmail(employee.getEmail());
        }
        if (Objects.nonNull(employee.getMobile()) && !"".equalsIgnoreCase(employee.getMobile())) {
            savedEmployee.setMobile(employee.getMobile());
        }
        if (Objects.nonNull(employee.getEmployeeStatus()) && !"".equals(employee.getEmployeeStatus())) {
            savedEmployee.setEmployeeStatus(employee.getEmployeeStatus());
        }
        if (Objects.nonNull(employee.getNationalities()) && !"".equals(employee.getNationalities())) {
            savedEmployee.setNationalities(employee.getNationalities());
        }
        if (Objects.nonNull(employee.getAddress()) && !"".equals(employee.getAddress())) {
            savedEmployee.setAddress(employee.getAddress());
        }
        if (Objects.nonNull(employee.getPicture()) && !"".equals(employee.getPicture())) {
            savedEmployee.setPicture(employee.getPicture());
        }
        return employeeRepository.save(savedEmployee);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository
                .findEmployeeByUsernameOrEmailOrMobileOrEmployeeId(employeeId, employeeId, employeeId, employeeId);
        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteEmployeeById(employeeId);
        } else throw new EmployeeNotFoundException("Employee ID " + employeeId + " not found");
    }

    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    private static boolean validatePhoneNumber(Employee employee) {
        // validate phone numbers of format "1234567890"
        if (employee.getMobile().matches("\\d{10}")) {
            return true;
        }
        // validate phone numbers of format "12345678901"
        else if (employee.getMobile().matches("\\d{11}")) {
            return true;
        }
        // validating phone number with -, . or spaces
        else if (employee.getMobile().matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return true;
        }
        // validating phone number with extension length from 3 to 5
        else if (employee.getMobile().matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            return true;
        }
        // validating phone number where area code is in braces ()
        else if (employee.getMobile().matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return true;
        }    // Validation for India numbers
        else if (employee.getMobile().matches("\\d{4}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}")) {
            return true;
        } else if (employee.getMobile().matches("\\(\\d{5}\\)-\\d{3}-\\d{3}")) {
            return true;
        } else if (employee.getMobile().matches("\\(\\d{4}\\)-\\d{3}-\\d{3}")) {
            return true;
        }    // return false if nothing matches the input
        else {
            return false;
        }
    }
}
