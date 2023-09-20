package com.logicgate.payrollmanagement.pensionmanager.repository;

import com.logicgate.payrollmanagement.pensionmanager.model.PensionAdministrator;
import org.springframework.data.jpa.repository.Query;

public interface PensionAdministratorRepositoryCustom {
    @Query("From PensionAdministrator p Where p.administratorName=?1")
    PensionAdministrator findByAdministratorName(String administratorName);
}
