package duke.utils;

import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateAndTime {


	public static String converter(String date) {

		if (isDashFormat(date)) {
			try {
				LocalDate d1 = LocalDate.parse(date.trim());
				return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
			} catch (DateTimeParseException e) {
				return Ui.WRONG_DATE_FORMAT;
			}
		} else {
			return Ui.WRONG_DATE_FORMAT;
		}
	}


	private static boolean isDashFormat(String date) {
		String[] dashFormat = date.toLowerCase().split("-", 3);
		for (String s : dashFormat) {
			if (!isNumeric(s.trim())) {
				return false;
			}
		}
		return true;
	}

	private static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
