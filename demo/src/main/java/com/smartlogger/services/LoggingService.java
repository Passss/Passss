package com.smartlogger.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartlogger.controllers.LoggingState;
import com.smartlogger.entities.LoggingEntity;
import com.smartlogger.repositories.LoggingRepository;

@Service
public class LoggingService {
	@Autowired
	LoggingRepository<LoggingEntity> repository;
	
	@Transactional
	public List<LoggingEntity> findByLogId(int logId) {
		return repository.findByLogId(logId);
	}

	@Transactional
	public void addLogging(LoggingEntity entity) {
		repository.save(entity);
	}

	@Transactional
	public void deleteLogging(int logId) {
		repository.deleteByLogId(logId);
	}

	@Transactional
	public void deleteAll() {
		repository.deleteAll();
	}

	public long getNumberOfMessages() {
		return repository.count();
	}
	
	@Transactional
	public void deleteAllOlderThan(int maxAge) {
		List<LoggingEntity> loggings 
		    = StreamSupport.stream(repository.findAll().spliterator(), false)
		          .collect(Collectors.toList());
		
		List<LoggingEntity> loggingsToBeRemoved = loggings.stream().filter(logging 
				-> Duration.between(logging.getCreatedDate(),
						LocalDateTime.now()).getSeconds() > maxAge)
		    .collect(Collectors.toList());
		repository.deleteAll(loggingsToBeRemoved);
	}
}
