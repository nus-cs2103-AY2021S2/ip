package datetime;

import java.util.HashMap;

// todo maybe kiwi date time should be the only one who calls this class... or should it be the oher way... but how to parse two parts
/**
 * Parses user-inputted date/time arguments into KiwiDateTime objects.
 */
class ParseKiwiDateTime {

    // not allowed: ' '
    HashMap<String, Integer> dateDelimiters = new HashMap<>();
    HashMap<String, Integer> timeDelimiters = new HashMap<>(); // time delimiter might not always be used; also might have whitesapces

    // can't remember what this entire class was doing before
    private String timeDelimiter;
    private String dateDelimiter;


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
    hh aa
    hh:mm aa
     */

    // entry function
    // what if you want to allow date time or time date
    public KiwiDateTime parse(String input) {
        String[] inputs = input.split(" ");
        if (inputs.length == 3) {
            // parse3InputStrs(inputs);
        } else if (inputs.length == 2) {
            // parse2InputStrs(inputs);
        }
//        return null;
        boolean hasBothDateAndTime = input.contains(" ");

        if (!hasBothDateAndTime) {
            // determineDateOrTime(input);
        }

        int idx = input.indexOf(" ");
        String str1 = input.substring(0, idx);
        String str2 = input.substring(idx + 1);

        // assume str1 is date and str2 is time for now
        String d = findDateDelimiter(str1);
        String t = findTimeDelimiter(str2);

        return parseDateAndTimeStrings(str1, d, str2, t);

        // random return statement
//        return d;
    }

    /*
    private void parse3InputStrs(String[] inputs) {
        if (inputs.isAmPm(inputs[1])) { // inputs are: time AM/PM date
            parseTimeString(inputs[0], inputs[1]);
            parseDateString(inputs[2]);
        } else if (inputs.isAmPm(inputs[2])) { // inputs are: date time AM/PM
            parseDateString(inputs[0]);
            parseTimeString(inputs[1], inputs[2]);
        }
    }

    private void parse2InputStrs(String[] inputs) {
        if (inputs.isAmPm(inputs[1])) { // inputs are: time AM/PM
            parseTimeString(inputs[0], inputs[1]);
            parseDateString(inputs[2]);
        }

        // valid inputs are either: date 24hTime or 24hTime date
        if (isDateString(inputs[0]) && is24hTimeString(inputs[1])) {
            parseDateString(inputs[0]);
            parseTimeString(inputs[1]);
        } else if (isDateString(inputs[1]) && is24hTimeString(inputs[0])) {
            parseDateString(inputs[1]);
            parseTimeString(inputs[0]);
        } else {
            // throw unsupported argument exception
        }
    }

     */
    private KiwiDateTime parseDateAndTimeStrings(String dateStr, String dDelimiter, String timeStr, String tDelimiter) {
        String[] dateParams = dateStr.split(dDelimiter);
        String[] timeParams = timeStr.split(tDelimiter);

        if (dateParams.length > 3) {
            // throw unsupp format
        } else if (timeParams.length > 2) {
            // throw unsupp format
        }

        //KiwiDateTime.of(dateParams, timeParams);
        return KiwiDateTime.ofThisYear(Integer.parseInt(dateParams[0]),
                Integer.parseInt(dateParams[1]),
                Integer.parseInt(timeParams[0]),
                Integer.parseInt(timeParams[1]));
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

//    private String determineDateOrTime(String input) {
//        String d1 = findDateDelimiter(input);
//        String d2 = findTimeDelimiter(input);
//
//        if (!d1.isEmpty()) {
//            // parseStringToDateParams();
//        } else if (!d2.isEmpty()) {
//            // parseStringToTimeParams();
//        } else if (d1.isEmpty() && d2.isEmpty()) {
//            // throw new UnsupportedFormatException
//            // but time may not have delimitters
//        }
//        return "yes"; // fixme random return statement
//    }

    /**
     * Returns true if formatted with a recognized date delimiter
     * @param input
     * @return
     */
    private boolean isDateString(String input) {
        String d = findDateDelimiter(input);
        return !d.isEmpty();
    }

    private boolean is24hTimeString(String input) {
        String d = findTimeDelimiter(input);
        return !d.isEmpty();
    }

    static void print(Object... objects) {
        for (Object o : objects) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        // focus on the simple case first
        // todo datetime throws a lot of exceptions
        ParseKiwiDateTime p = new ParseKiwiDateTime();
        print(
                p.parse("20-05 11:44"),
                p.parse("30-11 15:01"),
                p.parse("6/4 1:22")
                //p.parse("1/2 9:40PM")
                //p.parse("1/2 9:40 PM")
                //p.parse("6/4 1")

//                p.isDateString("6/4"),
//                p.isDateString("6:40"),
//                p.is24hTimeString("6:40"),
//                p.is24hTimeString("06/05")
        );

    }
}
