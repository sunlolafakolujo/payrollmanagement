package com.logicgate.payrollmanagement.state.service;

import com.logicgate.payrollmanagement.state.model.Province;

import java.util.List;

public interface StateService {
    Province addState(Province province);

    Province fetchState(Long id);

    Province fetchStateByName(String stateName);

    List<Province> fetchAllStates(int pageNumber, int pageSize);

    Province editState(Province province, Long id);

    void deleteState(Long id);

    void deleteAllStates();
}
