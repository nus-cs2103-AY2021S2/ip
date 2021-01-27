package main.java;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The class that deals with making sense of the user command
 */
public class Parser{

    /**
     * The main method that reads the input from the user.
     *
     * @param input the input from the user
     * @return a String[] data type that the other methods can process
     * @throws DukeException together with the error message
     */
    public String[] processCommand (String input) throws DukeException {
        String command = input.split(" ")[0];
        if (command.equals("list")) {
            return this.processList(input);
        } else if (command.equals("done")) {
            return this.processDone(input);
        } else if (command.equals("delete")) {
            return this.processDelete(input);
        } else if (command.equals("todo")) {
            return this.processToDo(input);
        } else if (command.equals("deadline")) {
            return this.processDeadLine(input);
        } else if (command.equals("event")) {
            return this.processEvent(input);
        } else if (command.equals("find")) {
            return this.processFind(input);
        } else {
            throw new DukeException("Invalid command");
        }
    }

    private String[] processList(String input) throws DukeException {
        String[] result = new String[1];
        String[] processedInput = input.split(" ");
        if (processedInput.length > 1) {
            throw new DukeException("Invalid input");
        } else {
            result[0] = "list";
        }
        return result;
    }

    private String[] processDone(String input) throws DukeException {
        String[] result = new String[2];
        String[] processedInput = input.split(" ");
        if (processedInput.length == 1) {
            throw new DukeException("Invalid input, index is missing");
        } else if (processedInput.length > 2) {
            throw new DukeException("Invalid input");
        }
        try {
            int index = Integer.parseInt(processedInput[1]);
            result[0] = "done";
            result[1] = String.valueOf(index);
            return result;
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid input, index must be an integer");
        }
    }

    private String[] processDelete(String input) throws DukeException {
        String[] result = new String[2];
        String[] processedInput = input.split(" ");
        if (processedInput.length == 1) {
            throw new DukeException("Invalid input, index is missing");
        } else if (processedInput.length > 2) {
            throw new DukeException("Invalid input");
        }
        try {
            int index = Integer.parseInt(processedInput[1]);
            result[0] = "delete";
            result[1] = String.valueOf(index);
            return result;
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid input, index must be an integer");
        }
    }

    private String[] processToDo(String input) throws DukeException {
        String[] result = new String[2];
        String processedInput = input.substring(5);
        if (input.length() <= 4) {
            throw new DukeException("Invalid input, description of todo cannot be empty");
        } else if (input.charAt(4) != ' ') {
            throw new DukeException("Invalid description of todo");
        }
        result[0] = "todo";
        result[1] = processedInput;
        return result;
    }

    private String[] processDeadLine(String input) throws DukeException {
        String[] result = new String[3];
        String[] processedInput = input.substring(9).split("/by");
        if (processedInput.length == 0) {
            throw new DukeException("Invalid input, description of deadline cannot be empty");
        } else if (processedInput.length != 2) {
            throw new DukeException("Invalid description of deadline");
        }
        try {
            LocalDate date = LocalDate.parse(processedInput[1].substring(1));
            result[0] = "deadline";
            result[1] = processedInput[0];
            result[2] = processedInput[1].substring(1);
            return result;
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid date");
        }
    }

    private String[] processEvent(String input) throws DukeException {
        String[] result = new String[3];
        String[] processedInput = input.substring(6).split("/at");
        if (processedInput.length == 0) {
            throw new DukeException("Invalid input, description of event cannot be empty");
        } else if (processedInput.length != 2) {
            throw new DukeException("Invalid description of event");
        }
        try {
            LocalDate date = LocalDate.parse(processedInput[1].substring(1));
            result[0] = "event";
            result[1] = processedInput[0];
            result[2] = processedInput[1].substring(1);
            return result;
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid date");
        }
    }

    private String[] processFind(String input) throws DukeException {
        String[] result = new String[2];
        if (input.length() <= 5) {
            throw new DukeException("Invalid input, no keyword found");
        } else {
            String processedInput = input.substring(5);
            result[0] = "find";
            result[1] = processedInput;
            return result;
        }
    }
}
