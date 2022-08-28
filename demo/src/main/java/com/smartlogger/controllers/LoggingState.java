package com.smartlogger.controllers;

public class LoggingState {
    private long currentNumberOfLogs;
    private int maxAgeLimit;
    private long noOfStoredMessages;

	public long getCurrentNumberOfLogs() {
		return currentNumberOfLogs;
	}
	public void setCurrentNumberOfLogs(long currentNumberOfLogs) {
		this.currentNumberOfLogs = currentNumberOfLogs;
	}
	public int getMaxAgeLimit() {
		return maxAgeLimit;
	}
	public void setMaxAgeLimit(int maxAgeLimit) {
		this.maxAgeLimit = maxAgeLimit;
	}
	public long getNoOfStoredMessages() {
		return noOfStoredMessages;
	}
	public void setNoOfStoredMessages(long noOfStoredMessages) {
		this.noOfStoredMessages = noOfStoredMessages;
	}
}
