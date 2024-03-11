package com.mm.app.exception;

public class EntityExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 498921020007735325L;

	public EntityExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EntityExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EntityExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EntityExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
