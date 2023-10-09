package com.logicgate.payrollmanagement.department.controller;

import com.logicgate.payrollmanagement.department.model.Department;
import com.logicgate.payrollmanagement.department.model.DepartmentDto;
import com.logicgate.payrollmanagement.department.model.EditDepartment;
import com.logicgate.payrollmanagement.department.model.PostDepartment;
import com.logicgate.payrollmanagement.department.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/payroll")
public record DepartmentController(DepartmentService departmentService, ModelMapper modelMapper) {

    @PostMapping("/addDepartment")
    public ResponseEntity<PostDepartment> addDepartment(@RequestBody PostDepartment postDepartment) {
        Department department = modelMapper.map(postDepartment, Department.class);
        Department post = departmentService.addDepartment(department);
        PostDepartment posted = modelMapper.map(post, PostDepartment.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/findDepartment")
    public ResponseEntity<DepartmentDto> getDepartmentByNameOrCostCentre(@RequestParam("searchKey") String searchKey) {
        Department department = departmentService.fetchDepartmentByCostCentreOrName(searchKey);
        return new ResponseEntity<>(convertDepartmentToDto(department), HttpStatus.OK);
    }

    @GetMapping("/allDepartments")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(@RequestParam("pageNumber") int pageNumber,
                                                                 @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(departmentService.fetchAllDepartments(pageNumber, pageSize)
                .stream().map(this::convertDepartmentToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editDepartment")
    public ResponseEntity<EditDepartment> editDepartment(@RequestBody EditDepartment editDepartment,
                                                         @RequestParam("searchKey") String searchKey) {
        Department department = modelMapper.map(editDepartment, Department.class);
        Department post = departmentService.editDepartment(department, searchKey);
        EditDepartment posted = modelMapper.map(post, EditDepartment.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDepartment")
    public ResponseEntity<?> deleteDepartment(@RequestParam("searchKey") String searchKey) {
        departmentService.deleteDepartment(searchKey);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllDepartment")
    public ResponseEntity<?> deleteAllDepartment() {
        departmentService.deleteAllDepartments();
        return ResponseEntity.noContent().build();
    }


    private DepartmentDto convertDepartmentToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setCostCentre(department.getCostCentre());
        departmentDto.setDepartmentName(department.getDepartmentName());
        departmentDto.setDepartmentLocation(department.getDepartmentLocation());
        return departmentDto;
    }
}
