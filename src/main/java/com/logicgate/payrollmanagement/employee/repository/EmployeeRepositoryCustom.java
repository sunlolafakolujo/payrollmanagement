package com.logicgate.payrollmanagement.employee.repository;

import com.logicgate.payrollmanagement.employee.model.Employee;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryCustom {
    @Query("From Employee e Where e.firstName=?1 Or e.lastName=?2 Or e.otherNames=?3")
    List<Employee> findEmployeeByFirstNameOrLastNameOrOtherNames(String key1, String key2, String key3);

    @Query("From Employee e Where e.hiredDate Between ?1 And ?2")
    List<Employee> findByHiredDateBetweenTwoDates(LocalDate date1, LocalDate date2);

    @Query("From Employee e Where e.username=?1 Or e.email=?2 Or e.mobile=?3")
    Optional<Employee> findEmployeeByUsernameOrEmailOrMobile(String key1, String key2, String key3);
}
