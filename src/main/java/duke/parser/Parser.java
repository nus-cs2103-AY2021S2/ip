package duke.parser;

import static duke.common.CommandUtils.ALL;
import static duke.common.Messages.MESSAGE_COMMAND_NOT_FOUND;
import static duke.common.Messages.MESSAGE_EMPTY_DATETIME_DESCRIPTION;
import static duke.common.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static duke.common.Messages.MESSAGE_EMPTY_TASK_DESCRIPTION;
import static duke.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_DATETIME_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_TASK_FORMAT;

import java.util.Arrays;
import java.util.function.Predicate;

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
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";
    private static final String DATETIME_DELIMITER = "T";

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
            throw new DukeException(MESSAGE_COMMAND_NOT_FOUND);
        }

        DukeCommand dukeCommand = DukeCommand.valueOf(command);
        switch (dukeCommand) {
        case BYE:
            return new ExitCommand();
        case DELETE:
        case DONE:
            return parseDoneAndDeleteCmd(inputs, dukeCommand);
        case EVENT:
        case DEADLINE:
            return parseEventAndDeadlineCmd(inputs, dukeCommand);
        case FIND:
        case TODO:
            return parseFindAndTodoCmd(inputs, dukeCommand);
        case LIST:
            return parseListCmd(inputs);
        default:
            throw new DukeException(MESSAGE_COMMAND_NOT_FOUND);
        }
    }

    /**
     * Returns either {@code DoneCommand} or {@code DeleteCommand} depending on the user's input.
     *
     * @param inputs the user's input that has been split into array by spaces
     * @return {@code DoneCommand} or {@code DeleteCommand}
     * @throws DukeException when the user types an invalid command format
     */
    private static Command parseDoneAndDeleteCmd(String[] inputs, DukeCommand dukeCommand) throws DukeException {
        checkInputsLength(inputs);
        String input = inputs[1];
        Predicate<String> isNumeric = str -> !Utils.checkIsNumeric(str);
        Predicate<String> isAll = str -> !str.equals(ALL);
        if (isNumeric.and(isAll).test(input)) {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        if (dukeCommand == DukeCommand.DELETE) {
            return new DeleteCommand(input);
        } else if (dukeCommand == DukeCommand.DONE) {
            return new DoneCommand(input);
        } else {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Returns either {@code EventCommand} or {@code DeadlineCommand} depending on the user's input.
     *
     * @param inputs the user's input that has been split into array by spaces
     * @return {@code EventCommand} or {@code DeadlineCommand}
     * @throws DukeException when the user types an invalid command format
     */
    private static Command parseEventAndDeadlineCmd(String[] inputs, DukeCommand dukeCommand) throws DukeException {
        String delimiter = dukeCommand == DukeCommand.EVENT ? EVENT_DELIMITER : DEADLINE_DELIMITER;
        int index = getDelimiterIndex(inputs, dukeCommand.toLower(), delimiter);
        String desc = String.join(" ", Arrays.copyOfRange(inputs, 1, index));
        String date = checkIsValidDate(inputs, index);

        Task newTask;
        if (dukeCommand == DukeCommand.EVENT) {
            newTask = new Event(desc, date);
        } else if (dukeCommand == DukeCommand.DEADLINE) {
            newTask = new Deadline(desc, date);
        } else {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new AddCommand(newTask);
    }

    /**
     * Returns either {@code FindCommand} or {@code ToDoCommand} depending on the user's input.
     *
     * @param inputs the user's input that has been split into array by spaces
     * @return {@code FindCommand} or {@code ToDoCommand}
     * @throws DukeException when the user types an invalid command format
     */
    private static Command parseFindAndTodoCmd(String[] inputs, DukeCommand dukeCommand) throws DukeException {
        checkInputsLength(inputs);
        String desc = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
        if (dukeCommand == DukeCommand.TODO) {
            return new AddCommand(new ToDo(desc));
        } else if (dukeCommand == DukeCommand.FIND) {
            return new FindCommand(desc);
        } else {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Returns a {@code ListCommand} depending on user's input
     *
     * @param inputs the user's input that has been split into array by spaces
     * @return {@code ListCommand}
     * @throws DukeException when the user types an invalid command format
     */
    private static Command parseListCmd(String[] inputs) throws DukeException {
        if (inputs.length == 2) {
            String date = inputs[1];
            if (!Utils.checkIsValidDate(date)) {
                throw new DukeException(MESSAGE_INVALID_DATETIME_FORMAT);
            }
            return new ListCommand(date);
        } else if (inputs.length == 1) {
            return new ListCommand();
        } else {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private static String checkIsValidDate(String[] inputs, int index) throws DukeException {
        String date = String.join(DATETIME_DELIMITER, Arrays.copyOfRange(inputs, index + 1, inputs.length));
        if (!Utils.checkIsValidDateTime(date)) {
            throw new DukeException(MESSAGE_INVALID_DATETIME_FORMAT);
        }
        return date;
    }

    private static int getDelimiterIndex(String[] inputs, String commandType, String delimiter) throws DukeException {
        checkInputsLength(inputs);
        int index = Arrays.asList(inputs).indexOf(delimiter);
        //ERROR: Delimiter not found
        if (index < 0) {
            throw new DukeException(String.format(MESSAGE_INVALID_TASK_FORMAT, commandType));
        }
        //ERROR: Delimiter found but no description
        if (inputs[index - 1].equals(commandType)) {
            throw new DukeException(String.format(MESSAGE_EMPTY_TASK_DESCRIPTION, commandType));
        }
        //ERROR: No date after delimiter
        if (inputs.length <= index + 1) {
            throw new DukeException(String.format(MESSAGE_EMPTY_DATETIME_DESCRIPTION, commandType));
        }
        return index;
    }

    private static void checkInputsLength(String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(MESSAGE_EMPTY_DESCRIPTION);
        }
    }
}
