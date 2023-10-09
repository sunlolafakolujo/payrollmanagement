package com.logicgate.payrollmanagement.pensionmanager.repository;

import com.logicgate.payrollmanagement.pensionmanager.model.PensionAdministrator;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PensionAdministratorRepositoryCustom {
    @Query("From PensionAdministrator p Where p.administratorName=?1 Or p.pensionAdministratorCode=?2")
    Optional<PensionAdministrator> findByAdministratorNameOrCode(String key1, String key2);

    @Modifying
    @Query("Delete From PensionAdministrator p Where p.administratorName=?1 Or p.pensionAdministratorCode=?2")
    void deletePensionAdministratorByNameOrCode(String key1, String key2);
}
