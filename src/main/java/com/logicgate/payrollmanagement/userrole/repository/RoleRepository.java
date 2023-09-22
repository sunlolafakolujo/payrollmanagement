package com.logicgate.payrollmanagement.userrole.repository;

import com.logicgate.payrollmanagement.userrole.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>, RoleRepositoryCustom {
}
