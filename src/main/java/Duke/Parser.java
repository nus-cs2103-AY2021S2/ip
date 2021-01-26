package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public enum commandType {
        INPUT_DEADLINE,
        INPUT_EVENT,
        FILE_DEADLINE,
        FILE_EVENT
    }
    
    public StringDatePair parse(String fullCommand, commandType cmdType) {
        if (cmdType == commandType.INPUT_DEADLINE) {
            // Split up the string into the description and date accordingly
            String[] userInputValues = fullCommand.substring(9).split("/by ");
            String description = userInputValues[0];

            // Specific the date format that our system will accept and save it in the by variable
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime by = LocalDateTime.parse(userInputValues[1], dateFormatter);

            // Return the description and date pair
            return new StringDatePair(description, by);
        } else if (cmdType == commandType.INPUT_EVENT) {
            // Split up the string into the description and date accordingly
            String[] userInputValues = fullCommand.substring(6).split("/at ");
            String description = userInputValues[0];
            
            // Specific the date format that our system will accept and save it in the by variable
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime at = LocalDateTime.parse(userInputValues[1], dateFormatter);

            // Return the description and date pair
            return new StringDatePair(description, at);
        } else if (cmdType == commandType.FILE_DEADLINE) {
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
        } else if (cmdType == commandType.FILE_EVENT) {
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
}
