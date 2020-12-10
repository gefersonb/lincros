package com.pack.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public final class MessageBundle {

	private static final ResourceBundle messages = ResourceBundle
			.getBundle(WebConstants.MESSAGE_BUNDLE);

	/**
	 * Recupera o valor para a chave informada do arquivo de mensagens da
	 * aplicação
	 * 
	 * @param Chave
	 *            para recuperar o valor
	 * @return Valor da chave informada
	 */
	public static String getMessage(String key) {
		try {
			return messages.getString(key);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Recupera o valor para a chave informada do arquivo de mensagens da
	 * aplicação
	 * 
	 * @param Chave
	 *            para recuperar o valor
	 * @param Lista
	 *            de parâmetros para substituição na mensagens
	 * @return Valor da chave informada
	 */
	public static String getMessage(String key, Object... parameters) {
		String message = getMessage(key);
		MessageFormat formatter = new MessageFormat(message);
		return formatter.format(parameters);
	}

}