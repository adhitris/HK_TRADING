package com.hk.common.exception;

@SuppressWarnings("serial")
public abstract class ServiceException extends Exception{

	public ServiceException() {
		super("Service Process Fail.");
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
