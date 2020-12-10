package com.pack.exceptions;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.jboss.logging.Logger;

import com.pack.util.WebConstants;

/**
 * Classe reponsável por controlar os IDs das Views para serem recuperados
 * quando a sessão terminar.
 * 
 * @author Geferson Buzzello
 */
public class ApplicationExceptionHandler extends ExceptionHandlerWrapper {

	private static Logger log = Logger
			.getLogger(ApplicationExceptionHandler.class);

	private ExceptionHandler wrapped;

	/**
	 * Construtor padrão
	 * 
	 * @param GenericExceptionHandler
	 */
	public ApplicationExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	/**
	 * Método responsável por retornar um ExceptionHandler.
	 * 
	 * @return ExceptionHandler
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	/**
	 * Método responsável por guardar os IDs das Views.
	 */
	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents()
				.iterator(); i.hasNext();) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext exceptionContext = (ExceptionQueuedEventContext) event
					.getSource();
			Throwable t = exceptionContext.getException();
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				String error_page = WebConstants.ERROR_PAGE;
				if (t instanceof ViewExpiredException) {
					error_page = WebConstants.VIEW_EXPIRED_PAGE;
				}
				log.error("Generic error ocurred: ", t);
				context.getApplication().getNavigationHandler()
						.handleNavigation(context, null, error_page);
				context.renderResponse();
			} finally {
				i.remove();
			}
		}
		getWrapped().handle();
	}

}