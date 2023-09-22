package com.logicgate.payrollmanagement.userrole.service;

import com.logicgate.payrollmanagement.userrole.exception.RoleNotFoundException;
import com.logicgate.payrollmanagement.userrole.model.Role;
import com.logicgate.payrollmanagement.userrole.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        Optional<Role> optionalRole = roleRepository.findByRoleName(role.getRoleName());
        if (optionalRole.isPresent()) {
            throw new RoleNotFoundException("Role " + role.getRoleName() + " already exists");
        }
        return roleRepository.save(role);
    }

    @Override
    public Role fetchById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role ID " + id + " not found"));
    }

    @Override
    public Role fetchByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role " + roleName + " not found"));
    }

    @Override
    public List<Role> fetchAllRoles(int pageNumber, int pageSize) {
        return roleRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Role editRole(Role role, Long id) {
        Role savedRole = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role ID " + id + " not found"));
        if (Objects.nonNull(role.getRoleName()) && !"".equalsIgnoreCase(role.getRoleName())) {
            savedRole.setRoleName(role.getRoleName());
        }
        if (Objects.nonNull(role.getRoleDescription()) && !"".equalsIgnoreCase(role.getRoleDescription())) {
            savedRole.setRoleDescription(role.getRoleDescription());
        }
        return roleRepository.save(savedRole);
    }

    @Override
    public void deleteRole(String roleName) {
        Optional<Role> optionalRole = roleRepository.findByRoleName(roleName);
        if (optionalRole.isPresent()) {
            roleRepository.deleteByRoleName(roleName);
        } else throw new RoleNotFoundException("Role " + roleName + " not found");
    }

    @Override
    public void deleteAllRoles() {
        roleRepository.deleteAll();
    }
}
