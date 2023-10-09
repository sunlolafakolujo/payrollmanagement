package com.logicgate.payrollmanagement.bank.controller;

import com.logicgate.payrollmanagement.bank.model.EditEmployeeBankDetail;
import com.logicgate.payrollmanagement.bank.model.EmployeeBankDetail;
import com.logicgate.payrollmanagement.bank.model.EmployeeBankDetailDto;
import com.logicgate.payrollmanagement.bank.model.PostEmployeeBankDetail;
import com.logicgate.payrollmanagement.bank.service.EmployeeBankDetailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/payroll")
public record EmployeeBankDetailController(EmployeeBankDetailService employeeBankDetailService,ModelMapper modelMapper) {

    @PostMapping("/addEmployeeBankDetail")
    public ResponseEntity<PostEmployeeBankDetail> addEmployeeBankDetail(@RequestBody PostEmployeeBankDetail postEmployeeBankDetail,
                                                                        @RequestParam("employeeId") String employeeId) {
        EmployeeBankDetail employeeBankDetail = modelMapper.map(postEmployeeBankDetail, EmployeeBankDetail.class);
        EmployeeBankDetail post = employeeBankDetailService.addBankDetails(employeeId, employeeBankDetail);
        PostEmployeeBankDetail posted = modelMapper.map(post, PostEmployeeBankDetail.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/findEmployeeBankDetail")
    public ResponseEntity<EmployeeBankDetailDto> getEmployeeBankDetail(@RequestParam("employeeId") String employeeId) {
        return new ResponseEntity<>(convertEmployeeBankDetailDto(employeeBankDetailService.
                fetchEmployeeBankDetail(employeeId)), HttpStatus.OK);
    }

    @GetMapping("/findEmployeesBankDetails")
    public ResponseEntity<List<EmployeeBankDetailDto>> getEmployeesBankDetails(@RequestParam("pageNumber") int pageNumber,
                                                                               @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(employeeBankDetailService.fetchEmployeeBankDetails(pageNumber, pageSize)
                .stream()
                .map(this::convertEmployeeBankDetailDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("editEmployeeBankDetail")
    public ResponseEntity<EditEmployeeBankDetail> editEmployeeBankDetails(@RequestParam("employeeId") String employeeId,
                                                                          @RequestBody EditEmployeeBankDetail editEmployeeBankDetail) {
        EmployeeBankDetail employeeBankDetail1 = modelMapper.map(editEmployeeBankDetail, EmployeeBankDetail.class);
        EmployeeBankDetail post = employeeBankDetailService.editBankDetail(employeeId, employeeBankDetail1);
        EditEmployeeBankDetail posted = modelMapper.map(post, EditEmployeeBankDetail.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @GetMapping("deleteEmployeeBankDetail")
    public ResponseEntity<?> deleteEmployeeBankDetail(@RequestParam("employeeId") String employeeId) {
        employeeBankDetailService.deleteBankByEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }


    private EmployeeBankDetailDto convertEmployeeBankDetailDto(EmployeeBankDetail employeeBankDetail) {
        EmployeeBankDetailDto employeeBankDetailDto = new EmployeeBankDetailDto();
        employeeBankDetailDto.setId(employeeBankDetail.getId());
        employeeBankDetailDto.setEmployeeId(employeeBankDetail.getEmployee().getEmployeeId());
        employeeBankDetailDto.setFirstName(employeeBankDetail.getEmployee().getFirstName());
        employeeBankDetailDto.setLastName(employeeBankDetail.getEmployee().getLastName());
        employeeBankDetailDto.setBankName(employeeBankDetail.getBankName());
        employeeBankDetailDto.setAccountType(employeeBankDetail.getAccountType());
        employeeBankDetailDto.setAccountNumber(employeeBankDetail.getAccountNumber());
        employeeBankDetailDto.setBankCode(employeeBankDetail.getBankCode());
        employeeBankDetailDto.setBankBranch(employeeBankDetail.getBankBranch());
        employeeBankDetailDto.setBankAddress(employeeBankDetail.getBankAddress());
        return employeeBankDetailDto;
    }
}
