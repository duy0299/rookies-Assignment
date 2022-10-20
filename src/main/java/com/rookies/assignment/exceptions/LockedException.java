package com.rookies.assignment.exceptions;

public class LockedException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public LockedException() {
		super();
	}

	public LockedException(String message) {
		super(message);
	}

	public LockedException(String message, Throwable cause) {
		super(message, cause);
	}
}
