package com.logicgate.payrollmanagement.address.service;

import com.logicgate.payrollmanagement.address.model.Address;

import java.util.List;

public interface AddressService {
    Address addAddress(Address address);

    Address fetAddressById(Long id);

    List<Address> fetchAllAddresses(int pageNumber, int pageSize);

    Address editAddress(Address address, Long id);

    void deleteAddress(Long id);

    void deleteAllAddresses();
}
