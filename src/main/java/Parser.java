import java.text.ParseException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    /**
     * A class to parse input String for more information of what type of event it is.
     */

    /**
     * finds date and time within the string
     * @param input
     * @return
     */


    public static String extractDate(String input){
        String regex = "\\d+[-]\\d+[-]\\d+";
        Pattern datePattern  = Pattern.compile(regex);
        Matcher m = datePattern.matcher(input);
        if(m.find()){
            return m.group(0);
        } else {
            return "";
        }
    }

    public static LocalDate parseDate (String input) {
        String regex = "(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})"; // format YYYY-MM-DD
        Pattern dateFormat = Pattern.compile(regex);
        Matcher m = dateFormat.matcher(input);
        m.find();
        String day = m.group("day");
        String month = m.group("month");
        String year = m.group( "year");
        return LocalDate.parse(year + '-' + month + '-' + day);
    }

    public static class ParseException extends Exception {
        ParseException(String message){
            super(message);
        }
    }

}
