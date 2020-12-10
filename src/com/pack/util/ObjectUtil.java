package com.pack.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectUtil {

	private static ObjectUtil instance;
	public static ObjectUtil getInstance() {
		if (instance == null) {
			instance = new ObjectUtil();
		}
		return instance;
	}

	public static String toStringTrim(Object value) {
		if (value != null) {
			return value.toString().trim();
		}
		return "";
	}

	public static Long longValueOf(Object value) {
		if (value != null && value instanceof String) {
			return Long.valueOf(value.toString());
		}
		return null;
	}

	public static Long longValueOf(BigDecimal value) {
		if (value != null) {
			return value.longValue();
		}
		return null;
	}

	public static BigDecimal bigDecimalValueOf(Object value) {
		if (value != null) {
			return new BigDecimal(toStringTrim(value));
		}
		return null;
	}

	public static Timestamp timesStampValueOf(String pattern, Object value) {
		if (value != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			Date parsedDate = null;
			try {
				parsedDate = dateFormat.parse(toStringTrim(value));
			} catch (ParseException e) {
				return null;
			}
			return new java.sql.Timestamp(parsedDate.getTime());
		}
		return null;
	}

	public static boolean isDigit(String str) {
		if (!StringUtils.isNullOrEmpty(str)) {
			String pattern = "\\D";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(str.trim().replaceAll("[,.]", ""));
			if (m.find()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNumber(String str) {
		if (StringUtils.isNullOrEmpty(str) || ObjectUtil.isDigit(str)) {
			return false;
		}
		try {
			NumberFormat.getNumberInstance(new Locale("pt", "BR")).parse(str.trim());
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	public static boolean objectIsInteger(Object value){
		try {
			Integer.parseInt(value.toString());
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
}