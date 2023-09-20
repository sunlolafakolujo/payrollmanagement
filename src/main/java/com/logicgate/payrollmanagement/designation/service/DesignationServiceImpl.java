package com.logicgate.payrollmanagement.designation.service;

import com.logicgate.payrollmanagement.department.exception.DepartmentNotFoundException;
import com.logicgate.payrollmanagement.designation.exception.DesignationNotFoundException;
import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.designation.repository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class DesignationServiceImpl implements DesignationService {
    @Autowired
    private DesignationRepository designationRepository;

    @Override
    public Designation addDesignation(Designation designation) {
        Optional<Designation> optionalDesignation = designationRepository
                .findByDesignationTitle(designation.getDesignationTitle());
        if (optionalDesignation.isPresent()) {
            throw new DesignationNotFoundException(designation.getDesignationTitle() + " already exist");
        }
        return designationRepository.save(designation);
    }

    @Override
    public Designation fetchDesignationById(Long id) {
        return designationRepository.findById(id)
                .orElseThrow(()->new DesignationNotFoundException("Designation ID " + id + " not found"));
    }

    @Override
    public Designation fetchByDesignationTitle(String designationTitle) {
        return designationRepository.findByDesignationTitle(designationTitle)
                .orElseThrow(() -> new DepartmentNotFoundException(designationTitle + " not found"));
    }

    @Override
    public List<Designation> fetchAllDesignation(int pageNumber, int pageSize) {
        return designationRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Designation editDesignation(Designation designation, Long id) {
        Designation savedDesignation = designationRepository.findById(id)
                .orElseThrow(() -> new DesignationNotFoundException("Job ID "+id+ " not found"));
        if (Objects.nonNull(designation.getDesignationTitle()) && !"".equalsIgnoreCase(designation.getDesignationTitle())) {
            savedDesignation.setDesignationTitle(designation.getDesignationTitle());
        }
        if (Objects.nonNull(designation.getDepartment()) && !"".equals(designation.getDepartment())) {
            savedDesignation.setDepartment(designation.getDepartment());
        }
        return designationRepository.save(savedDesignation);
    }

    @Override
    public void deleteDesignation(String designationTitle) {
        Optional<Designation> optionalDesignation = designationRepository.findByDesignationTitle(designationTitle);
        if (optionalDesignation.isPresent()) {
            designationRepository.deleteByDesignationCodeOrTitle(designationTitle);
        } else throw new DesignationNotFoundException("Job title "+designationTitle + " not found");
    }

    @Override
    public void deleteAllDesignations() {
        designationRepository.deleteAll();
    }
}
