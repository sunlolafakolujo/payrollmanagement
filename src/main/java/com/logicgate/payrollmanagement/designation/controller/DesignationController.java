package com.logicgate.payrollmanagement.designation.controller;

import com.logicgate.payrollmanagement.designation.model.Designation;
import com.logicgate.payrollmanagement.designation.model.DesignationDto;
import com.logicgate.payrollmanagement.designation.model.EditDesignation;
import com.logicgate.payrollmanagement.designation.model.PostDesignation;
import com.logicgate.payrollmanagement.designation.service.DesignationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/payroll")
public record DesignationController(DesignationService designationService,ModelMapper modelMapper ) {

    @PostMapping("/addDesignation")
    public ResponseEntity<PostDesignation> addDesignation(@RequestBody PostDesignation postDesignation) {
        Designation designation = modelMapper.map(postDesignation, Designation.class);
        Designation post = designationService.addDesignation(designation);
        PostDesignation posted = modelMapper.map(post, PostDesignation.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/designation")
    public ResponseEntity<DesignationDto> getDesignation(@RequestParam("designationTitle") String designationTitle) {
        return new ResponseEntity<>(convertDesignationToDto(designationService.fetchByDesignationTitle(designationTitle)), HttpStatus.OK);
    }

    @GetMapping("/allDesignations")
    public ResponseEntity<List<DesignationDto>> getAllDesignations(@RequestParam("pageNumber") int pageNumber,
                                                                   @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(designationService.fetchAllDesignation(pageNumber, pageSize)
                .stream().map(this::convertDesignationToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editDesignation")
    public ResponseEntity<EditDesignation> editDesignation(@RequestBody EditDesignation editDesignation,
                                                           @RequestParam("id") Long id) {
        Designation designation = modelMapper.map(editDesignation, Designation.class);
        Designation post = designationService.editDesignation(designation, id);
        EditDesignation posted = modelMapper.map(post, EditDesignation.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDesignation")
    public ResponseEntity<?> deleteDesignation(@RequestParam("designationTitle") String designationTitle) {
        designationService.deleteDesignation(designationTitle);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllDesignations")
    public ResponseEntity<?> deleteAllDesignations() {
        designationService.deleteAllDesignations();
        return ResponseEntity.noContent().build();
    }

    private DesignationDto convertDesignationToDto(Designation designation) {
        DesignationDto designationDto = new DesignationDto();
        designationDto.setId(designation.getId());
        designationDto.setDesignationTitle(designation.getDesignationTitle());
        designationDto.setCostCentre(designation.getDepartment().getCostCentre());
        return designationDto;
    }
}
