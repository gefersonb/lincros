package com.pack.util;

/**
 * Classe utilitária para realizar a validação de CPF ou CNPJ com ou sem máscara.
 * @author Geferson Buzzello
 * 
 */
public class CpfCnpjUtils {
	
	private static boolean isValidCPF(String cpf){
		
		if(cpf == null || cpf.isEmpty()){
			return false;
		}
		
		int priVer;
		int segVer;
		
		// Ajustada rotina de validação do CPF para tratar tanto 
		// número formatado quanto não formatado. 
		String cpfVerificar = null;
		String digito = null;
		// CPF inválido
		if (cpf.length() < 11) {
			return false;
		// CPF formatado
		} else if (cpf.length() > 11) {
			cpfVerificar = cpf.substring(0,11);
			digito = cpf.substring(12, 14);
			cpfVerificar = cpfVerificar.replaceAll("[^0-9]", "");
		// CPF não formatado
		}else {
			cpfVerificar = cpf.substring(0,9);
			digito = cpf.substring(9, 11);
		}

		char[] auxCpf = cpfVerificar.toCharArray();  
		
		int[] valPri = {10,9,8,7,6,5,4,3,2};
		int[] valSeg = {11,10,9,8,7,6,5,4,3,2};	
		
		int i = 0;
		
		Double sum = 0d;		
		for (char c : auxCpf) {
			sum += (double) (valPri[i] *  Character.getNumericValue(c)); 
			i++;
		}		
		if((sum % 11) < 2){
			priVer = 0;
		}else{
			priVer = (int) (11 - (sum % 11));
		}
		
		//Adiciona o digito verificador para calculo do segundo digito
		cpfVerificar = cpfVerificar + priVer;
		auxCpf = cpfVerificar.toCharArray();		
		
		//Reseta a somatória e calcula o segundo digito.
		sum = 0d;
		i = 0;
		for (char c : auxCpf) {
			sum += (double) (valSeg[i] *  Character.getNumericValue(c));
			i++;
		}		
		if((sum % 11) < 2){
			segVer = 0;
		}else{
			segVer = (int) (11 - (sum % 11));
		}	
		
		return digito.equals(String.valueOf(priVer) + String.valueOf(segVer));
	}
	
	private static boolean isValidCNPJ(String cnpj) {
		
		if(cnpj == null || cnpj.isEmpty()){
			return false;
		}
		
		int soma = 0, dig;
		String cnpj_calc = cnpj.substring(0, 12);

		if (cnpj.length() != 14)
			return false;

		char[] chr_cnpj = cnpj.toCharArray();

		/* Primeira parte */
		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);

		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		/* Segunda parte */
		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		return cnpj.equals(cnpj_calc);
	}	
	
	/**
	 * Verifica se o número do CPF ou CNPJ é valido
	 * @param String número do CPF CNPJ
	 * @return true | false
	 */	
	public static boolean isValid(String cpfOrCnpj){
		if(cpfOrCnpj == null || cpfOrCnpj.isEmpty()){
			return false;
		}
		String cpfcnpj = cpfOrCnpj.replace(".", "").replace("-", "").replace("/", "").trim();
		double validaZero = Double.parseDouble(cpfcnpj);
		if(validaZero==0 ){
			return false;
		}
		if(cpfcnpj.length() > 11){
			return isValidCNPJ(cpfcnpj);
		}else{
			return isValidCPF(cpfcnpj);
		}
	}
	
}