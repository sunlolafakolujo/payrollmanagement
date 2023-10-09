package com.logicgate.payrollmanagement.pensionmanager.controller;

import com.logicgate.payrollmanagement.pensionmanager.model.EditPensionAdministrator;
import com.logicgate.payrollmanagement.pensionmanager.model.PensionAdministrator;
import com.logicgate.payrollmanagement.pensionmanager.model.PensionAdministratorDto;
import com.logicgate.payrollmanagement.pensionmanager.model.PostPensionAdministrator;
import com.logicgate.payrollmanagement.pensionmanager.service.PensionAdministratorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/payroll")
public record PensionAdministratorController(PensionAdministratorService pensionAdministratorService,
                                             ModelMapper modelMapper) {
    @PostMapping("/addPensionAdministrator")
    public ResponseEntity<PostPensionAdministrator> addPensionAdministrator(@RequestBody PostPensionAdministrator postPensionAdministrator) {
        PensionAdministrator pensionAdministrator = modelMapper.map(postPensionAdministrator, PensionAdministrator.class);
        PensionAdministrator post = pensionAdministratorService.addPensionAdministrator(pensionAdministrator);
        PostPensionAdministrator posted = modelMapper.map(post, PostPensionAdministrator.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/findAdministrator")
    public ResponseEntity<PensionAdministratorDto> getPensionAdministrator(@RequestParam("searchKey") String searchKey) {
        return new ResponseEntity<>(convertPensionAdministratorToDto(pensionAdministratorService
                .fetchPensionAdministratorByNameOrCode(searchKey)), HttpStatus.OK);
    }

    @GetMapping("/findAdministrators")
    public ResponseEntity<List<PensionAdministratorDto>> getAllAdministrator(@RequestParam(defaultValue = "0") int pageNumber,
                                                                             @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(pensionAdministratorService.fetchAllPensionAdministrators(pageNumber, pageSize)
                .stream()
                .map(this::convertPensionAdministratorToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editAdministrator")
    public ResponseEntity<EditPensionAdministrator> editPensionAdministrator(@RequestBody EditPensionAdministrator editPensionAdministrator,
                                                                             @RequestParam("searchKey") String searchKey) {
        PensionAdministrator pensionAdministrator = modelMapper.map(editPensionAdministrator, PensionAdministrator.class);
        PensionAdministrator post = pensionAdministratorService.editPensionAdministrator(pensionAdministrator, searchKey);
        EditPensionAdministrator posted = modelMapper.map(post, EditPensionAdministrator.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAdministrator")
    public ResponseEntity<?> deletePensionAdministrator(@RequestParam("searchKey") String searchKey) {
        pensionAdministratorService.deletePensionAdministrator(searchKey);
        return ResponseEntity.noContent().build();
    }


    private PensionAdministratorDto convertPensionAdministratorToDto(PensionAdministrator pensionAdministrator) {
        PensionAdministratorDto pensionAdministratorDto = new PensionAdministratorDto();
        pensionAdministratorDto.setPensionAdministratorCode(pensionAdministrator.getPensionAdministratorCode());
        pensionAdministratorDto.setAdministratorName(pensionAdministrator.getAdministratorName());
        pensionAdministratorDto.setContactPerson(pensionAdministrator.getContactPerson());
        pensionAdministratorDto.setPhone(pensionAdministrator.getPhone());
        pensionAdministratorDto.setEmail(pensionAdministrator.getEmail());
        pensionAdministratorDto.setOfficeNumber(pensionAdministrator.getAddress().getHouseNumber());
        pensionAdministratorDto.setOfficeStreetName(pensionAdministrator.getAddress().getStreetName());
        pensionAdministratorDto.setOfficeCity(pensionAdministrator.getAddress().getCity());
        pensionAdministratorDto.setOfficeLandmark(pensionAdministrator.getAddress().getLandmark());
        pensionAdministratorDto.setStateName(pensionAdministrator.getAddress().getProvince().getStateName());
        pensionAdministratorDto.setCountryName(pensionAdministrator.getAddress().getCountry().getCountryName());
        return pensionAdministratorDto;
    }

}
