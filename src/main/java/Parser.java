import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Parser {
    private ArrayList<String> validOperators;
    private static final String timeFormat = "yyyy-M-dd H:mm";

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

    public String parseOperator(String userInput){
        String[] parts = userInput.split(" ", 2);
        return parts[0];
    }

    public void parseCommand(String userInput) throws DukeException{
        String[] command = splitCommand(userInput);
        String operator = command[0].toLowerCase();

        if (userInput.equals("list")){
            parseDisplay();
        }

        if (!isValidOperator(operator)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else {
            if (operator.equals("done")) {
                parseDone(command);
            }
            if (operator.equals("delete")){
                parseDelete(command);
            }
            if (operator.equals("todo")) {
                parseAddToDo(command);
            }
            if (operator.equals("deadline")) {
                parseAddDeadline(command);
            }
            if (operator.equals("event")) {
                parseAddEvent(command);
            }
        }
    }

    private boolean isValidOperator(String operator){
        return validOperators.contains(operator.toLowerCase());
    }

    private String[] splitCommand(String userInput){
        return userInput.split(" ", 2);
    }

//    private void parseDisplay(){
//
//    }

    private int parseDone(String[] command) throws DukeException{
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

    private int parseDelete(String[] command) throws DukeException {
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
    private String parseAddToDo(String[] command) throws DukeException {
        if(command.length == 1 || command[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else{
            return command[1];
        }
    }

    private String[] parseAddDeadline(String[] command) throws DukeException {
        if(command.length == 1 || command[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else{
            String[] details = command[1].split(" /by ", 2);

            if (details.length == 1 || !isValidTime(details[1])){
                throw new DukeException("OOPS!!! The time of a deadline is invalid.");
            }

            String description = details[0];
            String by = details[1];
            return new String[] {description, by};
        }
    }

    private String[] parseAddEvent(String[] command) throws DukeException {
        if(command.length == 1 || command[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else{
            String[] details = command[1].split(" /at ", 2);

            if (details.length == 1 || !isValidTime(details[1])){
                throw new DukeException("OOPS!!! The time of an event is invalid.");
            }

            String description = details[0];
            String at = details[1];
            return new String[] {description, at};
        }
    }

    private boolean isValidTime(String time){
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-dd H:mm");
        boolean isValid;
        try{
            LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
            isValid = true;
        } catch (Exception e){
            isValid = false;
            throw new DukeException("OOPS!! Please follow the correct data/time format: yyyy-mm-dd hh:mm");
        }
        return isValid;
    }


    private String extractOperator(String userInput){
        String[] parts = userInput.split(" ", 2);
        return parts[0];
    }

    private String extractDescription(String userInput) {
        String[] parts = userInput.split(" ", 2);
        return parts[1];
    }
}
