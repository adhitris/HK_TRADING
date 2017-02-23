package com.hk.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hk.common.model.BaseModel;


@Entity
@Table(name = "EXCEPTION_EVENT")
public class ExceptionEvent extends BaseModel{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Exception_ID")
	private Long id;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name = "EXCEPTION_TYPE")
	private String exceptionType;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "MESSAGE",columnDefinition="text")
	private String message;
	
	@Column(name = "STACKTRACE",columnDefinition="text")
	private String stackTrace;
	
	@Column(name = "DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public Long getId() {
		return id;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	

}
