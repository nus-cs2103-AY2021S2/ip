package duke.utils;

import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class process the date key in by user and convert it to the right format to be use.
 */
public class DateAndTime {

	/**
	 * This method convert a yyyy-mm-dd format of date to MMM d yyyy format.
	 *
	 * @param date the String representation of date.
	 * @return MMM d yyyy format of date and return error message if the given date is in wrong format.
	 */
	public static final String converter(String date) {

		if (isDashFormat(date)) {
			try {
				LocalDate d1 = LocalDate.parse(date.trim());
				return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
			} catch (DateTimeParseException e) {
				return Ui.WRONGDATEFORMAT;
			}
		} else {
			return Ui.WRONGDATEFORMAT;
		}
	}


	private static final boolean isDashFormat(String date) {
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
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
