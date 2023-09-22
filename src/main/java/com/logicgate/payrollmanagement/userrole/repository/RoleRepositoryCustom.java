package com.logicgate.payrollmanagement.userrole.repository;

import com.logicgate.payrollmanagement.userrole.model.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepositoryCustom {
    @Query("From Role r Where r.roleName=?1")
    Optional<Role> findByRoleName(String roleName);

    @Modifying
    @Query("Delete From Role r Where r.roleName=?1")
    void deleteByRoleName(String roleName);

}
