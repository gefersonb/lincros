package com.pack.util;

import org.jboss.logging.Logger;

public final class AppUtils {

	private static Logger logger = Logger.getLogger(AppUtils.class);

	/**
	 * Recupera o valor da propriedade tratando a mesma como do tipo Int
	 * 
	 * @param Nome
	 *            da propriedade
	 * @return Valor da propriedade como inteiro
	 */
	public static int getPropertyAsInt(String property) {
		int result = 0;
		try {
			result = Integer.parseInt(getPropertyAsString(property));
		} catch (Exception e) {
			logger.warn("Erro recuperar o valor da propridade " + property
					+ ". Assumido valor padrão.", e);
		}
		return result;
	}

	/**
	 * Recupera o valor da propriedade
	 * 
	 * @param Nome
	 *            da propriedade
	 * @return Valor da propriedade
	 */
	public static String getPropertyAsString(String property) {
		return ResourceUtils.getInstance().getProperty(property);
	}

	/**
	 * Recupera o valor da propriedade tratando a mesma como do tipo Boolean
	 * 
	 * @param Nome
	 *            da propriedade
	 * @return Valor da propriedade como inteiro
	 */
	public static boolean getPropertyAsBoolean(String property) {
		boolean result = false;
		try {
			result = Boolean.parseBoolean(getPropertyAsString(property));
		} catch (Exception e) {
			logger.warn("Erro recuperar o valor da propridade " + property
					+ ". Assumido valor padrão.", e);
		}
		return result;
	}

}