package duke;

import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.DukeCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Parses the user's input.
 */
public class Parser {

    /**
     * Parses the user input into its respective command.
     *
     * @param fullCommand the user's input string
     * @return a {@code Command} based on the user's input
     * @throws DukeException when the user types an invalid or incomplete command
     */
    public static final Command parse(String fullCommand) throws DukeException {
        String[] inputs = fullCommand.split(" ");
        String command = inputs[0].toUpperCase();
        if (!DukeCommand.isContains(command)) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } else {
            DukeCommand dukeCommand = DukeCommand.valueOf(command);
            switch (dukeCommand) {
            case BYE:
                return new ExitCommand();
            case DELETE:
                return new DeleteCommand(getIntegerFromInput(inputs));
            case DONE:
                return new DoneCommand(getIntegerFromInput(inputs));
            case EVENT:
            case DEADLINE:
            case TODO:
                return new AddCommand(getTaskFromInput(inputs, dukeCommand));
            case LIST:
                return inputs.length == 2 ? new ListCommand(inputs[1]) : new ListCommand();
            case FIND:
                if (inputs.length < 2) {
                    throw new DukeException("You are missing the query!");
                }
                return new FindCommand(String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length)));
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /**
     * Return the numeric value that the user has typed in if the command input is valid.
     *
     * @param arr the user's input that has been split into array by spaces
     * @return the parsed numeric value
     * @throws DukeException when the user did not type a number after the command
     */
    public static String getIntegerFromInput(String[] arr) throws DukeException {
        if (arr.length < 2) {
            throw new DukeException("You are missing the index!");
        }

        String input = arr[1];
        if (!DukeHelper.numericChecker(input) && !input.equals("all")) {
            throw new DukeException("Your input is not recognised.");
        }

        return input;
    }

    /**
     * Parses the arguments into {@code Deadline}, {@code Event} or {@code ToDo}, depending on the user's command.
     *
     * @param arr the user's input that has been split into array by spaces
     * @param dukeCommand the user's desired command type
     * @return the prepared {@code Deadline}, {@code Event} or {@code ToDo}
     * @throws DukeException when the user types an invalid or incomplete command
     */
    public static Task getTaskFromInput(String[] arr, DukeCommand dukeCommand) throws DukeException {
        String commandType = dukeCommand.name().toLowerCase();
        if (arr.length < 2) {
            throw new DukeException(String.format("The description of %s cannot be empty.", commandType));
        }

        String desc;
        if (dukeCommand == DukeCommand.DEADLINE || dukeCommand == DukeCommand.EVENT) {
            String delimiter = dukeCommand == DukeCommand.DEADLINE ? "/by" : "/at";
            int index = Arrays.asList(arr).indexOf(delimiter);

            //Delimiter not found
            if (index < 0) {
                throw new DukeException(String.format("You have entered an invalid %s format.", commandType));
            }

            //Delimiter found but no description
            if (arr[index - 1].equals(commandType)) {
                throw new DukeException(String.format("The description of %s cannot be empty.", commandType));
            }

            //No date after delimiter
            if (arr.length <= index + 1) {
                throw new DukeException(String.format("The date of %s cannot be empty.", commandType));
            }

            //Joins up the description
            desc = String.join(" ", Arrays.copyOfRange(arr, 1, index));
            String date = arr[index + 1];

            if (!DukeHelper.validDateChecker(date)) {
                throw new DukeException("You have entered an invalid date format.");
            }

            if (dukeCommand == DukeCommand.DEADLINE) {
                return new Deadline(desc, date);
            } else {
                return new Event(desc, date);
            }
        } else {
            desc = String.join(" ", Arrays.copyOfRange(arr, 1, arr.length));
            return new ToDo(desc);
        }
    }
}
