//package com.logicgate.payrollmanagement.allowance.repository;
//
//import com.logicgate.payrollmanagement.allowance.model.Allowance;
//import com.logicgate.payrollmanagement.staticdata.AllowanceType;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Optional;
//
//public interface AllowanceRepositoryCustom {
//    @Query("From Allowance a Where a.allowanceType=?1")
//    Optional<Allowance> findAllowanceByType(AllowanceType allowanceType);
//}
