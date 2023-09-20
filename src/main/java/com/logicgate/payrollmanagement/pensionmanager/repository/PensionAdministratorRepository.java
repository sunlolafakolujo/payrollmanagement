package com.logicgate.payrollmanagement.pensionmanager.repository;

import com.logicgate.payrollmanagement.pensionmanager.model.PensionAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PensionAdministratorRepository extends JpaRepository<PensionAdministrator, Long>,
        PensionAdministratorRepositoryCustom {
}
