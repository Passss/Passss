package com.smartlogger.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.smartlogger.entities.LoggingEntity;
import com.smartlogger.scheduledjobs.LoggingCronJob;
import com.smartlogger.services.LoggingService;

@RestController
public class LoggingController {
	private static final String DEFAULT_MESSAGE = "Hello World!";
	private static final String SCHEDULED_TASKS = "scheduledTasks";
	
	@Autowired
	private LoggingService service;

	@Autowired
	private ScheduledAnnotationBeanPostProcessor postProcessor;

	@Autowired
	private LoggingCronJob cronJob;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getDefault()
	{
		return DEFAULT_MESSAGE;
	}
	
	@RequestMapping(value = "/get/{logId}", method = RequestMethod.GET)
	public List<LoggingEntity> getLoggingsById(@PathVariable int logId) {
		return service.findByLogId(logId);
	}
	
	@RequestMapping(value="/createorupdate", method=RequestMethod.POST)
	public void createOrUpdateLogging(@RequestBody LoggingEntity entity) {
		service.addLogging(entity);
	}

	@RequestMapping(value="/delete/{logId}", method=RequestMethod.DELETE)
	public void deleteLogging(@PathVariable int logId) {
		service.deleteLogging(logId);
	}

	@RequestMapping(value="/getcurrentstate", method=RequestMethod.GET)
	public LoggingState getCurrentLoggingState() {
		LoggingState state = new LoggingState();
		state.setNoOfStoredMessages(service.getNumberOfMessages());
		state.setCurrentNumberOfLogs(service.getNumberOfMessages());
		state.setMaxAgeLimit(cronJob.getMaxAgeLimit());
		return state;
	}

	@RequestMapping(value = "/maxagelimit/{customMaxAgeLimit}", method=RequestMethod.GET)
	public String setMaxAgeLimit(@PathVariable int customMaxAgeLimit) {
		postProcessor.postProcessBeforeDestruction(cronJob, SCHEDULED_TASKS);
		cronJob.setMaxAgeLimit(customMaxAgeLimit);
		postProcessor.postProcessAfterInitialization(cronJob, SCHEDULED_TASKS);
		return "OK";
	}
}
