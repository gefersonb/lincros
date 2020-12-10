package com.pack.exceptions;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Classe responsável por manipular o Handler da ViewExpiredException.
 * 
 * @author Geferson Buzzello
 */
public class ApplicationExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	/**
	 * Construtor que atualiza o handler factory.
	 * 
	 * @param parent
	 */
	public ApplicationExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	/**
	 * Método que retorna o handler.
	 * 
	 * @return ExceptionHandler
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler result = parent.getExceptionHandler();
		result = new ApplicationExceptionHandler(result);
		return result;
	}

}