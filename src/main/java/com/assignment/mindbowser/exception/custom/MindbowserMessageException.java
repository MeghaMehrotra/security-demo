package com.assignment.mindbowser.exception.custom;

import org.springframework.http.HttpStatus;

public class MindbowserMessageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus type;

	/**
	 * @return the type
	 */
	public HttpStatus getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(HttpStatus type) {
		this.type = type;
	}

	public MindbowserMessageException() {
	}

	public MindbowserMessageException(String message, HttpStatus type) {
		super(message);
		this.type = type;

	}

	public MindbowserMessageException(Throwable cause) {
		super(cause);
	}

	public MindbowserMessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public MindbowserMessageException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
