package com.rookies.assignment.exceptions;

public class TooManyRequestsException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public TooManyRequestsException() {
		super();
	}

	public TooManyRequestsException(String message) {
		super(message);
	}

	public TooManyRequestsException(String message, Throwable cause) {
		super(message, cause);
	}
}
