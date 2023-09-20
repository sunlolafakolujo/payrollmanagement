package com.logicgate.payrollmanagement.country.service;

import com.logicgate.payrollmanagement.country.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Country addCountry(Country country);

    Country fetchCountry(Long id);

    Country fetchCountryByName(String countryName);

    List<Country> fetchAllCountries(int pageNumber, int pageSize);

    Country editCountry(Country country, Long id);

    void deleteCountry(Long id);

    void deleteAllCountries();
}
