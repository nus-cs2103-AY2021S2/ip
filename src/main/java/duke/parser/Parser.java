package duke.parser;

import static duke.common.CommandUtils.ALL;
import static duke.common.Messages.MESSAGE_COMMAND_NOT_FOUND;
import static duke.common.Messages.MESSAGE_CORRECT_DEADLINE_FORMAT;
import static duke.common.Messages.MESSAGE_CORRECT_EVENT_FORMAT;
import static duke.common.Messages.MESSAGE_EMPTY_DATETIME_DESCRIPTION;
import static duke.common.Messages.MESSAGE_EMPTY_TASK_DESCRIPTION;
import static duke.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_DATETIME_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_TASK_FORMAT;
import static duke.common.Utils.checkIsInvalidDate;
import static duke.common.Utils.checkIsInvalidDateTime;
import static duke.common.Utils.checkIsNumeric;

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
            return parseDoneOrDeleteCmd(inputs, dukeCommand);
        case EVENT:
        case DEADLINE:
            return parseDeadlineOrEventCmd(inputs, dukeCommand);
        case FIND:
        case TODO:
            return parseFindOrTodoCmd(inputs, dukeCommand);
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
    private static Command parseDoneOrDeleteCmd(String[] inputs, DukeCommand dukeCommand) throws DukeException {
        checkInputsLength(inputs, dukeCommand.toString());
        String params = inputs[1];
        if (checkValidParams().test(params)) {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        switch (dukeCommand) {
        case DELETE:
            return new DeleteCommand(params);
        case DONE:
            return new DoneCommand(params);
        default:
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private static Predicate<String> checkValidParams() {
        Predicate<String> isNumeric = str -> !checkIsNumeric(str);
        Predicate<String> isAll = str -> !str.equals(ALL);
        return isNumeric.and(isAll);
    }

    /**
     * Returns either {@code EventCommand} or {@code DeadlineCommand} depending on the user's input.
     *
     * @param inputs the user's input that has been split into array by spaces
     * @return {@code EventCommand} or {@code DeadlineCommand}
     * @throws DukeException when the user types an invalid command format
     */
    private static Command parseDeadlineOrEventCmd(String[] inputs, DukeCommand dukeCommand) throws DukeException {
        String delimiter = (dukeCommand == DukeCommand.DEADLINE) ? DEADLINE_DELIMITER : EVENT_DELIMITER;
        int delimiterIndex = getDelimiterIndex(inputs, dukeCommand, delimiter);
        String taskDesc = joinStringFromArray(inputs, 1, delimiterIndex);
        String taskDate = parseDateTimeString(inputs, delimiterIndex);

        Task newTask;
        switch (dukeCommand) {
        case TODO:
            newTask = new Event(taskDesc, taskDate);
            break;
        case FIND:
            newTask = new Deadline(taskDesc, taskDate);
            break;
        default:
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
    private static Command parseFindOrTodoCmd(String[] inputs, DukeCommand dukeCommand) throws DukeException {
        checkInputsLength(inputs, dukeCommand.toString());
        String cmdDesc = joinStringFromArray(inputs, 1, inputs.length);
        switch (dukeCommand) {
        case TODO:
            return new AddCommand(new ToDo(cmdDesc));
        case FIND:
            return new FindCommand(cmdDesc);
        default:
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
            String queryDate = inputs[1];
            if (checkIsInvalidDate(queryDate)) {
                throw new DukeException(MESSAGE_INVALID_DATETIME_FORMAT);
            }
            return new ListCommand(queryDate);
        } else if (inputs.length == 1) {
            return new ListCommand();
        } else {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private static String parseDateTimeString(String[] inputs, int index) throws DukeException {
        String dateString = joinStringFromArray(inputs, index + 1, inputs.length);
        if (checkIsInvalidDateTime(dateString)) {
            throw new DukeException(MESSAGE_INVALID_DATETIME_FORMAT);
        }
        return dateString;
    }

    private static String joinStringFromArray(String[] inputs, int startIndex, int endIndex) {
        return String.join(" ", Arrays.copyOfRange(inputs, startIndex, endIndex));
    }

    private static int getDelimiterIndex(String[] inputs, DukeCommand dukeCommand, String delimiter)
            throws DukeException {
        String commandType = dukeCommand.toString();
        checkInputsLength(inputs, commandType);
        int index = Arrays.asList(inputs).indexOf(delimiter);
        //ERROR: Delimiter not found
        if (index < 0) {
            throw new DukeException(String.format(MESSAGE_INVALID_TASK_FORMAT, commandType,
                    getFormatMsg(dukeCommand)));
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

    private static String getFormatMsg(DukeCommand commandType) throws DukeException {
        switch (commandType) {
        case DEADLINE:
            return MESSAGE_CORRECT_DEADLINE_FORMAT;
        case EVENT:
            return MESSAGE_CORRECT_EVENT_FORMAT;
        default:
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private static void checkInputsLength(String[] inputs, String commandType) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(String.format(MESSAGE_EMPTY_TASK_DESCRIPTION, commandType));
        }
    }
}
