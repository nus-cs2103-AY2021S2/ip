import java.util.Arrays;
import java.util.List;

public class Validation {

    public static void checkValidCommand(String command) throws DukeException {
        String[] validCommands = new String[]{"todo", "deadline", "event", "list", "bye", "done", "delete"};
        List<String> commands = Arrays.asList(validCommands);
        int index = command.indexOf(' ');
        String first;
        if (index > -1) {
            first = command.substring(0, index);
        } else {
            first = command;
        }
        if(!commands.contains(first.toLowerCase())){
            throw new DukeException(":( OOPS! I'm sorry, but I don't know what that means :-(");
        } else {
            String[] secondValidation = new String[]{"todo", "deadline", "event", "done", "delete"};
            List<String> secondListOfCommands = Arrays.asList(secondValidation);
            if (secondListOfCommands.contains(first.toLowerCase())) {
                if (index <= -1 || command.substring(index).isBlank()) {
                    throw new DukeException(":( OOPS! The description of a todo/deadline/event/done/delete " +
                            "cannot be empty!!");
                }
            }
        }
    }

    public static void checkForSchedule(String command, int findSlash) throws DukeException {
        if ((findSlash <= -1) || command.endsWith("/") || command.substring(findSlash + 1).isBlank()) {
            throw new DukeException(":( OOPS! Please input a valid time/date");
        }
    }

    public static void checkValidRange(int taskSize, int chosenNumber) throws DukeException {
        if(chosenNumber > taskSize) {
            throw new DukeException(":( OOPS! This task does not exist! Use 'list' to check your task numbers!");
        }
    }
}
