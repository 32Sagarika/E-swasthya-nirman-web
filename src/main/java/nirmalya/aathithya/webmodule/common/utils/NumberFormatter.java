package nirmalya.aathithya.webmodule.common.utils;

import java.text.DecimalFormat;

public class NumberFormatter {

public static String doubleToStringWithComma(Double value) {
		
		String doubleValue = "";
		
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		
		if(value != null) {
			doubleValue = decimalFormat.format((double) value);
		}
		
		return doubleValue;
	}
}
