package duke;

import duke.task.Task;

import java.util.List;

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
    public static String[] parseDeadline(String string) throws IllegalArgumentException{
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
    public static String[] parseEvent(String string) throws IllegalArgumentException{
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
}
