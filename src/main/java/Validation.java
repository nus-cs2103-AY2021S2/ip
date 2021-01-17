import java.util.Arrays;
import java.util.List;

public class Validation {

    public void checkValidCommand(String command) throws DukeException {
        String[] validCommands = new String[]{"todo", "deadline", "event", "list", "bye", "done", "delete"};
        List<String> list = Arrays.asList(validCommands);
        int index = command.indexOf(' ');
        String first;
        if (index > -1) {
            first = command.substring(0, index);
        } else {
            first = command;
        }
        if(!list.contains(first.toLowerCase())){
            throw new DukeException(":( OOPS! I'm sorry, but I don't know what that means :-(");
        } else {
            String[] secondValidation = new String[]{"todo", "deadline", "event", "done", "delete"};
            List<String> secondList = Arrays.asList(secondValidation);
            if (secondList.contains(first.toLowerCase())) {
                if(index <= -1 || command.substring(index).isBlank()) {
                    throw new DukeException(":( OOPS! The description of a todo/deadline/event/done/delete cannot be empty!!");
                }
            }
        }
    }

    public void checkForSchedule(String command, int findSlash) throws DukeException {
        if ((findSlash <= -1) || command.endsWith("/") || command.substring(findSlash + 1).isBlank()) {
            throw new DukeException(":( OOPS! Please input a valid time/date");
        }
    }
}
