package com.logicgate.payrollmanagement.nationality.service;

import com.logicgate.payrollmanagement.nationality.exception.NationalityNotFoundException;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.nationality.repository.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class NationalityServiceImpl implements NationalityService {

    @Autowired
    private NationalityRepository nationalityRepository;

    @Override
    public Nationality addNationality(Nationality nationality) {
        Optional<Nationality> optionalNationality = nationalityRepository
                .findByNationality(nationality.getNationality());
        if (optionalNationality.isPresent()) {
            throw new NationalityNotFoundException("Nationality " + nationality.getNationality() + " already exists");
        }
        return nationalityRepository.save(nationality);
    }

    @Override
    public Nationality fetchById(Long id) {
        return nationalityRepository.findById(id)
                .orElseThrow(() -> new NationalityNotFoundException("Nationality ID " + id + " not found"));
    }

    @Override
    public Nationality fetchByNationality(String nationality) {
        return nationalityRepository.findByNationality(nationality)
                .orElseThrow(() -> new NationalityNotFoundException("Nationality " + nationality + " not found"));
    }

    @Override
    public List<Nationality> fetchAllNationality(int pageNumber, int pageSize) {
        return nationalityRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Nationality editNationality(Nationality nationality, Long id) {
        Nationality savedNationality = nationalityRepository.findById(id)
                .orElseThrow(() -> new NationalityNotFoundException("Nationality ID " + id + " not found"));
        if (Objects.nonNull(nationality.getNationality()) && !"".equalsIgnoreCase(nationality.getNationality())) {
            savedNationality.setNationality(nationality.getNationality());
        }
        return nationalityRepository.save(savedNationality);
    }

    @Override
    public void deleteNationality(String nationality) {
        Optional<Nationality> optionalNationality = nationalityRepository.findByNationality(nationality);
        if (optionalNationality.isPresent()) {
            nationalityRepository.deleteByNationality(nationality);
        } else throw new NationalityNotFoundException("Nationality " + nationality + " not found");

    }

    @Override
    public void deleteAllNationality() {
        nationalityRepository.deleteAll();
    }
}
