package datetime;

import java.util.HashMap;

import exceptions.MissingArgumentException;


/**
 * Parses user-inputted date/time arguments into KiwiDateTime objects.
 * KiwiDateTime is a wrapper class for storing LocalDateTime in this kiwi app.
 */
public class ParseKiwiDateTime {

    // not allowed: ' '
    private HashMap<String, Integer> dateDelimiters = new HashMap<>();
    private HashMap<String, Integer> timeDelimiters = new HashMap<>();

    private int hour;
    private int min;
    private int day;
    private int month;
    private int year;

    final String MISSING_ARG_ERR_MSG = "Missing date or time.";

    public ParseKiwiDateTime() {
        initDelimiters();
    }

    /**
     * Initialise all recognized delimiters for the Kiwi app
     */
    public void initDelimiters() {
        dateDelimiters.put("-", 1);
        dateDelimiters.put("/", 1);
        timeDelimiters.put(":", 1);
    }

    /**
     * Parses a user input string into a KiwiDateTime object. Is the main driver of this class.
     *
     * @param input
     * @return
     * @throws MissingArgumentException
     */
    public KiwiDateTime parse(String input) throws MissingArgumentException {
        resetVars();

        String[] inputs = input.split(" ");

        if (inputs.length == 3) {
            parse3InputStrs(inputs);
        } else if (inputs.length == 2) {
            parse2InputStrs(inputs);
        } else if (inputs.length == 1) {
            throw new MissingArgumentException(MISSING_ARG_ERR_MSG);
        }
        return createKiwiDateTimeObj();
    }

    private void resetVars() {
        this.day = 0;
        this.month = 0;
        this.hour = 0;
        this.min = 0;
        this.year = 0;
    }

    private KiwiDateTime createKiwiDateTimeObj() {
        return KiwiDateTime.of(this.day, this.month, this.year, this.hour, this.min);
    }

    /**
     * Parses three input strings that were delimited by a space by the user. This exists because
     * 2 of the three spaced strings describe time and need to be parsed together.
     *
     * @param inputs
     */
    private void parse3InputStrs(String[] inputs) throws MissingArgumentException {
        if (isAmPm(inputs[1])) { // inputs are: time AM/PM date
            parse12hTimeString(inputs[0], inputs[1]);
            parseDateString(inputs[2]);
        } else if (isAmPm(inputs[2])) { // inputs are: date time AM/PM
            parseDateString(inputs[0]);
            parse12hTimeString(inputs[1], inputs[2]);
        } else {
            throw new MissingArgumentException(MISSING_ARG_ERR_MSG);
        }
    }

    /**
     * Parses two input strings that were delimited by a space by the user. This exists because
     * the two spaced strings may both describe time and need to be parsed together.
     *
     * @param inputs
     */
    private void parse2InputStrs(String[] inputs) throws MissingArgumentException {
        if (isAmPm(inputs[1])) { // inputs are: time AM/PM
            parse12hTimeString(inputs[0], inputs[1]);
        }

        // check if one input string is a date and the other is either a 12h or 24h time input
        if (isDateString(inputs[0]) && isUnspaced12hTimeString(inputs[1])) {
            parseDateString(inputs[0]);
            parseUnspaced12hTimeString(inputs[1]);

        } else if (isDateString(inputs[1]) && isUnspaced12hTimeString(inputs[0])) {
            parseDateString(inputs[1]);
            parseUnspaced12hTimeString(inputs[0]);

        } else if (isDateString(inputs[0]) && is24hTimeString(inputs[1])) {
            parseDateString(inputs[0]);
            parse24hTimeString(inputs[1]);

        } else if (isDateString(inputs[1]) && is24hTimeString(inputs[0])) {
            parseDateString(inputs[1]);
            parse24hTimeString(inputs[0]);

        } else {
            throw new MissingArgumentException(MISSING_ARG_ERR_MSG);
        }
    }

    private boolean isDateAnd24hTimeString(String str1, String str2) {
        return isDateString(str1) && isUnspaced12hTimeString(str2);
    }

    private boolean isAmPm(String input) {
        return input.equalsIgnoreCase("am") || input.equalsIgnoreCase("pm");
    }

    /**
     * Is only for 24h timestring
     *
     * @param input
     */
    private void parse24hTimeString(String input) {
        // assume input is of format hourDelimiterMinute
        String t = findTimeDelimiter(input);
        String[] parts = input.split(t);

        this.hour = Integer.parseInt(parts[0]);
        this.min = Integer.parseInt(parts[1]);
    }

    private void parseUnspaced12hTimeString(String input) {
        int indexOfAmOrPm = Math.max(input.toLowerCase().indexOf("am"), input.toLowerCase().indexOf("pm"));

        parse12hTimeString(
                input.substring(0, indexOfAmOrPm).trim(),
                input.substring(indexOfAmOrPm).trim()
        );
    }

    /**
     * Initialises this.hour and this.minute from two strings that make up a valid 12h format.
     *
     * @param input
     * @param amPm
     */
    private void parse12hTimeString(String input, String amPm) {
        // input string may contain a single integer to represent the hour, or the input string is
        // essentially a 24h time string excluding the "am" or "pm"
        if (isHourOnly(input)) {
            this.hour = Integer.parseInt(input);
        } else {
            parse24hTimeString(input);
        }

        assert isAmPm(amPm);

        if (amPm.equalsIgnoreCase("pm")) {
            this.hour += 12;
        }
    }

    /**
     * Initialises this.day, this.month and this.year based on a valid input string.
     *
     * @param input
     */
    private void parseDateString(String input) {
        String d = findDateDelimiter(input);
        String[] parts = input.split(d);

        // assumption: day comes before month
        this.day = Integer.parseInt(parts[0]);
        this.month = Integer.parseInt(parts[1]);

        if (parts.length == 3) {
            this.year = Integer.parseInt(parts[2]);
        }
    }

    /**
     * Detects if a string containing no spaces contains a 12h time format containing the phrase 'am' or 'pm'.
     *
     * @param input
     * @return
     */
    private boolean isUnspaced12hTimeString(String input) {
        return input.toLowerCase().contains("am") || input.toLowerCase().contains("pm");
    }


    /**
     * Finds a recognized date delimiter in the string provided
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
     * Finds a recognized time delimiter in the string provided
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

    /**
     * Returns true if formatted with a recognized date delimiter
     *
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

    private boolean isHourOnly(String input) {
        String d = findTimeDelimiter(input);
        return d.isEmpty();
    }
}
