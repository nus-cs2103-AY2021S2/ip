package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    /**
     * A class containing static methods to parse input String for more information of what type of event it is.
     * Initialise a Parser that accepts in a String input Command, which it can Parse and determine the appropriate CommandType
     * to issue to the caller.
     *
     */

    private String inputCommand;

    Parser(String inputCommand) {
        this.inputCommand = inputCommand;
    }



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


    // Methods for extracting dates and formatting dates.


    /**
     * finds date within the string
     * @param input
     * @return The sustring containing the date only
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return LocalDate.parse(input,dateTimeFormatter);
    }

    public static class ParseException extends Exception {
        ParseException(String message){
            super(message);
        }
    }


}
