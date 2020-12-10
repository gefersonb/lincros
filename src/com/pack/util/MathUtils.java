package com.pack.util;

public class MathUtils {

	public static double truncate(double value, int casas_decimais) 
	{
		int var1 = (int) value;   
		double var2 = var1 * Math.pow(10, casas_decimais); 
		double var3 = (value - var1) * Math.pow(10, casas_decimais); 
		int var4 = (int) var3; 
		int var5 = (int) var2; 
		int resultado = var5 + var4; 
		double resultado_final = resultado/Math.pow(10, casas_decimais); 
		
		return resultado_final;  
	}
}
