package com.logicgate.payrollmanagement.state.service;

import com.logicgate.payrollmanagement.state.exception.StateNotFoundException;
import com.logicgate.payrollmanagement.state.model.Province;
import com.logicgate.payrollmanagement.state.repository.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class StateServiceImpl implements StateService {
    @Autowired
    private StateRepository stateRepository;

    @Override
    public Province addState(Province province) {
        Optional<Province> savedProvince = stateRepository.findByStateName(province.getStateName());
        if (savedProvince.isPresent()) {
            throw new StateNotFoundException("State " + province.getStateName() + " not found");
        }
        log.info("State {} successfully added", province.getStateName());
        return stateRepository.save(province);
    }

    @Override
    public Province fetchState(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(()->new StateNotFoundException("State "+id+" not found"));
    }


    @Override
    public Province fetchStateByName(String stateName) {
        log.info("State {} successfully retrieved", stateName);
        return stateRepository.findByStateName(stateName)
                .orElseThrow(() -> new StateNotFoundException("State " + stateName + " not found"));
    }

    @Override
    public List<Province> fetchAllStates(int pageNumber, int pageSize) {
        log.info("Fetched all states successfully");
        return stateRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Province editState(Province province, Long id) {
        Province saveProvince = stateRepository.findById(id)
                .orElseThrow(() -> new StateNotFoundException("State " + id + " not found"));
        if (Objects.nonNull(province.getStateName()) && !"".equalsIgnoreCase(province.getStateName())) {
            saveProvince.setStateName(province.getStateName());
        }
        return stateRepository.save(saveProvince);
    }

    @Override
    public void deleteState(Long id) {
        if (stateRepository.existsById(id)) {
            stateRepository.deleteById(id);
        } else throw new StateNotFoundException("State " + id + " not found");
    }

    @Override
    public void deleteAllStates() {
        stateRepository.deleteAll();
    }
}
