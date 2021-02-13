package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import duke.task.Task;

/**
 * Parser class used to parse input into appropriate formats.
 * Contains static methods.
 */
public class Parser {
    /**
     * Parse given line into an array where the first element is a keyword.
     * Will be the first to be called to parse user input to get the keyword used.
     * @param string Input Line.
     */
    public static String[] parseInitial(String string) {
        String[] inputArray = string.split(" ", 2);
        return inputArray;
    }

    /**
     * Parses given string according to the deadline specifications.
     * @param string String representation of deadline description and by date.
     * @return A String array length 2, inputArray[0] is description, inputArray[1] is deadline date
     */
    public static String[] parseDeadline(String string) throws IllegalArgumentException {
        String[] inputArray = string.split("/by", 2);
        if (inputArray[0].isBlank()) {
            throw new IllegalArgumentException("Error: Description of deadline cannot be empty");
        } else if (inputArray.length == 1) {
            throw new IllegalArgumentException("Error: Please add a time limit to your deadline");
        } else {
            assert inputArray.length == 2 : "Length of inputArray for parsing Deadline should be 2.";
            return inputArray;
        }
    }

    /**
     * Parses given string according to the event specifications.
     * @param string string representation of event description and at date.
     * @return A String array length 2, inputArray[0] is description, inputArray[1] is event date
     */
    public static String[] parseEvent(String string) throws IllegalArgumentException {
        String[] inputArray = string.split("/at", 2);
        if (inputArray[0].isBlank()) {
            throw new IllegalArgumentException("Error: Description of event cannot be empty");
        } else if (inputArray.length == 1) {
            throw new IllegalArgumentException("Error: Please add a start time to your event");
        } else {
            assert inputArray.length == 2 : "Length of inputArray for parsing Deadline should be 2.";
            return inputArray;
        }
    }

    /**
     * Parses given string according to the the priority setting specifications.
     * @param string string representation of task index and priority it is changing into.
     * @return A String array length 2, inputArray[0] is the task index, inputArray[1] is the new priority.
     * @throws IllegalArgumentException
     */
    public static String[] parseSetPriority(String string) throws IllegalArgumentException {
        String[] inputArray = string.trim().split(" ", 2);
        if (inputArray[0].isBlank()) {
            throw new IllegalArgumentException("Error: Arguments should not be empty.");
        } else if (inputArray.length == 1) {
            throw new IllegalArgumentException("Error: Missing arguments found. priority [taskNumber] [0-2]");
        } else {
            assert inputArray.length == 2 : "Length of inputArray for parsing SetPriority should be 2.";
            return inputArray;
        }
    }
    /**
     * Parses the given list of tasks into a string representation.
     * @param taskList The list of Tasks needed to turn to string.
     * @return String representation of the entire list of tasks.
     */
    public static String listTaskToString(List<Task> taskList) {
        String content = "";
        Integer count = 1;
        for (Task t: taskList) {
            content += count.toString() + ".";
            content += t.toString();
            content += "\n";
            count++;
        }
        return content.trim();
    }

    /**
     * Attempts to parse the given string into a defined date format if the appropriate format is found.
     * Currently only supports the format date, "yyyy-mm-dd".
     * @param string string representing the time of task
     * @return properly formatted string.
     */
    public static String parseDate(String string) {
        try {
            LocalDate date = LocalDate.parse(string);
            return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (DateTimeParseException e) {
            return string;
        }
    }
}
