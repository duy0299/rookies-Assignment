package com.rookies.assignment.exceptions;

public class RepeatDataException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public RepeatDataException() {
		super();
	}

	public RepeatDataException(String message) {
		super(message);
	}

	public RepeatDataException(String message, Throwable cause) {
		super(message, cause);
	}
}
