package com.pack.util;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária para manipulação e tratamento de Strings.
 * 
 * @author Geferson Buzzello
 * 
 */
public final class StringUtils {

	public static final String EMPTY = " ";
	public static final String ZERO = "0";
	public static final String BREAK_LINE = "\n";

	/**
	 * Retira acentuação de qualquer string
	 * 
	 * @param Valor
	 *            com acentuação
	 * @return Valor sem acentuação
	 */
	public static String normalize(String str) {
		String result = "";
		if (!isNullOrEmpty(str)) {
			result = Normalizer.normalize(str.trim(), Normalizer.Form.NFD);
			result = result.replaceAll("[^\\p{ASCII}]", "");
		}
		return result.trim();
	}

	/**
	 * Verifica se a string informada é nula ou vazia.
	 * 
	 * @param String
	 * @return true | false
	 */
	public static boolean isNullOrEmpty(String str) {
		return !(str instanceof String) || str.trim().isEmpty();
	}

	/**
	 * Transforma a primeira letra de um texto em minuscula
	 * 
	 * @param Texto
	 *            a transformar
	 * @return Texto transformado
	 */
	public static String lowerCaseFirstLetter(String texto) {
		return texto.substring(0, 1).toLowerCase() + texto.substring(1);
	}

	/**
	 * Adiciona máscara ao valor informado
	 * 
	 * @param Formato
	 *            de máscara
	 * @param Valor
	 * @param Caracter
	 *            representado pela máscara
	 * @return Valor formatado
	 */
	public static String format(String format, String value, char c) {
		if (format == null || format.trim().equals(""))
			return null;
		int index = 0;
		StringBuilder s = new StringBuilder();
		char charsFormat[] = format.toCharArray();
		char charsValue[] = value.toCharArray();
		char arr$[] = charsFormat;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			char element = arr$[i$];
			if (element != c)
				s.append(element);
			else
				s.append(charsValue[index++]);
		}

