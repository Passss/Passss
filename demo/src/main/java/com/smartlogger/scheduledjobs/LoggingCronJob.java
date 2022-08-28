package com.smartlogger.scheduledjobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.smartlogger.services.LoggingService;

@Configuration
@PropertySource("classpath:application.properties")
@EnableScheduling
@EnableAsync
public class LoggingCronJob {
	@Autowired
	private LoggingService service;
	
    @Value("${app.logging.maxagelimit}")
    private int maxAgeLimit;

    public int getMaxAgeLimit() {
    	return maxAgeLimit;
    }

    public void setMaxAgeLimit(int customMaxAgeLimit) {
    	this.maxAgeLimit = customMaxAgeLimit;
    }
    
    @Async
    @Scheduled(fixedRate = 60000)
    public void defaultScheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println("CRON DELETING ALL LOGS");
        System.out.println("DEFAULT CRON JOB RUNNING WITH MAX AGE LIMIT " + maxAgeLimit);
        service.deleteAllOlderThan(maxAgeLimit);
        System.out.println("CRON DELETED ALL LOGS");
    }
}
