package com.logicgate.payrollmanagement.country.repository;

import com.logicgate.payrollmanagement.country.model.Country;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepositoryCustom {
    @Query("From Country c Where c.countryName=?1")
    Optional<Country> findCountryByName(String countryName);

    void deleteByCountryName(String countryName);
}
