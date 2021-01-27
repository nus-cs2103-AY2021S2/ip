package Duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAndTime {

	public static final String converter(String date) {

		if (isDashFormat(date)) {
			try {
				LocalDate d1 = LocalDate.parse(date.trim());
				return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
			} catch (DateTimeParseException e) {
				return "";
			}
		} else {
			return "";
		}
	}


	public static final boolean isDashFormat(String date) {
		String[] dashFormat = date.toLowerCase().split("-", 3);
		for (String s : dashFormat) {
			if (!isNumeric(s.trim())) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
