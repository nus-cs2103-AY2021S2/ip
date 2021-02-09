package datetime;

import java.util.HashMap;

// todo maybe kiwi date time should be the only one who calls this class... or should it be the oher way... but how to parse two parts
/**
 * Parses user-inputted date/time arguments into KiwiDateTime objects.
 */
class ParseKiwiDateTime {

    HashMap<String, Integer> dateDelimiters = new HashMap<>();
    HashMap<String, Integer> timeDelimiters = new HashMap<>(); // time delimiter might not always be used; also might have whitesapces

    public void initDelimiters() {
        dateDelimiters.put("-", 1);
        dateDelimiters.put("/", 1);
        timeDelimiters.put(":", 1);
        timeDelimiters.put(".", 1);
    }

    public ParseKiwiDateTime() {
        initDelimiters();
    }
    /*
    Strings to parse...
    nn/nn haa
    nn/n haa
    n/nn haa
    n/n haa
    So should split the whitespace and parseTimeString and parseDateString

    timeStrings:
    hh:mm aa
    h:mm aa
    hhaa
    haa
    without aa --> assume 24h timing
    h
    hh
    hh:mm

    how about no whitespace? how to know time string or date string? - contain date-delimiters or time-delimiters2 "/"
    haa
    hhaa
     */

    // entry function
    // what if you want to allow date time or time date
    public String parseStringToDateTime(String input) {
        boolean hasBothDateAndTime = input.contains(" ");

        if (!hasBothDateAndTime) {
            determineDateOrTime(input);
        }

        int idx = input.indexOf(" ");
        String str1 = input.substring(0, idx);
        String str2 = input.substring(idx + 1);

        // assume str1 is date and str2 is time for now
        String d = findDateDelimiter(str1);
        String t = findTimeDelimiter(str2);


        // random return statement
        return d;
    }

    private KiwiDateTime parseDateAndTimeStrings(String dateStr, String dDelimiter, String timeStr, String tDelimiter) {
        String[] dateParams = dateStr.split(dDelimiter);
        String[] timeParams = timeStr.split(tDelimiter);

        if (dateParams.length > 3) {
            // throw unsupp format
        } else if (timeParams.length > 2) {
            // throw unsupp format
        }

        //KiwiDateTime.of(dateParams, timeParams);
        return KiwiDateTime.ofThisYear(21, 4);
    }

    /**
     *
     * @param input
     * @return delimiter found in string, empty string if none matched
     */
    private String findDateDelimiter(String input) {
        for (String d : dateDelimiters.keySet()) {
            if (input.contains(d)) {
                return d;
            }
        }
        return "";
    }
    /**
     *
     * @param input
     * @return delimiter found in string, empty string if none matched
     */
    private String findTimeDelimiter(String input) {
        for (String d : timeDelimiters.keySet()) {
            if (input.contains(d)) {
                return d;
            }
        }
        return "";
    }

    private String determineDateOrTime(String input) {
        String d1 = findDateDelimiter(input);
        String d2 = findTimeDelimiter(input);

        if (!d1.isEmpty()) {
            // parseStringToDateParams();
        } else if (!d2.isEmpty()) {
            // parseStringToTimeParams();
        } else if (d1.isEmpty() && d2.isEmpty()) {
            // throw new UnsupportedFormatException
            // but time may not have delimitters
        }
        return "yes"; // fixme random return statement
    }

    public static void main(String[] args) {
        // focus on the simple case first
        ParseKiwiDateTime p = new ParseKiwiDateTime();

    }
}
