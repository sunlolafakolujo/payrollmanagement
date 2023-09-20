package com.logicgate.payrollmanagement.country.repository;

import com.logicgate.payrollmanagement.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long>, CountryRepositoryCustom {
}
