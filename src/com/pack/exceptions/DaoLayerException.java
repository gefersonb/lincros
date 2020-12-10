package com.pack.exceptions;


public class DaoLayerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoLayerException() {
		super();
	}

	public DaoLayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoLayerException(String message) {
		super(message);
	}

	public DaoLayerException(Throwable cause) {
		super(cause);
	}

}