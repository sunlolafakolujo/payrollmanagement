package com.logicgate.payrollmanagement.country.controller;

import com.logicgate.payrollmanagement.country.model.Country;
import com.logicgate.payrollmanagement.country.model.CountryDto;
import com.logicgate.payrollmanagement.country.model.EditCountry;
import com.logicgate.payrollmanagement.country.model.PostCountry;
import com.logicgate.payrollmanagement.country.service.CountryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/payroll")
@AllArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    @PostMapping("/addCountry")
    public ResponseEntity<PostCountry> addCountry(@RequestBody PostCountry postCountry) {
        Country country = modelMapper.map(postCountry, Country.class);
        Country post = countryService.addCountry(country);
        PostCountry posted = modelMapper.map(post, PostCountry.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/country")
    public ResponseEntity<CountryDto> getCountryByName(@RequestParam("countryName") String countryName) {
        return new ResponseEntity<>(convertCountryToDto(countryService.fetchCountryByName(countryName)), HttpStatus.OK);
    }

    @GetMapping("/allCountries")
    public ResponseEntity<List<CountryDto>> geAllCountries(@RequestParam(defaultValue = "0") int pageNumber, int pageSize) {
        return new ResponseEntity<>(countryService.fetchAllCountries(pageNumber, pageSize)
                .stream()
                .map(this::convertCountryToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editCountry")
    public ResponseEntity<EditCountry> updateCountry(@RequestBody EditCountry editCountry,
                                                     @RequestParam("id") Long id) {
        Country country = modelMapper.map(editCountry, Country.class);
        Country post = countryService.editCountry(country, id);
        EditCountry posted = modelMapper.map(post, EditCountry.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCountry")
    public ResponseEntity<?> deleteCountry(@RequestParam("id") Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllCountries() {
        countryService.deleteAllCountries();
        return ResponseEntity.noContent().build();
    }


    private CountryDto convertCountryToDto(Country country) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setCountryName(country.getCountryName());
        return countryDto;
    }
}
