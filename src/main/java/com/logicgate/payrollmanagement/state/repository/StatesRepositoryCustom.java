package com.logicgate.payrollmanagement.state.repository;

import com.logicgate.payrollmanagement.state.model.Province;

import java.util.Optional;

public interface StatesRepositoryCustom {
    Optional<Province> findByStateName(String stateName);

    void deleteByStateName(String stateName);
}
