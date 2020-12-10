package com.pack.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Singleton para recuperar arquivos de propriedade da aplicação
 * 
 * @author Geferson Buzzello
 * @version 1.0.0
 *
 */
public final class ResourceUtils {

	private static ResourceUtils INSTANCE;

	private Properties application;
	private Properties messages;
	private Properties sql;

	private Properties loadPropertyFile(String filename) {
		Properties props = new Properties();
		try {
			props.load(ResourceUtils.class.getClassLoader()
					.getResourceAsStream(filename));
		} catch (IOException e) {
			throw new ExceptionInInitializerError(
					"Impossível inicializar a aplicação! Arquivo " + filename
							+ " não foi encontrado na pasta de configurações.");
		}
		return props;
	}

	private void loadSystemProperties() {
		String systemPropertyes = this
				.getProperty(Constants.JBOSS_SYSTEM_PROPERTIES);
		if (application != null && !StringUtils.isNullOrEmpty(systemPropertyes)) {
			for (String property : systemPropertyes.split(",")) {
				String key = property.trim();
				String value = System.getProperty(key);
				if (!StringUtils.isNullOrEmpty(value)) {
					this.application.put(key, value);
				}
			}
		}
	}

	private ResourceUtils() {
		application = loadPropertyFile(Constants.APPLICATION_PROPERTY_FILE);
		loadSystemProperties();
		messages = loadPropertyFile(Constants.MESSAGE_PROPERTY_FILE);
		sql = loadPropertyFile(Constants.SQL_PROPERTY_FILE);
	}

	/**
	 * Recupera uma a instância da classe de recursos da aplicação
	 * 
	 * @return
	 */
	public static ResourceUtils getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ResourceUtils();
		}
		return INSTANCE;
	}

	/**
	 * Recupera o valor para a chave informada do arquivo de configurações da
	 * aplicação
	 * 
	 * @param Chave
	 *            para recuperar o valor
	 * @return Valor da chave informada
	 */
	public String getProperty(String key) {
		String value = application.getProperty(key);
		// Se não existir no arquivo de propriedades, busca nas propriedades do
		// sistema
		if (StringUtils.isNullOrEmpty(value)) {
			return System.getProperty(key);
		}
		return value;
	}

	/**
	 * Recupera o valor para a chave informada do arquivo de mensagens da
	 * aplicação
	 * 
	 * @param Chave
	 *            para recuperar o valor
	 * @return Valor da chave informada
	 */
	public String getMessage(String key) {
		return messages.getProperty(key);
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
	public String getMessage(String key, Object... parameters) {
		String message = this.getMessage(key);
		MessageFormat formatter = new MessageFormat(message);
		return formatter.format(parameters);
	}

	/**
	 * Recupera o valor para a chave informada do arquivo de sql da aplicação
	 * 
	 * @param Chave
	 *            para recuperar o valor
	 * @return Valor da chave informada
	 */
	public String getStatement(String key) {
		return sql.getProperty(key);
	}

}