package com.smartlogger.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class LoggingEntity {
	private long id;
    private String name;
    private int logId;
    private String message;
    private LocalDateTime createdDateTime;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDateTime = createdDate;
	}
	
	public LocalDateTime getCreatedDate() {
		return createdDateTime;
	}

    @PrePersist
    protected void prePersist() {
        if (this.createdDateTime == null) createdDateTime = LocalDateTime.now();
    }
	
	@Override
	public String toString() {
		return "LoggingEntity [id=" + id + ", name=" + name + ", logId=" + logId + ", message=" + message
				+ ", createdDateTime=" + createdDateTime + "]";
	}
}
