import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    /**
     * A class containing methods to parse input String for more information of what type of event it is.
     */

    /**
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


}
