package com.logicgate.payrollmanagement.country.service;

import com.logicgate.payrollmanagement.country.exception.CountryNotFoundException;
import com.logicgate.payrollmanagement.country.model.Country;
import com.logicgate.payrollmanagement.country.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class CountryServiceImp implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country addCountry(Country country) {
        Optional<Country> savedCountry = countryRepository.findCountryByName(country.getCountryName());
        if (savedCountry.isPresent()) {
            throw new CountryNotFoundException("Country " + country.getCountryName() + " already exist");
        }
        log.info("Country {} added to the database successfully", country.getCountryName());
        return countryRepository.save(country);
    }

    @Override
    public Country fetchCountry(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(()->new CountryNotFoundException("Country "+id+" not found"));
    }

    @Override
    public Country fetchCountryByName(String countryName) {
        log.info("Country {} fetched successfully", countryName);
        return countryRepository.findCountryByName(countryName)
                .orElseThrow(() -> new CountryNotFoundException("Country " + countryName + " not found"));
    }

    @Override
    public List<Country> fetchAllCountries(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        log.info("Fetched all countries successfully");
        return countryRepository.findAll(pageable).toList();
    }

    @Override
    public Country editCountry(Country country, Long id) {
        Country savedCountry = countryRepository.findById(id)
                .orElseThrow(()->new CountryNotFoundException("Country "+id+" not found"));
        if (Objects.nonNull(country.getCountryName()) && !"".equalsIgnoreCase(country.getCountryName())) {
            savedCountry.setCountryName(country.getCountryName());
        }
        log.info("Country {} successfully updated", savedCountry.getCountryName());
        return countryRepository.save(savedCountry);
    }

    @Override
    public void deleteCountry(Long id) {

        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
        } else throw new CountryNotFoundException("Country " + id + " not found");
        log.info("Country {} successfully deleted", id);
    }

    @Override
    public void deleteAllCountries() {
        countryRepository.deleteAll();
        log.info("Countries successfully deleted");
    }
}
