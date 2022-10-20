package com.rookies.assignment.exceptions;

public class MethodNotAllowedException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MethodNotAllowedException() {
		super();
	}

	public MethodNotAllowedException(String message) {
		super(message);
	}

	public MethodNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}
}
