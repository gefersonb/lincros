package com.pack.util;

public class WebConstants extends Constants {

	// Nome da variável de sessão que mantem o usuário conectado
	public static final String USER = "usuario";

	// Usados para navegação implícita do JSF
	public static final String MAIN_PAGE = "principal";
	public static final String ERROR_PAGE = "error";
	public static final String VIEW_EXPIRED_PAGE = "view-expired";
	public static final String ACCESS_DENIED_PAGE = "access-denied";

	// Identifica o contexto que classifica as páginas como seguras
	public static final String SECURED = "secured";
	
	public static final String NAO_INFORMADO = "Não Informado";

	// URL da página de login para redirecionamento quando usuário não
	// autenticado
	public static final String LOGIN_PAGE = "login-page";

	// Define as URL's do contexto seguro que são livres, ou seja, não precisam
	// ser autorizadas
	public static final String URLS_LIVRES = "urls-livres";

	// Message bundle para apresentação e customização de mensagens do JSF
	public static final String MESSAGE_BUNDLE = "com.pack.resources.messages";

	// Nome do objeto da sessão que armazena a página requisitada antes da
	// autenticação
	public static final String ORIGINAL_REQUESTED_URI = "original-requested-uri";

	// Caminho onde estão as páginas de erro da aplicação no contexto público
	public static final String ERROR_DIRECTORY = "/pages/public/error/";
	// ###

	// Mensagem de credenciais inválidas exibida na autenticação quando
	// mal-sucedida
	public static final String INVALID_CREDENTIALS = "login.invalid_credentials";

}