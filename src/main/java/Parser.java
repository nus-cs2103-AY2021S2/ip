import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

public class Parser {
    private ArrayList<String> validOperators;
    private static final String timeFormat = "yyyy-M-d H:mm";

    public Parser(){
        this.validOperators = initialiseOperators();
    }

    private ArrayList<String> initialiseOperators(){
        ArrayList<String> list = new ArrayList<>();
        list.add("bye");
        list.add("list");
        list.add("todo");
        list.add("deadline");
        list.add("event");
        list.add("done");
        list.add("delete");
        list.add("find");
        return list;
    }

    /**
     * Parse the operator input by user.
     *
     * @param userInput full text command input by user.
     * @return Extracted operator.
     * @throws DukeException If operator is not valid.
     */
    public String parseOperator(String userInput) throws DukeException {
        String[] parts = userInput.split(" ", 2);
        String operator = parts[0];
        if (!isValidOperator(operator)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return parts[0];
    }

    private boolean isValidOperator(String operator){
        return validOperators.contains(operator.toLowerCase());
    }

    private String[] parseCommand(String userInput){
        String[] command = userInput.split(" ", 2);
        return command;
}

    /**
     * Return the task number to be marked as done.
     *
     * @param commandText full text command input by user.
     * @return Task number.
     * @throws DukeException If task number is empty or a non-integer is entered.
     */
    public int parseDone(String commandText) throws DukeException{
        String[] command = commandText.split(" ", 2);
        if (command.length == 1) {
            throw new DukeException("OOPS!!! Please specify the task number.");
        } else {
            try {
                int taskNo = Integer.valueOf(command[1]);
                return taskNo;
            } catch (Exception e) {
                throw new DukeException("OOPS!!! The task cannot be found.");
            }
        }
    }

    /**
     * Return the task number to be deleted.
     *
     * @param commandText full text command input by user.
     * @return Task number.
     * @throws DukeException If task number is empty or a non-integer is entered.
     */
    public int parseDelete(String commandText) throws DukeException {
        String[] command = commandText.split(" ", 2);
        if (command.length == 1) {
            throw new DukeException("OOPS!!! Please specify the task number.");
        } else {
            try {
                int taskNo = Integer.valueOf(command[1]);
                return taskNo;
            } catch (Exception e) {
                throw new DukeException("OOPS!!! The task cannot be found.");
            }
        }
    }

    /**
     * Return the description of a ToDo task to be added specified by user.
     *
     * @param commandText full text command input by user.
     * @return Description of ToDo.
     * @throws DukeException If description is empty.
     */
    public String parseAddToDo(String commandText) throws DukeException {
        String[] command = commandText.split(" ", 2);
        if(command.length == 1 || command[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else{
            return command[1];
        }
    }

    /**
     * Return the description and due time of a Deadline task to be added specified by user.
     *
     * @param commandText full text command input by user.
     * @return List containing description and due time of Deadline.
     * @throws DukeException If description is empty or due time does not follow 'yyyy-M-d H:mm' format.
     */
    public String[] parseAddDeadline(String commandText) throws DukeException {
        String[] command = commandText.split(" ", 2);
        if(command.length == 1 || command[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else{
            String[] details = command[1].split(" /by ", 2);
            if (details.length == 1 || !isValidTime(details[1])){
                throw new DukeException("OOPS!! Please follow the correct data/time format: yyyy-M-d H:mm");
            }
            String description = details[0];
            String by = details[1];
            return new String[] {description, by};
        }
    }

    /**
     * Return the description and time of an Event task to be added specified by user.
     *
     * @param commandText full text command input by user.
     * @return List containing description and time of event.
     * @throws DukeException If description is empty or time does not follow 'yyyy-M-d H:mm' format.
     */
    public String[] parseAddEvent(String commandText) throws DukeException {
        String[] command = commandText.split(" ", 2);
        if(command.length == 1 || command[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else{
            String[] details = command[1].split(" /at ", 2);

            if (details.length == 1 || !isValidTime(details[1])){
                throw new DukeException("OOPS!! Please follow the correct data/time format: yyyy-MM-dd HH:mm");
            }
            String description = details[0];
            String at = details[1];
            return new String[] {description, at};
        }
    }

    /**
     * Return the keyword specified by user.
     *
     * @param commandText full text command input by user.
     * @return Keyword to be used for finding matching tasks.
     * @throws DukeException If keyword is empty.
     */
    public String parseFindTask(String commandText) throws DukeException {
        String[] command = commandText.split(" ", 2);
        if(command.length == 1 || command[1].isBlank()) {
            throw new DukeException("OOPS!!! There is no matching task.");
        } else{
            return command[1];
        }
    }

    /**
     * Return if the time entered by user is valid.
     *
     * @param time Time of Deadline or Event.
     * @return True if the time follows "yyyy-M-d H:mm"; else, return false.
     * @throws DateTimeParseException If time is in invalid format.
     */
    public boolean isValidTime(String time) throws DateTimeParseException {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-d H:mm", Locale.ENGLISH);
            inputFormat.parse(time);
        } catch (DateTimeParseException e){
            return false;
        }
        return true;
    }
}
