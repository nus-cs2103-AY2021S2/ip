package duke.parser;

import java.util.Arrays;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.DukeCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.common.Utils;
import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Parses the user's input.
 */
public class Parser {
    private static final String NOT_FOUND_MESSAGE = "I'm sorry, but I don't know what that means :-(";
    /**
     * Parses the user input into its respective command.
     *
     * @param fullCommand the user's input string
     * @return a {@code Command} based on the user's input
     * @throws DukeException when the user types an invalid or incomplete command
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputs = fullCommand.split(" ");
        String command = inputs[0].toUpperCase();
        if (!DukeCommand.isContains(command)) {
            throw new DukeException(NOT_FOUND_MESSAGE);
        } else {
            DukeCommand dukeCommand = DukeCommand.valueOf(command);
            switch (dukeCommand) {
            case BYE:
                return new ExitCommand();
            case DELETE:
                return new DeleteCommand(getIntegerFromInputs(inputs));
            case DONE:
                return new DoneCommand(getIntegerFromInputs(inputs));
            case EVENT:
            case DEADLINE:
            case TODO:
                return new AddCommand(getTaskFromInputs(inputs, dukeCommand));
            case LIST:
                return inputs.length == 2 ? new ListCommand(inputs[1]) : new ListCommand();
            case FIND:
                return new FindCommand(getFindQueryFromInputs(inputs));
            default:
                throw new DukeException(NOT_FOUND_MESSAGE);
            }
        }
    }

    /**
     * Return the numeric value that the user has typed in if the command input is valid.
     *
     * @param inputs the user's input that has been split into array by spaces
     * @return the parsed numeric value
     * @throws DukeException when the user did not type a number after the command
     */
    private static String getIntegerFromInputs(String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException("You are missing the index!");
        }

        String input = inputs[1];
        if (!Utils.checkNumeric(input) && !input.equals("all")) {
            throw new DukeException("Your input is not recognised.");
        }

        return input;
    }

    /**
     * Parses the arguments into {@code Deadline}, {@code Event} or {@code ToDo}, depending on the user's command.
     *
     * @param inputs the user's input that has been split into array by spaces
     * @param dukeCommand the user's desired command type
     * @return the prepared {@code Deadline}, {@code Event} or {@code ToDo}
     * @throws DukeException when the user types an invalid or incomplete command
     */
    private static Task getTaskFromInputs(String[] inputs, DukeCommand dukeCommand) throws DukeException {
        String commandType = dukeCommand.name().toLowerCase();
        if (inputs.length < 2) {
            throw new DukeException(String.format("The description of %s cannot be empty.", commandType));
        }

        String desc;
        if (dukeCommand == DukeCommand.DEADLINE || dukeCommand == DukeCommand.EVENT) {
            String delimiter = dukeCommand == DukeCommand.DEADLINE ? "/by" : "/at";
            int index = Arrays.asList(inputs).indexOf(delimiter);

            //ERROR: Delimiter not found
            if (index < 0) {
                throw new DukeException(String.format("You have entered an invalid %s format.", commandType));
            }

            //ERROR: Delimiter found but no description
            if (inputs[index - 1].equals(commandType)) {
                throw new DukeException(String.format("The description of %s cannot be empty.", commandType));
            }

            //ERROR: No date after delimiter
            if (inputs.length <= index + 1) {
                throw new DukeException(String.format("The date of %s cannot be empty.", commandType));
            }

            //Joins up the description
            desc = String.join(" ", Arrays.copyOfRange(inputs, 1, index));

            //Joins up the date and time
            String date = String.join("T", Arrays.copyOfRange(inputs, index + 1, inputs.length));

            //ERROR: Invalid date time format
            if (!Utils.checkValidDate(date)) {
                throw new DukeException("You have entered an invalid date time format.");
            }

            if (dukeCommand == DukeCommand.DEADLINE) {
                return new Deadline(desc, date);
            } else {
                return new Event(desc, date);
            }

        } else if (dukeCommand == DukeCommand.TODO) {
            desc = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
            return new ToDo(desc);

        } else {
            throw new DukeException(NOT_FOUND_MESSAGE);
        }
    }

    /**
     * Return the find query that the user has typed in if the command input is valid.
     *
     * @param inputs the user's input that has been split into String array by spaces
     * @return the joined find query
     * @throws DukeException when the user did not type a query after the command
     */
    private static String getFindQueryFromInputs(String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException("You are missing the query!");
        }

        return String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
    }
}
