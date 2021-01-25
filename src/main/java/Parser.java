import java.time.LocalDate;

// DEALS WITH MAKING SENSE OF USER'S COMMAND
public class Parser {

    static String parseCommandType(String command) {
        String[] tokenizedCommand = command.split(" ");
        return tokenizedCommand[0];
    }

    static int parseTaskIndex(String command) {
        String[] tokenizedCommand = command.split(" ");
        return Integer.parseInt(tokenizedCommand[1]);
    }

    static String parseTodoDescription(String command) {
        return command.substring(5);
    }

    static String parseDeadlineDescription(String command) {
        return command.substring(9).split(" /by ")[0];
    }

    static LocalDate parseDeadlineDate(String command) {
        return LocalDate.parse(command.substring(9).split(" /by ")[1]);
    }

    static String parseEventDescription(String command) {
        return command.substring(6).split(" /at ")[0];
    }

    static String parseEventDate(String command) {
        return command.substring(6).split(" /at ")[1];
    }

}