		return s.toString();
	}

	/**
	 * Formata o valor informado de acordo com a máscara
	 * 
	 * @param Formato
	 *            definido com o uso de #
	 * @param Valor
	 *            a formatar
	 * @return Valor formatado
	 */
	public static String format(String format, String value) {
		return format(format, value, '#');
	}

	/**
	 * Remove a formatação do campo
	 * 
	 * @param Format
	 *            definido com o uso de #
	 * @param Valor
	 *            formatado
	 * @return Valor sem formatação
	 */
	public static String unformat(final String format, final String value) {
		return StringUtils.unformat(format, value, '#');
	}

	/**
	 * Remove a formatação do campo
	 * 
	 * @param Format
	 *            definido com o uso de #
	 * @param Valor
	 *            formatado
	 * @param Caracter
	 *            representado pela máscara
	 * @return Valor sem formatação
	 */
	public static String unformat(final String format, final String value, final char c) {

		if (format == null || format.trim().equals(""))
			return null;

		if (value == null || value.trim().equals(""))
			return null;

		StringBuilder s = new StringBuilder();

		char[] charsFormat = format.toCharArray();
		char[] charsValue = value.toCharArray();

		for (int i = 0; i < charsFormat.length; i++) {
			if (charsFormat[i] == c) {
				s.append(charsValue[i]);
			}
		}

		return s.toString();
	}

	/**
	 * Remove formato de máscara de campos CPF, CNPJ, Telefone, CEP Remove
	 * qualquer máscara que contenha: . / -
	 * 
	 * @param Valor
	 *            a ser removida máscara
	 * @return Valor sem máscara
	 */
	public static String unformat(final String value) {
		return value.replace(".", "").replace("/", "").replace("-", "");
	}

	/**
	 * Converte um array de strings em uma lista, removendo espaços em branco
	 * dos valores do array.
	 * 
	 * @param Array
	 *            de valores
	 * @return Lista de valores
	 */
	public static List<String> asList(String[] values) {
		List<String> lista = new ArrayList<String>();
		if (values != null && values.length > 0) {
			for (String s : values) {
				lista.add(s.trim());
			}
		}
		return lista;
	}

	/**
	 * Converte uma String com conteúdo separado por ',' em uma lista, removendo
	 * espaços em branco dos valores do array que compõe a string.
	 * 
	 * @param String
	 *            com valores separados por ,
	 * @return Lista de valores
	 */
	public static List<String> asList(String values) {
		if (!StringUtils.isNullOrEmpty(values)) {
			return asList(values.split(","));
		}
		return new ArrayList<String>();
	}

	/**
	 * Retorna o número divisor de uma operação aritmética de acordo com a
	 * precisão informada. Ex.: precisão igual a 2, retorna 100. Precisão igual
	 * a 3 retorna 1000.
	 * 
	 * @param Número
	 *            da precisão para divisão de valor real
	 * @return Retorna o divisor em unidade de dezena, centena milhar, de acordo
	 *         com a precisão informada
	 */
	public static long precision(Integer precision) {
		return Long.parseLong(String.format("%-" + (precision + 1) + "s", "1").replace(' ', '0'));
	}

	/**
	 * Preenche a string a esquerda com a quantidade informada no filler.
	 * 
	 * @param valor
	 *            a ser preenchido
	 * @param tamanho
	 *            de caracteres a ser adicionado
	 * @param caracter
	 *            utilizado para preenchimento
	 * @return valor preenchido a esquerda
	 */
	public static String lpad(String valueToPad, int size, String filler) {
		if (StringUtils.isNullOrEmpty(valueToPad)) {
			valueToPad = "";
		}
		StringBuilder builder = new StringBuilder();
		while (builder.length() + valueToPad.length() < size) {
			builder.append(filler);
		}
		builder.append(valueToPad);
		return builder.toString();
	}

	/**
	 * Preenche a string a direita com a quantidade informada no filler.
	 * 
	 * @param valor
	 *            a ser preenchido
	 * @param tamanho
	 *            de caracteres a ser adicionado
	 * @param caracter
	 *            utilizado para preenchimento
	 * @return valor preenchido a direita
	 */
	public static String rpad(String valueToPad, int size, String filler) {
		if (StringUtils.isNullOrEmpty(valueToPad)) {
			valueToPad = "";
		}
		StringBuilder builder = new StringBuilder(valueToPad);
		while (builder.length() + valueToPad.length() < size + valueToPad.length()) {
			builder.append(filler);
		}
		return builder.toString();
	}

	public static String getNewString(String string, int startIndex, int count) {
		StringBuffer str = new StringBuffer();
		int countLimit = 0;
		int index = 0;
		for (char ch : string.toCharArray()) {
			if (index >= startIndex) {
				str.append(ch);
				countLimit++;
				if (countLimit == count) {
					break;
				}
			}
			index++;
		}
		return str.toString();
	}

	public static String formatDate(String date) {
		String[] dataParts = date.split(" ");
		return dataParts != null ? dataParts[0].trim() : null;
	}
	
	public static String obterDataNoPrimeiroDiaBimestreByString(int ano, int bimestre){
		String mes;
		switch (bimestre) {
			case 1:
				mes  = "01";
				break;
			case 2:
				mes  = "03";
				break;
			case 3:
				mes  = "05";
				break;
			case 4:
				mes  = "07";
				break;
			case 5:
				mes  = "09";
				break;
			default:
				mes  = "11";
				break;
		}
		
		return "01/" + String.valueOf(mes) + "/" + String.valueOf(ano);
	}

	public static java.util.Date obterDataNoUltimoDiaBimestre(int ano, int bimestre) throws ParseException{
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		data.setLenient(false);
		String retorno = obterDataNoUltimoDiaBimestreByString(ano, bimestre);
		return  (java.util.Date) data.parse(retorno);
	}
	
	public static String obterDataNoUltimoDiaBimestreByString(int ano, int bimestre){
		String dia;
		String mes;
		switch (bimestre) {
			case 1:
				if (isAnoBissexto(ano)){
					dia  = "29";
				} else {
					dia  = "28";
				}
				mes = "02";
				break;
			case 2:
				dia  = "30";
				mes = "04";
				break;
			case 3:
				dia  = "30";
				mes = "06";
				break;
			case 4:
				dia  = "31";
				mes = "08";
				break;
			case 5:
				dia  = "31";
				mes = "10";
				break;
			default:
				dia  = "31";
				mes = "12";
				break;
		}
		
		String ultimoDia =  dia + "/" + mes + "/" + String.valueOf(ano);
		return ultimoDia;
	}
	
	public static boolean isAnoBissexto(int ano) {
        if (( ano % 4 == 0 && ano % 100 != 0 ) || ( ano % 400 == 0 )){
            return true;
        }
        else{
            return false;
        }
	}
	
	public static String getXmlEscape(String xmlInformation){
		return xmlInformation.replace("&", "&amp;").replace(">", "&gt;").replace("<", "&lt;").replace("\"", "&quot;").replace("'", "&apos;");
	}
	
}