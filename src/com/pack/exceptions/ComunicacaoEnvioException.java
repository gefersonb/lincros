package com.pack.exceptions;

public class ComunicacaoEnvioException extends Exception {

	private static final long serialVersionUID = 1L;

	public ComunicacaoEnvioException() {
		super();
	}

	public ComunicacaoEnvioException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComunicacaoEnvioException(String message) {
		super(message);
	}

	public ComunicacaoEnvioException(Throwable cause) {
		super(cause);
	}

}