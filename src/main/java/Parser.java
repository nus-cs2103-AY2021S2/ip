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
        ArrayList<String> operators = new ArrayList<>();
        // add valid operators into list
        operators .add("bye");
        operators .add("list");
        operators .add("todo");
        operators .add("deadline");
        operators .add("event");
        operators .add("done");
        operators .add("delete");
        operators .add("find");
        return operators ;
    }

    public String parseOperator(String userInput) throws DukeException {
        // split command text by its first space into 2 parts
        String[] commandParts = userInput.split(" ", 2);

        // parse operator in the first word of a text command
        String operator = commandParts[0];

        if (!isValidOperator(operator)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return operator;
    }

    private boolean isValidOperator(String operator){
        return validOperators.contains(operator.toLowerCase());
    }

    private String[] parseCommand(String userInput){
        String[] commandParts = userInput.split(" ", 2);
        return commandParts;
}

    public int parseDone(String commandText) throws DukeException{
        // split command text by its first space into 2 parts
        String[] commandParts = commandText.split(" ", 2);

        // parse task number
        if (commandParts.length == 1) {
            throw new DukeException("OOPS!!! Please specify the task number.");
        } else {
            try {
                int taskNo = Integer.valueOf(commandParts[1]);
                return taskNo;
            } catch (Exception e) {
                throw new DukeException("OOPS!!! The task cannot be found.");
            }
        }
    }

    public int parseDelete(String commandText) throws DukeException {
        // split command text by its first space into 2 parts
        String[] commandParts = commandText.split(" ", 2);

        // parse task number
        if (commandParts.length == 1) {
            throw new DukeException("OOPS!!! Please specify the task number.");
        } else {
            try {
                int taskNo = Integer.valueOf(commandParts[1]);
                return taskNo;
            } catch (Exception e) {
                throw new DukeException("OOPS!!! The task cannot be found.");
            }
        }
    }

    /**
     * return description of todo */
    public String parseAddToDo(String commandText) throws DukeException {
        // split command text by its first space into 2 parts
        String[] commandParts = commandText.split(" ", 2);

        // parse description of ToDo
        if(commandParts.length == 1 || commandParts[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else{
            String description = commandParts[1];
            return description;
        }
    }

    public String[] parseAddDeadline(String commandText) throws DukeException {
        // split command text by its first space into 2 parts
        String[] commandParts = commandText.split(" ", 2);

        // parse details of Deadline
        if(commandParts.length == 1 || commandParts[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else{

            // split details to description and time
            String[] details = commandParts[1].split(" /by ", 2);
            if (details.length == 1 || !isValidTime(details[1])){
                throw new DukeException("OOPS!! Please follow the correct data/time format: yyyy-M-d H:mm");
            }

            String description = details[0];
            String by = details[1];
            return new String[] {description, by};
        }
    }

    public String[] parseAddEvent(String commandText) throws DukeException {
        // split command text by its first space into 2 parts
        String[] commandParts = commandText.split(" ", 2);

        // parse details of Event
        if(commandParts.length == 1 || commandParts[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else{

            // split details to description and time
            String[] details = commandParts[1].split(" /at ", 2);
            if (details.length == 1 || !isValidTime(details[1])){
                throw new DukeException("OOPS!! Please follow the correct data/time format: yyyy-MM-dd HH:mm");
            }

            String description = details[0];
            String at = details[1];
            return new String[] {description, at};
        }
    }

    public String parseFindTask(String commandText) throws DukeException {
        // split command text by its first space into 2 parts
        String[] commandParts = commandText.split(" ", 2);

        // parse keyword of task
        if(commandParts.length == 1 || commandParts[1].isBlank()) {
            throw new DukeException("OOPS!!! There is no matching task.");
        } else{
            String keyword = commandParts[1];
            return commandParts[1];
        }
    }

    public boolean isValidTime(String time) throws DukeException {
        try {
            // convert time from String to LocalDateTime
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
