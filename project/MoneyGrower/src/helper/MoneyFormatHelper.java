package helper;

import java.text.DecimalFormat;

public class MoneyFormatHelper {
	public static String format(Long value, String unit) {
		DecimalFormat formatter = new DecimalFormat("#,###");
		String output = formatter.format(value) + " " + unit;
		return output;
	}

	public static String format(Long value) {
		DecimalFormat formatter = new DecimalFormat("#,###");
		String output = formatter.format(value);
		return output;
	}

	public static String format(Long value, boolean prefix) {
		DecimalFormat formatter = new DecimalFormat("#,###");
		String output = formatter.format(value);
		if (prefix && value > 0) {
			output = "+" + output;
		}
		return output;
	}

	public static Long fromString(String str) {
		String[] tokens = str.split(" ")[0].split(",");
		StringBuilder sb = new StringBuilder();
		for (String token : tokens) {
			sb.append(token);
		}
		Long output = Long.parseLong(sb.toString());
		return output;
	}
}
