package com.logicgate.payrollmanagement.address.controller;

import com.logicgate.payrollmanagement.address.model.Address;
import com.logicgate.payrollmanagement.address.model.AddressDto;
import com.logicgate.payrollmanagement.address.model.EditAddress;
import com.logicgate.payrollmanagement.address.model.PostAddress;
import com.logicgate.payrollmanagement.address.service.AddressService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/payroll")
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @PostMapping("/addAddress")
    public ResponseEntity<PostAddress> addAddress(@RequestBody PostAddress postAddress) {
        Address address = modelMapper.map(postAddress, Address.class);
        Address post = addressService.addAddress(address);
        PostAddress posted = modelMapper.map(post, PostAddress.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/address")
    public ResponseEntity<AddressDto> getAddressById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(convertAddressToDto(addressService.fetAddressById(id)), HttpStatus.OK);
    }

    @GetMapping("/allAddresses")
    public ResponseEntity<List<AddressDto>> getAllAddresses(@RequestParam("pageNumber") int pageNumber,
                                                            @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(addressService.fetchAllAddresses(pageNumber, pageSize)
                .stream()
                .map(this::convertAddressToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editAddress")
    public ResponseEntity<EditAddress> editAddress(@RequestBody EditAddress editAddress, @RequestParam("id") Long id) {
        Address address = modelMapper.map(editAddress, Address.class);
        Address post = addressService.editAddress(address, id);
        EditAddress posted = modelMapper.map(post, EditAddress.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress")
    public ResponseEntity<?> deleteAddress(@RequestParam("id") Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllAddresses() {
        addressService.deleteAllAddresses();
        return ResponseEntity.noContent().build();
    }

    private AddressDto convertAddressToDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setHouseNumber(address.getHouseNumber());
        addressDto.setStreetName(address.getStreetName());
        addressDto.setCity(address.getCity());
        addressDto.setLandmark(address.getLandmark());
        addressDto.setStateName(address.getProvince().getStateName());
        addressDto.setCountryName(address.getCountry().getCountryName());
        return addressDto;
    }
}
