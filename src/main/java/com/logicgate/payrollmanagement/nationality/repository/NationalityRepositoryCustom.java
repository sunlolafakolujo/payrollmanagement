package com.logicgate.payrollmanagement.nationality.repository;

import com.logicgate.payrollmanagement.nationality.model.Nationality;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NationalityRepositoryCustom {
    @Query("From Nationality n Where n.nationality=?1")
    Optional<Nationality> findByNationality(String nationality);

    @Modifying
    @Query("Delete From Nationality n Where n.nationality=?1")
    void deleteByNationality(String nationality);
}
