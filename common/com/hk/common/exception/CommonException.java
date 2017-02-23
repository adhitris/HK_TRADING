package com.hk.common.exception;

@SuppressWarnings("serial")
public class CommonException extends ServiceException{

	public CommonException() {
		super("Global Exception Occurs.");
		// TODO Auto-generated constructor stub
	}

	public CommonException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CommonException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CommonException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
