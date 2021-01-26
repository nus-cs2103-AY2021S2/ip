
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    /**
     * A class containing methods to parse input String for more information of what type of event it is.
     */

    /**
<<<<<<< HEAD
     * initializes the record of tasks from a text file containing saved data since the last time the application was opened.
     */


    public static Task parseTaskFromStoredFormat(String input){
        String[] fields = input.split(" \\| ");
        String commandCode = fields[0];
        Task parsedTask;
        switch(commandCode) {
        case("T"):
            parsedTask = new ToDo(fields[2]);
            break;
        case("D"):
            parsedTask = new Deadline(fields[2],fields[3]);
            break;
        case("E"):
            parsedTask = new Event(fields[2],fields[3]);
            break;
            //default case throw a ParseError to be defined later...
        default:
            parsedTask = null;
        }
        boolean isDone = (Integer.parseInt(fields[1]) == 1);
        if (isDone && (parsedTask != null)) {
            parsedTask.markAsDone();
        }
        return parsedTask;
    }

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
        String regex = "(?<year>\\d{4})-(?<month>\\d{1,2})-(?<day>\\d{1,2})"; // format YYYY-MM-DD
        Pattern dateFormat = Pattern.compile(regex);
        Matcher m = dateFormat.matcher(input);
        m.find();
        String day = String.format("%02d", Integer.parseInt(m.group("day")));
        String month = String.format("%02d", Integer.parseInt(m.group("month")));
        String year = m.group("year");
        return LocalDate.parse(year + '-' + month + '-' + day);
    }

    public static class ParseException extends Exception {
        ParseException(String message){
            super(message);
        }
    }


}
