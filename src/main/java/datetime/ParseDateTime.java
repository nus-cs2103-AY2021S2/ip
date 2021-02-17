package datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

// is datetime.ParseDateTime getting called from the wrong place?
// should it be called from parser or the task subclasses?
// todo naming this. DateTimeParser > current name
/**
 * A class that helps parse user input strings into date time objects for relevant tasks
 */
public class ParseDateTime {
    // LIST OF SUPPORTED DATES
    // dd-MM ha i.e. "30-04 6PM"

    /**
     * Parses user input into a dateTimeObject by calling the relevant formatting function.
     * @param s User input to parse
     * @return A LocalDateTime object
     */
    public static LocalDateTime parse(String s) {
        // format YYYY-MM-DD
        // todo exceptions/error
        // todo check length and see which format to use
        return parseFormat1(s);
    }

    // for saving minimal format back to list?
    // maybe once there are more parsing formats, should create a CustomDateTime object that
    // stores what format we're using after parsing once, so that don't need to keep parsing so much
    // upon every save to harddisk and loading

    /**
     * Formats a date time object as a string to save into hard disk
     * @param d date time object to stringify
     * @return String representation for hard disk
     */
    public static String unparse(LocalDateTime d) {
        return unparseFormat1(d);
    }

    private static String unparseFormat1(LocalDateTime d) {
        String patt = "dd-MM ha yyyy";
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern(patt);
        String str = f2.format(d);
        return str.substring(0, patt.length() - 4);
    }

    // where s is formatted like "30-04 6PM"
    // todo as suggested in PR, make parsing format names more descriptive
    private static LocalDateTime parseFormat1(String s) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM ha yyyy");
        int yyyy = Year.now().getValue();
        LocalDateTime d = LocalDateTime.parse(s + " " + yyyy, f);
        return d;
    }

    /**
     * Formats date time into a readable string so that /list command is readable
     * @param d date time object
     * @return a user-friendly string
     */
    public static String readableString(LocalDateTime d) {
        String patt = "dd MMM ha yyyy";
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern(patt);
        String str = f2.format(d);
        return str.substring(0, patt.length() - 4);
    }

//    @Override
//    public String toString() {
//        return readableString()
//    }

    /**
     * This method only exists for one-off testing of this class.
     * @param args
     */
    public static void main(String[] args) {
        LocalDate d3 = LocalDate.parse("2019-12-02");
        System.out.println(d3);

        // parser for dd-MM ha
        // time currently is hour only, and PM needs to be in caps
        // f2 and d2 work together to enable user to key in particular formats
        String test2 = "30-04 6PM";
        int yyyy2 = Year.now().getValue();
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd-MM ha yyyy");
        LocalDateTime d2 = LocalDateTime.parse(test2 + " " + yyyy2, f2);
        System.out.println(d2);
    }



    public static boolean isOnlyTimeString(String input) {
        if (input.contains(" ")) {
            int spaceIdx = input.indexOf(" ");
            // there is only an AM/PM after the space
            // so before the space there is only time value
            // the entire string is a time value
            return spaceIdx == input.length() - 3;
        } else if (true){ // check if got AM/PM

        } else if (true) { // check if got time delimiters i.e. they might have used 24 hour formatters

        }

        return false;
    }

}
