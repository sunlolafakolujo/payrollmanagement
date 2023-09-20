package com.logicgate.payrollmanagement.nationality.controller;

import com.logicgate.payrollmanagement.nationality.model.EditNationality;
import com.logicgate.payrollmanagement.nationality.model.Nationality;
import com.logicgate.payrollmanagement.nationality.model.NationalityDto;
import com.logicgate.payrollmanagement.nationality.model.PostNationality;
import com.logicgate.payrollmanagement.nationality.service.NationalityService;
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
public class NationalityController {
    private final NationalityService nationalityService;
    private final ModelMapper modelMapper;

    @PostMapping("/addNationality")
    public ResponseEntity<PostNationality> addNationality(@RequestBody PostNationality postNationality) {
        Nationality nationality = modelMapper.map(postNationality, Nationality.class);
        Nationality post = nationalityService.addNationality(nationality);
        PostNationality posted = modelMapper.map(post, PostNationality.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/findNationalityById")
    public ResponseEntity<NationalityDto> getNationalityById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(convertNationalityToDto(nationalityService.fetchById(id)), HttpStatus.OK);
    }

    @GetMapping("/findNationalityByName")
    public ResponseEntity<NationalityDto> getNationalityByName(@RequestParam("nationality") String nationality) {
        return new ResponseEntity<>(convertNationalityToDto(nationalityService
                .fetchByNationality(nationality)), HttpStatus.OK);
    }

    @GetMapping("/findAllNationality")
    public ResponseEntity<List<NationalityDto>> findAllNationality(@RequestParam("pageNumber") int pageNumber,
                                                                   @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(nationalityService.fetchAllNationality(pageNumber, pageSize)
                .stream().map(this::convertNationalityToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editNationality")
    public ResponseEntity<EditNationality> editNationality(@RequestBody EditNationality editNationality,
                                                           @RequestParam("id") Long id) {
        Nationality nationality = modelMapper.map(editNationality, Nationality.class);
        Nationality post = nationalityService.editNationality(nationality, id);
        EditNationality posted = modelMapper.map(post, EditNationality.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteNationality")
    public ResponseEntity<?> deleteNational(@RequestParam("nationality") String nationality) {
        nationalityService.deleteNationality(nationality);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllNationality")
    public ResponseEntity<?> deleteAllNationality(){
        nationalityService.deleteAllNationality();
        return ResponseEntity.noContent().build();
    }

    private NationalityDto convertNationalityToDto(Nationality nationality) {
        NationalityDto nationalityDto = new NationalityDto();
        nationalityDto.setId(nationality.getId());
        nationalityDto.setNationality(nationality.getNationality());
        return nationalityDto;
    }
}
