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
     * @param userInput The input from the user.
     * @return A String[] data type that the other methods can process.
     * @throws DukeException If the format of the user input is incorrect.
     */
    public String[] parseCommand (String userInput) throws DukeException {
        String typeOfCommand = userInput.split(" ")[0];
        if (typeOfCommand.equals("list")) {
            return this.processList(userInput);
        } else if (typeOfCommand.equals("done")) {
            return this.processDone(userInput);
        } else if (typeOfCommand.equals("delete")) {
            return this.processDelete(userInput);
        } else if (typeOfCommand.equals("todo")) {
            return this.processToDo(userInput);
        } else if (typeOfCommand.equals("deadline")) {
            return this.processDeadLine(userInput);
        } else if (typeOfCommand.equals("event")) {
            return this.processEvent(userInput);
        } else if (typeOfCommand.equals("find")) {
            return this.processFind(userInput);
        } else if (typeOfCommand.equals("sort")) {
            return this.processSort(userInput);
        } else {
            throw new DukeException("Invalid command");
        }
    }

    private String[] processList(String input) throws DukeException {
        String errorMessage = "Invalid input. To use the list command, use the format: list";
        String[] parsedInput = new String[1];
        String[] removeSpaces = input.split(" ");
        if (removeSpaces.length > 1) {
            throw new DukeException(errorMessage);
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
            if (index <= 0) {
                throw new DukeException("Invalid index");
            }
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
            if (index <= 0) {
                throw new DukeException("Invalid index");
            }
            parsedInput[0] = "delete";
            parsedInput[1] = String.valueOf(index);
            return parsedInput;
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid input, index must be an integer");
        }
    }

    private String[] processToDo(String input) throws DukeException {
        String[] parsedInput = new String[2];
        String[] removeSpaces = input.split(" ");
        if (removeSpaces.length == 1) {
            throw new DukeException("Description of todo cannot be empty");
        }
        parsedInput[0] = "todo";
        for (int i = 1; i < removeSpaces.length; i++) {
            if (i == 1) {
                parsedInput[1] = removeSpaces[i];
            } else {
                parsedInput[1] += " " + removeSpaces[i];
            }
        }
        return parsedInput;
    }

    private String[] processDeadLine(String input) throws DukeException {
        String[] parsedInput = new String[3];
        if (input.length() <= 9) {
            throw new DukeException("Description of deadline cannot be empty");
        }
        String[] removeKeyword = input.substring(9).split("/by");
        if (removeKeyword.length != 2) {
            throw new DukeException("Invalid description of deadline");
        }
        String[] deadlineName = removeKeyword[0].split(" ");
        if (deadlineName[0].length() == 0) {
            throw new DukeException("Name of deadline task cannot be empty");
        }
        try {
            LocalDate date = LocalDate.parse(removeKeyword[1].substring(1));
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid date. Input date in the format: yyyy-mm-dd");
        }
        parsedInput[0] = "deadline";
        parsedInput[2] = removeKeyword[1].substring(1);
        for (int i = 0; i < deadlineName.length; i++) {
            if (i == 0) {
                parsedInput[1] = deadlineName[0];
            } else {
                parsedInput[1] += " " + deadlineName[i];
            }
        }
        return parsedInput;
    }

    private String[] processEvent(String input) throws DukeException {
        String[] parsedInput = new String[3];
        if (input.length() <= 6) {
            throw new DukeException("Description of event cannot be empty");
        }
        String[] removeKeyword = input.substring(6).split("/at");
        if (removeKeyword.length != 2) {
            throw new DukeException("Invalid description of event");
        }
        String[] eventName = removeKeyword[0].split(" ");
        if (eventName[0].length() == 0) {
            throw new DukeException("Name of event task cannot be empty");
        }
        try {
            LocalDate date = LocalDate.parse(removeKeyword[1].substring(1));
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid date. Input date in the format: yyyy-mm-dd");
        }
        parsedInput[0] = "event";
        parsedInput[2] = removeKeyword[1].substring(1);
        for (int i = 0; i < eventName.length; i++) {
            if (i == 0) {
                parsedInput[1] = eventName[0];
            } else {
                parsedInput[1] += " " + eventName[i];
            }
        }
        return parsedInput;
    }

    private String[] processFind(String input) throws DukeException {
        String[] parsedInput = new String[2];
        if (input.length() <= 5) {
            throw new DukeException("No keyword found");
        }
        String[] removeSpaces = input.substring(5).split(" ");
        if (removeSpaces.length == 0) {
            throw new DukeException("No keyword found");
        }
        if (removeSpaces[0].length() == 0) {
            throw new DukeException("No keyword found");
        }
        parsedInput[0] = "find";
        parsedInput[1] = input.substring(5);
        return parsedInput;
    }

    private String[] processSort(String input) throws DukeException {
        String[] parsedInput = new String[2];
        String errorMessage = "Invalid input for sort. The commands for sort are:\n";
        errorMessage += "sort name";
        errorMessage += "\nsort donefirst";
        errorMessage += "\nsort notdonefirst";
        if (input.length() <= 5) {
            throw new DukeException(errorMessage);
        }
        String removeSpaces = input.substring(5);
        boolean isWrongInput = !(removeSpaces.equals("name") || removeSpaces.equals("donefirst")
                || removeSpaces.equals("notdonefirst"));
        if (isWrongInput) {
            throw new DukeException(errorMessage);
        }
        parsedInput[0] = "sort";
        parsedInput[1] = removeSpaces;
        return parsedInput;
    }
}
