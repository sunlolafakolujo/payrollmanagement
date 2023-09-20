package com.logicgate.payrollmanagement.state.repository;

import com.logicgate.payrollmanagement.state.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<Province, Long>, StatesRepositoryCustom {
}
