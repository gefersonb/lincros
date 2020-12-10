package com.pack.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date toDate(String data, String formato) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		return formatter.parse(data);
	}

	public static String toString(Date data, String formato) {
		DateFormat df = new SimpleDateFormat(formato);
		return df.format(data);
	}

}
