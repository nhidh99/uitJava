package helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

public class DateFormatHelper {
	static String pattern = "dd/MM/yyyy";

	public static StringConverter<LocalDate> getDatePickerFormatter() {
		return new StringConverter<LocalDate>() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public LocalDate fromString(String dateString) {
				if (dateString == null)
					return null;
				return LocalDate.parse(dateString, formatter);
			}

			@Override
			public String toString(LocalDate localDate) {
				if (localDate == null)
					return "";
				return formatter.format(localDate);
			}
		};
	}
	
	public static String fromLocalDate(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static LocalDate fromString(String str) {
		return LocalDate.parse(str, DateTimeFormatter.ofPattern(pattern));
	}
}
