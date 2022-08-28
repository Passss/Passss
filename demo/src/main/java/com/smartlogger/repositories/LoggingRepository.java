package com.smartlogger.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.smartlogger.entities.LoggingEntity;

public interface LoggingRepository<T> extends CrudRepository<LoggingEntity, Long> {
	public List<LoggingEntity> findByLogId(int logId);
	public void deleteByLogId(int logId);
}
