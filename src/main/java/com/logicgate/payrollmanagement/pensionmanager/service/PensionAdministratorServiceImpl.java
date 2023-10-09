package com.logicgate.payrollmanagement.pensionmanager.service;

import com.logicgate.payrollmanagement.pensionmanager.exception.PensionAdministratorNotFoundException;
import com.logicgate.payrollmanagement.pensionmanager.model.PensionAdministrator;
import com.logicgate.payrollmanagement.pensionmanager.repository.PensionAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class PensionAdministratorServiceImpl implements PensionAdministratorService {
    @Autowired
    private PensionAdministratorRepository pensionAdministratorRepository;

    @Override
    public PensionAdministrator addPensionAdministrator(PensionAdministrator pensionAdministrator) {
        Optional<PensionAdministrator> savedPensionAdministrator = pensionAdministratorRepository
                .findByAdministratorNameOrCode(pensionAdministrator.getPensionAdministratorCode(),
                        pensionAdministrator.getAdministratorName());
        if (savedPensionAdministrator.isPresent()) {
            throw new PensionAdministratorNotFoundException("Administrator " + pensionAdministrator.getPensionAdministratorCode() +
                    " or " + pensionAdministrator.getAdministratorName() + " already exists");
        }
        return pensionAdministratorRepository.save(pensionAdministrator);
    }

    @Override
    public PensionAdministrator fetchPensionAdministratorByNameOrCode(String searchKey) {
        return pensionAdministratorRepository.findByAdministratorNameOrCode(searchKey, searchKey)
                .orElseThrow(() -> new PensionAdministratorNotFoundException("Administrator " + searchKey + " not found"));
    }

    @Override
    public List<PensionAdministrator> fetchAllPensionAdministrators(int pageNumber, int pageSize) {
        return pensionAdministratorRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public PensionAdministrator editPensionAdministrator(PensionAdministrator pensionAdministrator, String searchKey) {
        PensionAdministrator savedPensionAdministrator = pensionAdministratorRepository
                .findByAdministratorNameOrCode(searchKey, searchKey)
                .orElseThrow(() -> new PensionAdministratorNotFoundException("PensionAdministrator " + searchKey + " not found"));
        if (Objects.nonNull(pensionAdministrator.getPensionAdministratorCode()) && !"".equalsIgnoreCase(pensionAdministrator.getPensionAdministratorCode())) {
            savedPensionAdministrator.setPensionAdministratorCode(pensionAdministrator.getPensionAdministratorCode());
        }
        if (Objects.nonNull(pensionAdministrator.getAdministratorName()) && !"".equalsIgnoreCase(pensionAdministrator.getAdministratorName())) {
            savedPensionAdministrator.setAdministratorName(pensionAdministrator.getAdministratorName());
        }
        if (Objects.nonNull(pensionAdministrator.getContactPerson()) && !"".equalsIgnoreCase(pensionAdministrator.getContactPerson())) {
            savedPensionAdministrator.setContactPerson(pensionAdministrator.getContactPerson());
        }
        if (Objects.nonNull(pensionAdministrator.getPhone()) && !"".equalsIgnoreCase(pensionAdministrator.getPhone())) {
            savedPensionAdministrator.setPhone(pensionAdministrator.getPhone());
        }
        if (Objects.nonNull(pensionAdministrator.getEmail()) && !"".equalsIgnoreCase(pensionAdministrator.getEmail())) {
            savedPensionAdministrator.setEmail(pensionAdministrator.getEmail());
        }
        if (Objects.nonNull(pensionAdministrator.getAddress()) && !"".equals(pensionAdministrator.getAddress())) {
            savedPensionAdministrator.setAddress(pensionAdministrator.getAddress());
        }
        return pensionAdministratorRepository.save(savedPensionAdministrator);
    }

    @Override
    public void deletePensionAdministrator(String searchKey) {
        Optional<PensionAdministrator> pensionAdministrator = pensionAdministratorRepository
                .findByAdministratorNameOrCode(searchKey, searchKey);
        if (pensionAdministrator.isPresent()) {
            pensionAdministratorRepository.deletePensionAdministratorByNameOrCode(searchKey, searchKey);
        } else throw new PensionAdministratorNotFoundException("Pension Administrator " + searchKey + " not found");
    }
}
