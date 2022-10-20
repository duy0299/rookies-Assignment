package com.rookies.assignment.exceptions;

public class ParamNotValidException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ParamNotValidException() {
		super();
	}

	public ParamNotValidException(String message) {
		super(message);
	}

	public ParamNotValidException(String message, Throwable cause) {
		super(message, cause);
	}
}
