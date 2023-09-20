package com.logicgate.payrollmanagement.address.service;

import com.logicgate.payrollmanagement.address.exception.AddressNotFoundException;
import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.address.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.SequenceGenerator;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address addAddress(Address address) {
        log.info("Address saved successfully");
        return addressRepository.save(address);
    }

    @Override
    public Address fetAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(()->new AddressNotFoundException("Address "+id+" not found"));
    }

    @Override
    public List<Address> fetchAllAddresses(int pageNumber, int pageSize) {
        log.info("All addresses fetched successfully");
        return addressRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Address editAddress(Address address, Long id) {
        Address savedAddress = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address ID " + id + " not found"));
        log.info("Address {} updated successfully", id);
        if (Objects.nonNull(address.getHouseNumber()) && !"".equalsIgnoreCase(address.getHouseNumber())){
            savedAddress.setHouseNumber(address.getHouseNumber());
        }if (Objects.nonNull(address.getStreetName()) && !"".equalsIgnoreCase(address.getStreetName())){
            savedAddress.setStreetName(address.getStreetName());
        }if (Objects.nonNull(address.getCity()) && !"".equalsIgnoreCase(address.getCity())){
            savedAddress.setCity(address.getCity());
        }if (Objects.nonNull(address.getLandmark()) && !"".equalsIgnoreCase(address.getLandmark())){
            savedAddress.setLandmark(address.getLandmark());
        }if (Objects.nonNull(address.getProvince()) && !"".equals(address.getProvince())){
            savedAddress.setProvince(address.getProvince());
        }   if (Objects.nonNull(address.getCountry()) && !"".equals(address.getCountry())){
            savedAddress.setCountry(address.getCountry());
        }
        return addressRepository.save(savedAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        } else throw new AddressNotFoundException("Address " + id + " not found");
        log.info("Address {} deleted successfully", id);
    }

    @Override
    public void deleteAllAddresses() {
        addressRepository.deleteAll();
        log.info("Addresses deleted successfully");
    }
}
