package datetime;

import java.util.HashMap;

// todo maybe kiwi date time should be the only one who calls this class... or should it be the oher way... but how to parse two parts
/**
 * Parses user-inputted date/time arguments into KiwiDateTime objects.
 */
class ParseKiwiDateTime {

    // not allowed: ' '
    HashMap<String, Integer> dateDelimiters = new HashMap<>();
    HashMap<String, Integer> timeDelimiters = new HashMap<>();

    // can't remember what this entire class was doing before
    private String timeDelimiter;
    private String dateDelimiter;

    private int hour;
    private int min;
    private int day;
    private int month;
    private int year;


    public void initDelimiters() {
        dateDelimiters.put("-", 1);
        dateDelimiters.put("/", 1);
        timeDelimiters.put(":", 1);
        timeDelimiters.put(".", 1);
    }

    public ParseKiwiDateTime() {
        initDelimiters();
    }

    // entry function
    // what if you want to allow date time or time date
    public KiwiDateTime parse(String input) {
        resetVars();
        
        String[] inputs = input.split(" ");
        
        if (inputs.length == 3) {
             parse3InputStrs(inputs);
        } else if (inputs.length == 2) {
             parse2InputStrs(inputs);
        } else if (inputs.length == 1) {
            // parse1InputStr(inputs[0]);
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
        return KiwiDateTime.ofThisYear(this.day, this.month, this.hour, this.min);
    }


    private void parse3InputStrs(String[] inputs) {
        if (isAmPm(inputs[1])) { // inputs are: time AM/PM date
            parseTimeString(inputs[0], inputs[1]);
            parseDateString(inputs[2]);
        } else if (isAmPm(inputs[2])) { // inputs are: date time AM/PM
            parseDateString(inputs[0]);
            parseTimeString(inputs[1], inputs[2]);
        }
    }

    private void parse2InputStrs(String[] inputs) {
        if (isAmPm(inputs[1])) { // inputs are: time AM/PM
            parseTimeString(inputs[0], inputs[1]);
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

    private boolean isAmPm(String input) {
        return input.equalsIgnoreCase("am") || input.equalsIgnoreCase("pm");
    }

    /**
     * Is only for 24h timestring
     * @param input
     */
    private void parseTimeString(String input) {
        // assume input is of format hourDelimiterMinute
        String t = findTimeDelimiter(input);
        String[] parts = input.split(t);

        this.hour = Integer.parseInt(parts[0]);
        this.min = Integer.parseInt(parts[1]);
    }

    private void parseTimeString(String input, String amPm) {
        parseTimeString(input);

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
//        print(
//                p.parse("20-05 11:44"),
//                p.parse("30-11 15:01"),
//                p.parse("6/4 1:22"),
//                //p.parse("1/2 9:40PM")
//                //p.parse("1/2 9:40 PM")
//                //p.parse("6/4 1")
//
//                p.isDateString("6/4"),
//                p.isDateString("6:40"),
//                p.is24hTimeString("6:40"),
//                p.is24hTimeString("06/05")
//        );
//
//        p.parseTimeString("6:40");
//        print(p.min, p.hour);
//
//        p.parseTimeString("11:50");
//        print(p.min, p.hour);
//
//        p.parseTimeString("11:50", "PM");
//        print(p.min, p.hour);
//
//        p.parseDateString("13/4");
//
//        p.params();

//        String s1 = "13/4 6:00";
//        KiwiDateTime k = p.parse(s1);
//        print(k);

//        String s1 = "13/4 13:00";
//        KiwiDateTime k = p.parse(s1);
//        print(k);
//
//        s1 = "13/4 14:10";
//        k = p.parse(s1);
//        print(k);
//
//        s1 = "2:00 pm";
//        k = p.parse(s1);
//        print(k);

//        testParse("2.00pm", "2pm", "3 pm", "12am", "11:59pm");
    }

    private static void testParse(String... strings) {

        ParseKiwiDateTime p = new ParseKiwiDateTime(); // todo rename kiwidatetimeparser

        for (String s : strings) {
            print(p.parse(s));
        }
    }
}
