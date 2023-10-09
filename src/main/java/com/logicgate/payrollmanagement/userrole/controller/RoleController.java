package com.logicgate.payrollmanagement.userrole.controller;

import com.logicgate.payrollmanagement.userrole.model.EditRole;
import com.logicgate.payrollmanagement.userrole.model.PostRole;
import com.logicgate.payrollmanagement.userrole.model.Role;
import com.logicgate.payrollmanagement.userrole.model.RoleDto;
import com.logicgate.payrollmanagement.userrole.service.RoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/payroll")
public record RoleController(RoleService roleService,ModelMapper modelMapper) {

    @PostMapping("addRole")
    public ResponseEntity<PostRole> addRole(@RequestBody PostRole postRole) {
        Role role = modelMapper.map(postRole, Role.class);
        Role post = roleService.addRole(role);
        PostRole posted = modelMapper.map(post, PostRole.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/findRoleById")
    public ResponseEntity<RoleDto> getRoleById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(convertRoleToDto(roleService.fetchById(id)), HttpStatus.OK);
    }

    @GetMapping("/findRoleName")
    public ResponseEntity<RoleDto> getRoleByName(@RequestParam("roleName") String roleName) {
        return new ResponseEntity<>(convertRoleToDto(roleService.fetchByRoleName(roleName)), HttpStatus.OK);
    }

    @GetMapping("/findAllRoles")
    public ResponseEntity<List<RoleDto>> getAllRoles(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(roleService.fetchAllRoles(pageNumber, pageSize)
                .stream().map(this::convertRoleToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editRole")
    public ResponseEntity<EditRole> editRole(@RequestBody EditRole editRole, @RequestParam("id") Long id) {
        Role role = modelMapper.map(editRole, Role.class);
        Role post = roleService.editRole(role, id);
        EditRole posted = modelMapper.map(post, EditRole.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/deleteRole")
    public ResponseEntity<?> deleteRoleByName(@RequestParam("roleName") String roleName){
        roleService.deleteRole(roleName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllRoles")
    public ResponseEntity<?> deleteAllRoles(){
        roleService.deleteAllRoles();
        return ResponseEntity.noContent().build();
    }






    private RoleDto convertRoleToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRoleName(role.getRoleName());
        roleDto.setRoleDescription(role.getRoleDescription());
        return roleDto;
    }
}
