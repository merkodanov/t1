package com.t1.repository;

import com.t1.infrastucture.DataSourceErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataSourceErrorLogRepository extends JpaRepository<DataSourceErrorLog, Long> {
}
