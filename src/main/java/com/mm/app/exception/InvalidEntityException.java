package com.mm.app.exception;

public class InvalidEntityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1859969001412392118L;

	public InvalidEntityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidEntityException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidEntityException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
