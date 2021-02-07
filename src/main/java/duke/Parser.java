package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The class that deals with making sense of the user command
 */
public class Parser {

    /**
     * The main method that reads the input from the user.
     *
     * @param input the input from the user
     * @return a String[] data type that the other methods can process
     * @throws DukeException together with the error message
     */
    public String[] parseCommand (String input) throws DukeException {
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
        String[] parsedInput = new String[1];
        String[] removeSpaces = input.split(" ");
        if (removeSpaces.length > 1) {
            throw new DukeException("Invalid input");
        }
        parsedInput[0] = "list";
        return parsedInput;
    }

    private String[] processDone(String input) throws DukeException {
        String[] parsedInput = new String[2];
        String[] removeSpaces = input.split(" ");
        if (removeSpaces.length == 1) {
            throw new DukeException("Invalid input, index is missing");
        } else if (removeSpaces.length > 2) {
            throw new DukeException("Invalid input");
        }
        try {
            int index = Integer.parseInt(removeSpaces[1]);
            parsedInput[0] = "done";
            parsedInput[1] = String.valueOf(index);
            return parsedInput;
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid input, index must be an integer");
        }
    }

    private String[] processDelete(String input) throws DukeException {
        String[] parsedInput = new String[2];
        String[] removeSpaces = input.split(" ");
        if (removeSpaces.length == 1) {
            throw new DukeException("Invalid input, index is missing");
        } else if (removeSpaces.length > 2) {
            throw new DukeException("Invalid input");
        }
        try {
            int index = Integer.parseInt(removeSpaces[1]);
            parsedInput[0] = "delete";
            parsedInput[1] = String.valueOf(index);
            return parsedInput;
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid input, index must be an integer");
        }
    }

    private String[] processToDo(String input) throws DukeException {
        String[] parsedInput = new String[2];
        if (input.length() <= 4) {
            throw new DukeException("Invalid input, description of todo cannot be empty");
        } else if (input.charAt(4) != ' ') {
            throw new DukeException("Invalid description of todo");
        }
        String removeSpaces = input.substring(5);
        parsedInput[0] = "todo";
        parsedInput[1] = removeSpaces;
        return parsedInput;
    }

    private String[] processDeadLine(String input) throws DukeException {
        String[] parsedInput = new String[3];
        if (input.length() <= 9) {
            throw new DukeException("Invalid input, description of deadline cannot be empty");
        }
        String[] removeSpaces = input.substring(9).split("/by");
        if (removeSpaces.length != 2) {
            throw new DukeException("Invalid description of deadline");
        }
        try {
            LocalDate date = LocalDate.parse(removeSpaces[1].substring(1));
            parsedInput[0] = "deadline";
            parsedInput[1] = removeSpaces[0];
            parsedInput[2] = removeSpaces[1].substring(1);
            return parsedInput;
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid date");
        }
    }

    private String[] processEvent(String input) throws DukeException {
        String[] parsedInput = new String[3];
        if (input.length() <= 6) {
            throw new DukeException("Invalid input, description of event cannot be empty");
        }
        String[] removeSpaces = input.substring(6).split("/at");
        if (removeSpaces.length != 2) {
            throw new DukeException("Invalid description of event");
        }
        try {
            LocalDate date = LocalDate.parse(removeSpaces[1].substring(1));
            parsedInput[0] = "event";
            parsedInput[1] = removeSpaces[0];
            parsedInput[2] = removeSpaces[1].substring(1);
            return parsedInput;
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid date");
        }
    }

    private String[] processFind(String input) throws DukeException {
        String[] parsedInput = new String[2];
        if (input.length() <= 5) {
            throw new DukeException("Invalid input, no keyword found");
        } else {
            String removeSpaces = input.substring(5);
            parsedInput[0] = "find";
            parsedInput[1] = removeSpaces;
            return parsedInput;
        }
    }
}
