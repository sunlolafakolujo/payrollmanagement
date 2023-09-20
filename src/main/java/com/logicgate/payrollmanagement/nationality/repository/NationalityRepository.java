package com.logicgate.payrollmanagement.nationality.repository;

import com.logicgate.payrollmanagement.nationality.model.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalityRepository extends JpaRepository<Nationality, Long>, NationalityRepositoryCustom {
}
