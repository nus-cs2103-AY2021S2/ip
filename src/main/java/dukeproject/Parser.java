package dukeproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser to make sense of string inputs.
 */
public class Parser {
    public enum CommandType {
        INPUT_DEADLINE,
        INPUT_EVENT,
        FILE_DEADLINE,
        FILE_EVENT
    }

    /**
     *
     * Returns an object which contains the description and date of a task.
     *
     * @param fullCommand The input that the parser will try to make sense of.
     * @param cmdType The command type which determines how we try to interpret the data.
     * @return An object which contains the description and date of a task.
     */
    public StringDatePair parse(String fullCommand, CommandType cmdType) {
        if (cmdType == CommandType.INPUT_DEADLINE) {
            // Split up the string into the description and date accordingly
            String[] userInputValues = fullCommand.substring(9).split("/by ");
            String description = userInputValues[0];

            // Specific the date format that our system will accept and save it in the by variable
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime by = LocalDateTime.parse(userInputValues[1], dateFormatter);

            // Return the description and date pair
            return new StringDatePair(description, by);
        } else if (cmdType == CommandType.INPUT_EVENT) {
            // Split up the string into the description and date accordingly
            String[] userInputValues = fullCommand.substring(6).split("/at ");
            String description = userInputValues[0];

            // Specific the date format that our system will accept and save it in the by variable
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime at = LocalDateTime.parse(userInputValues[1], dateFormatter);

            // Return the description and date pair
            return new StringDatePair(description, at);
        } else if (cmdType == CommandType.FILE_DEADLINE) {
            // Split up the string into the description and date accordingly
            String[] taskInputValues = fullCommand.substring(7).split(" \\(by: ");
            String description = taskInputValues[0];

            // Specific the date format that our system will accept and save it in the by variable
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy - HHmm");
            LocalDateTime by = LocalDateTime.parse(
                taskInputValues[1].substring(0, taskInputValues[1].length() - 1),
                dateFormatter);

            // Return the description and date pair
            return new StringDatePair(description, by);
        } else if (cmdType == CommandType.FILE_EVENT) {
            // Split up the string into the description and date accordingly
            String[] taskInputValues = fullCommand.substring(7).split(" \\(at: ");
            String description = taskInputValues[0];

            // Specific the date format that our system will accept and save it in the by variable
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy - HHmm");
            LocalDateTime at = LocalDateTime.parse(
                taskInputValues[1].substring(0, taskInputValues[1].length() - 1),
                dateFormatter);

            // Return the description and date pair
            return new StringDatePair(description, at);
        }
        return null;
    }

    /**
     * Parse a find command.
     * @param fullCommand Command / input for find.
     * @return The keyword for the find command.
     */
    public String parseForFind(String fullCommand) {
        return fullCommand.substring(5);
    }
}
