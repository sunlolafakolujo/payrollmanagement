package com.logicgate.payrollmanagement.address.repository;

import com.logicgate.payrollmanagement.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
