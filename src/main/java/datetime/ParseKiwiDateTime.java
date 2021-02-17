package datetime;

import exceptions.MissingArgumentException;

import java.util.HashMap;

// todo maybe kiwi date time should be the only one who calls this class... or should it be the oher way... but how to parse two parts
/**
 * Parses user-inputted date/time arguments into KiwiDateTime objects.
 */
public class ParseKiwiDateTime {

    // not allowed: ' '
    HashMap<String, Integer> dateDelimiters = new HashMap<>();
    HashMap<String, Integer> timeDelimiters = new HashMap<>();

    // can't remember what this entire class was doing before

    private int hour;
    private int min;
    private int day;
    private int month;
    private int year;

    public void initDelimiters() {
        dateDelimiters.put("-", 1);
        dateDelimiters.put("/", 1);
        timeDelimiters.put(":", 1);
        timeDelimiters.put(".", 1); // bugs because split(regex) interprets . as a regex symbol
    }

    public ParseKiwiDateTime() {
        initDelimiters();
    }

    // entry function
    // what if you want to allow date time or time date
    public KiwiDateTime parse(String input) throws MissingArgumentException {
        resetVars();
        
        String[] inputs = input.split(" ");
        
        if (inputs.length == 3) {
             parse3InputStrs(inputs);
        } else if (inputs.length == 2) {
             parse2InputStrs(inputs);
        } else if (inputs.length == 1) {
            // parse1InputStr(inputs[0]);
            throw new MissingArgumentException("Missing date or time.");
        }
        return createKiwiDateTimeObj();
    }

    // THESE ARE FOR DEALING WITH INPUTS THAT HAVE NO SPACES
//    private void parse1InputStr(String input) {
//        if (isDateString(input)) {
//            parseDateString(input);
//        } else if (isTimeString(input)) {
//
//        }
//    }
//
//    // ha or h:ma or
//    private boolean isTimeString(String input) {
//        String s = input.toLowerCase();
//        int x = s.indexOf("am");
//        int y = s.indexOf("pm");
//        if (x == -1 && y == -1) {
//
//        }
//    }

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


    // two of the three spaced input strings can be connected, hence this function is created
    private void parse3InputStrs(String[] inputs) {
        if (isAmPm(inputs[1])) { // inputs are: time AM/PM date
            parse12hTimeString(inputs[0], inputs[1]);
            parseDateString(inputs[2]);
        } else if (isAmPm(inputs[2])) { // inputs are: date time AM/PM
            parseDateString(inputs[0]);
            parse12hTimeString(inputs[1], inputs[2]);
        }
    }

    // the two spaced input strings can be connected, hence this function is created
    private void parse2InputStrs(String[] inputs) {
        if (isAmPm(inputs[1])) { // inputs are: time AM/PM
            parse12hTimeString(inputs[0], inputs[1]);
        }

        // valid inputs are either: date 24hTime or 24hTime date or date 12hUnspacedtime or reverse
        if (false) {

        } else if (isDateString(inputs[0]) && isUnspaced12hTimeString(inputs[1])) { // note 12h time string check needs to come before 24h
            parseDateString(inputs[0]);
            parseUnspaced12hTimeString(inputs[1]);

        } else if (isDateString(inputs[1]) && isUnspaced12hTimeString(inputs[0])) { // note 12h time string check needs to come before 24h
            parseDateString(inputs[1]);
            parseUnspaced12hTimeString(inputs[0]);

        } else if (isDateString(inputs[0]) && is24hTimeString(inputs[1])) {
            parseDateString(inputs[0]);
            parse24hTimeString(inputs[1]);
        } else if (isDateString(inputs[1]) && is24hTimeString(inputs[0])) {
            parseDateString(inputs[1]);
            parse24hTimeString(inputs[0]);

        } else {
            // throw unsupported argument exception
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

    private void parse12hTimeString(String input, String amPm) {
        // parse24hTimeString(input); // fixme what if got no hour

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

    // why unspaced, because parsing function already split all the spaces
    // ha or h:ma
    private boolean isUnspaced12hTimeString(String input) {
        return input.toLowerCase().contains("am") || input.toLowerCase().contains("pm");
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

    private boolean isHourOnly(String input) {
        String d = findTimeDelimiter(input);
        return d.isEmpty();
    }

    static void print(Object... objects) {
        for (Object o : objects) {
            System.out.println(o);
        }
    }

    void params() {
        String str = String.format("h:%d m:%d d:%d m:%d", this.hour, this.min, this.day, this.month);
        print(str);
    }

    public static void main(String[] args) {
        // focus on the simple case first
        // todo datetime throws a lot of exceptions
        ParseKiwiDateTime p = new ParseKiwiDateTime();

         p.testParse(
//                 "2:00 pm",
//                 "18/11 6 pm",
//                 "18/11 6:11 pm",
//                 "6:11 pm 18/11",
//                 "7 pm 18/11",

                 "18/11/31 6 pm",

                 "6 pm",
                 "11:59 pm",
                 "13/4 13:00",
                 "6:00 1/7",

                 "2/3 11pm",
                 "2/3 11:49pm",
                 // "6:11pm 8/11", // fixme
                 "7pm 18/1" // fixme
                 // todo check if in creation, time and date are being set to none instead of 12am
         );

         // unsupported
        /*
        "4/5",
        "14:39", // fixme
         "2:31", // fixme
         */

//        testParse("13/4 6pm");
    }

    private static void testParse(String... strings) {

        ParseKiwiDateTime p = new ParseKiwiDateTime(); // todo rename kiwidatetimeparser

        for (String s : strings) {
            //print(p.parse(s));
        }
    }
}
