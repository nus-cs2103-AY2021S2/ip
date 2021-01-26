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
        return list;
    }

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
     * return description of todo */
    public String parseAddToDo(String commandText) throws DukeException {
        String[] command = commandText.split(" ", 2);
        if(command.length == 1 || command[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else{
            return command[1];
        }
    }

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

    public boolean isValidTime(String time) throws DukeException {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-d H:mm", Locale.ENGLISH);
            inputFormat.parse(time);
        } catch (DateTimeParseException e){
            return false;
        }
        return true;
    }


    public String extractOperator(String userInput){
        String[] parts = userInput.split(" ", 2);
        return parts[0];
    }

    public String extractDescription(String userInput) {
        String[] parts = userInput.split(" ", 2);
        return parts[1];
    }
}
