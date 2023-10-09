package com.logicgate.payrollmanagement.allowance.controller;

import com.logicgate.payrollmanagement.allowance.model.Allowance;
import com.logicgate.payrollmanagement.allowance.model.AllowanceDto;
import com.logicgate.payrollmanagement.allowance.model.EditAllowance;
import com.logicgate.payrollmanagement.allowance.model.PostAllowance;
import com.logicgate.payrollmanagement.allowance.service.AllowanceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/payroll")
public record AllowanceController(AllowanceService allowanceService, ModelMapper modelMapper) {

    @PostMapping("/addAllowance")
    public ResponseEntity<PostAllowance> addAllowance(@RequestBody PostAllowance postAllowance) {
        Allowance allowance = modelMapper.map(postAllowance, Allowance.class);
        Allowance post = allowanceService.addAllowance(allowance);
        PostAllowance posted = modelMapper.map(post, PostAllowance.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/allowance")
    public ResponseEntity<AllowanceDto> getAllowance(@RequestParam("id") Long id) {
        return new ResponseEntity<>(convertAllowanceToDto(allowanceService.fetchAllowanceById(id)), HttpStatus.OK);
    }

    @GetMapping("/getAllAllowances")
    public ResponseEntity<List<AllowanceDto>> getAllowances(@RequestParam("pageNumber") int pageNumber,
                                                            @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(allowanceService.fetchAllowances(pageNumber, pageSize)
                .stream().map(this::convertAllowanceToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<EditAllowance> editAllowance(@RequestBody EditAllowance editAllowance,
                                                       @RequestParam("id") Long id) {
        Allowance allowance=modelMapper.map(editAllowance,Allowance.class);
        Allowance post=allowanceService.editAllowance(allowance,id);
        EditAllowance posted=modelMapper.map(post,EditAllowance.class);
        return new ResponseEntity<>(posted,HttpStatus.OK);
    }

    @DeleteMapping("deleteAllowance")
    public ResponseEntity<?> deleteAllowance(@RequestParam("id") Long id) {
        allowanceService.deleteAllowance(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllowances")
    public ResponseEntity<?> deleteAllowance(){
        allowanceService.deleteAllowances();
        return ResponseEntity.noContent().build();
    }

    private AllowanceDto convertAllowanceToDto(Allowance allowance) {
        AllowanceDto allowanceDto = new AllowanceDto();
        allowanceDto.setId(allowance.getId());
        allowanceDto.setHousingAllowance(allowance.getHousingAllowance());
        allowanceDto.setMedicalAllowance(allowance.getMedicalAllowance());
        allowanceDto.setTransportationAllowance(allowance.getTransportationAllowance());
        allowanceDto.setLunchAllowance(allowance.getLunchAllowance());

        return allowanceDto;
    }
}
