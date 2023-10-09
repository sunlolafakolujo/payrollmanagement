package com.logicgate.payrollmanagement.allowance.service;

import com.logicgate.payrollmanagement.allowance.exception.AllowanceNotFoundException;
import com.logicgate.payrollmanagement.allowance.model.Allowance;
import com.logicgate.payrollmanagement.allowance.repository.AllowanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class AllowanceServiceImpl implements AllowanceService {

    @Autowired
    private AllowanceRepository allowanceRepository;

    @Override
    public Allowance addAllowance(Allowance allowance) {
//        Optional<Allowance> optionalAllowance = allowanceRepository.findAllowanceByType(allowance.getAllowanceType());
//        if (optionalAllowance.isPresent()) {
//            throw new AllowanceNotFoundException("Allowance " + allowance.getAllowanceType() + " already exist");
//        }
        return allowanceRepository.save(allowance);
    }

    @Override
    public Allowance fetchAllowanceById(Long id) {
        return allowanceRepository.findById(id)
                .orElseThrow(() -> new AllowanceNotFoundException("Allowance ID " + id + " not found"));
    }

    @Override
    public List<Allowance> fetchAllowances(int pageNumber, int pageSize) {
        return allowanceRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Allowance editAllowance(Allowance allowance, Long id) {
        Allowance savedAllowance = allowanceRepository.findById(id)
                .orElseThrow(() -> new AllowanceNotFoundException("Allowance ID " + id + " not found"));
        if (Objects.nonNull(allowance.getTransportationAllowance()) && !"".equals(allowance.getTransportationAllowance())) {
            savedAllowance.setTransportationAllowance(allowance.getTransportationAllowance());
        }
        if (Objects.nonNull(allowance.getHousingAllowance()) && !"".equals(allowance.getHousingAllowance())) {
            savedAllowance.setHousingAllowance(allowance.getHousingAllowance());
        }
        if (Objects.nonNull(allowance.getLunchAllowance()) && !"".equals(allowance.getLunchAllowance())) {
            savedAllowance.setLunchAllowance(allowance.getLunchAllowance());
        }
        if (Objects.nonNull(allowance.getMedicalAllowance()) && !"".equals(allowance.getMedicalAllowance())) {
            savedAllowance.setMedicalAllowance(allowance.getMedicalAllowance());
        }
        return allowanceRepository.save(savedAllowance);
    }

    @Override
    public void deleteAllowance(Long id) {
        if (allowanceRepository.existsById(id)) {
            allowanceRepository.deleteById(id);
        } else throw new AllowanceNotFoundException("Allowance ID " + id + " not found");

    }

    @Override
    public void deleteAllowances() {
        allowanceRepository.deleteAll();
    }
}
