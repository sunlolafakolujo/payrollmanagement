package com.logicgate.payrollmanagement.nationality.service;

import com.logicgate.payrollmanagement.nationality.model.Nationality;

import java.util.List;

public interface NationalityService {
    Nationality addNationality(Nationality nationality);

    Nationality fetchById(Long id);

    Nationality fetchByNationality(String nationality);

    List<Nationality> fetchAllNationality(int pageNumber, int pageSize);

    Nationality editNationality(Nationality nationality, Long id);

    void deleteNationality(String nationality);

    void deleteAllNationality();
}
