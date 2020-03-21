package com.bangla.snacks.common.change.log.repository;

import com.bangla.snacks.common.change.log.model.ChangeLogDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeLogRepository extends JpaRepository<ChangeLogDB, String> {

}
