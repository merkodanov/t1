package com.t1.repository;

import com.t1.infrastucture.TimeLimitExceedLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeLimitExceedLogRepository extends JpaRepository<TimeLimitExceedLog, Long> {
}
