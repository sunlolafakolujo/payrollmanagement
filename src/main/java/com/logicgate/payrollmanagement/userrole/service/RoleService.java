package com.logicgate.payrollmanagement.userrole.service;

import com.logicgate.payrollmanagement.userrole.model.Role;

import java.util.List;

public interface RoleService {
    Role addRole(Role role);

    Role fetchById(Long id);

    Role fetchByRoleName(String roleName);

    List<Role> fetchAllRoles(int pageNumber, int pageSize);

    Role editRole(Role role, Long id);

    void deleteRole(String roleName);

    void deleteAllRoles();
}
