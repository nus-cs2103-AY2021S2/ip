package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.DueCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

/**
 * Class responsible for parsing of commands.
 */
public class Parser {
    private static final String TODO = "todo";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String LIST = "list";
    private static final String DUE = "due";
    private static final String FIND = "find";

    /**
     * Takes in a list of tasks stored in the storage format and parses it into the loading format.
     *
     * @param oldData list of tasks in the storage format.
     * @return ArrayList list of tasks in user input format to load into tasklist.
     */
    public static ArrayList<String> parseToStart(ArrayList<String> oldData) {
        ArrayList<String> parsedData = new ArrayList<>();
        for (String task : oldData) {
            String[] array = task.split(" \\| ");
            if (task.contains("T")) {
                array[0] = "todo";
            } else if (task.contains("D")) {
                array[0] = "deadline";
            } else {
                array[0] = "event";
            }
            String parsedString = array[0];
            for (int i = 1; i < array.length; i++) {
                if (i == 3) {
                    parsedString = array[0].equals("deadline") ? parsedString + " /by " + array[i] : parsedString
                            + " /at " + array[i];
                } else {
                    parsedString = parsedString + " " + array[i];
                }
            }
            parsedData.add(parsedString);
        }
        return parsedData;
    }

    private static Command parseListCommand(String[] inputArray) throws DukeException {
        if (inputArray.length == 1) {
            return new ListCommand(inputArray);
        } else {
            throw new DukeException("Invalid list command! Please try again.");
        }
    }

    private static Command parseDoneCommand(String[] inputArray) throws DukeException {
        if (inputArray.length == 1) {
            throw new DukeException("Missing task number! Please try again.");
        } else {
            try {
                int taskNumber = Integer.parseInt(inputArray[1]);
                return new DoneCommand(inputArray);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number! Please try again.");
            }
        }
    }

    private static Command parseDeleteCommand(String[] inputArray) throws DukeException {
        if (inputArray.length == 1) {
            throw new DukeException("Missing task number! Please try again.");
        } else {
            try {
                String[] taskNumbers = inputArray[1].split(" ");
                for (int i = 0; i < taskNumbers.length; i++) {
                    Integer.parseInt(taskNumbers[i]);
                }
                return new DeleteCommand(inputArray);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number! Please try again.");
            }
        }
    }

    private static Command parseFindCommand(String[] inputArray) throws DukeException {
        if (inputArray.length == 1) {
            throw new DukeException("Missing find keyword! Please try again");
        } else {
            return new FindCommand(inputArray);
        }
    }

    private static Command parseDueCommand(String[] inputArray) throws DukeException {
        if (inputArray.length == 1) {
            throw new DukeException("Missing date! Please try again.");
        } else {
            try {
                LocalDate date = LocalDate.parse(inputArray[1]);
                return new DueCommand(inputArray);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date format! Please try again.");
            }
        }
    }

    private static Command parseAddCommand(String[] inputArray) throws DukeException {
        String command = inputArray[0];
        if (inputArray.length == 1) {
            throw new DukeException("Missing task description! Please try again.");
        } else {
            String description = inputArray[1];
            if (command.equals(TODO)) {
                return new AddCommand(inputArray);
            } else if (command.equals(DEADLINE) && description.split("/by").length == 2) {
                return new AddCommand(inputArray);
            } else if (command.equals(EVENT) && description.split("/at").length == 2) {
                return new AddCommand(inputArray);
            } else {
                throw new DukeException("Invalid command! Please try again.");
            }
        }
    }

    /**
     * Checks and parses the input into the executable commands if the input is valid.
     *
     * @param input input that is keyed in by the user.
     * @return Command command to be executed.
     * @throws DukeException if the input is not in the correct format.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0];
        if (command.equals("")) {
            throw new DukeException("No command typed. Please key in a valid command");
        } else if (command.equals(LIST)) {
            return parseListCommand(inputArray);
        } else if (command.equals(DONE)) {
            return parseDoneCommand(inputArray);
        } else if (command.equals(DELETE)) {
            return parseDeleteCommand(inputArray);
        } else if (command.equals(FIND)) {
            return parseFindCommand(inputArray);
        } else if (command.equals(DUE)) {
            return parseDueCommand(inputArray);
        } else if (command.equals(TODO) || command.equals(DEADLINE) || command.equals(EVENT)) {
            return parseAddCommand(inputArray);
        } else if (command.equals("bye")) {
            return new ByeCommand(inputArray);
        } else {
            throw new DukeException("Invalid command! Please try again.");
        }
    }
}
