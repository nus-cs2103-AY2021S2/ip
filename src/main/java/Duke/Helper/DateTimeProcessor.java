package Duke.Helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Duke.Constant.Constants;

/**
 * A helper class that converted a time string to a more meaningful version.
 */
public class DateTimeProcessor {
    private final String fullTimeString;

    /**
     * The constructor of DateTimeProcessor class has 1 parameter:
     * The string containing the date (and time) data that needs to be converted.
     * @param fullTimeString A string containing the date (and time) data.
     */
    public DateTimeProcessor(String fullTimeString) {
        this.fullTimeString = fullTimeString;
    }

    private String processTime(String time) {
        try {
            if (time.isEmpty()) {
                return "";
            } else if (time.length() != 3 && time.length() != 4) {
                return null;
            }
            int timeInt = Integer.parseInt(time);
            if (timeInt < 0) {
                return null;
            }
            int hour = timeInt / 100;
            int minute = timeInt % 100;
            if (hour == 24) {
                return (minute == 0) ? "12:00am" : null;
            } else if (hour > 24 || minute > 59) {
                return null;
            }
            String type = (hour >= 12) ? "pm" : "am";
            hour = hour % 12;
            if (hour == 0) {
                hour = 12;
            }
            String padding = (minute < 10) ? "0" : "";
            return hour + ":" + padding + minute + type;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String processDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            return null;
        }
    }

    private String[] processInput() {
        try {
            String date = "";
            String time = "";
            String[] info = fullTimeString.trim().split(" ");
            for (String s : info) {
                if (date.isEmpty()) {
                    date = s;
                } else {
                    time = s;
                    break;
                }
            }
            return new String[]{date, time};
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get a meaningful version of date and time string of the input, for example: Jan 5 2021 or May 15 2020 2:30pm.
     * @return A meaningful version of date and time string of the input.
     */
    public String getFullDateTime() {
        String[] array = processInput();
        if (array == null) {
            return Constants.INVALID_DATETIME_FORMAT_SHORT;
        }
        String date = processDate(array[0]);
        String time = processTime(array[1]);
        if (date == null || time == null) {
            return Constants.INVALID_DATETIME_FORMAT_SHORT;
        }
        return (time.isEmpty()) ? date : date + " " + time;
    }
}
