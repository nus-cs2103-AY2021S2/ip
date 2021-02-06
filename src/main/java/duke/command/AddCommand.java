package duke.command;

import java.time.DateTimeException;

import duke.CommandType;
import duke.DukeException;
import duke.StringParser;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Command type add.
 */
public class AddCommand extends Command {

    private static final int LENGTH_TODO = "todo ".length();
    private static final int LENGTH_EVENT = "event ".length();
    private static final int LENGTH_DEADLINE = "deadline ".length();
    private static final int LENGTH_SEPARATOR = 5;

    private static final String ERROR_EMPTY_ARGUMENT = "Invalid argument: Argument field cannot be empty.";
    private static final String ERROR_EMPTY_CONTENT = "Invalid argument: Content field is blank.";
    private static final String ERROR_EMPTY_TIME = "Void argument: Time field is blank.";
    private static final String ERROR_UNDETECTED_AT = "Invalid argument: /at not detected or no argument before /at.";
    private static final String ERROR_UNDETECTED_BY = "Invalid argument: /by not detected or no argument before /by.";
    private static final String ERROR_INCORRECT_FORMAT = "Incorrect time format: Correct format is yyyy-MM-dd HHmm.";

    private static final String MARK_AT = " /at ";
    private static final String MARK_BY = " /by ";


    private final String command;
    private final CommandType cmdType;

    /**
     * Constructs an add command.
     *
     * @param command Input string.
     * @param cmdType Determine whether this command is TODO, EVENT or DEADLINE.
     */
    public AddCommand(String command, CommandType cmdType) {
        this.command = command;
        this.cmdType = cmdType;
    }

    /**
     * Checks the validity of this command and build a todo task.
     *
     * @return Todo task.
     * @throws DukeException When command argument is invalid.
     */
    private Task convertToTodo() throws DukeException {

        if (command.length() <= LENGTH_TODO) {
            throw new DukeException(ERROR_EMPTY_ARGUMENT);
        }

        String subStr = command.substring(LENGTH_TODO);
        if (StringParser.isBlank(subStr)) {
            throw new DukeException(ERROR_EMPTY_CONTENT);
        }

        return new Todo(command.substring(LENGTH_TODO));
    }

    /**
     * Checks the validity of this command and build a event task.
     *
     * @return Event task.
     * @throws DukeException When command argument is invalid.
     */
    private Task convertToEvent() throws DukeException {

        if (command.length() <= LENGTH_EVENT) {
            throw new DukeException(ERROR_EMPTY_ARGUMENT);
        }

        int indexOfAt = command.toLowerCase().indexOf(MARK_AT);
        if (indexOfAt < LENGTH_EVENT) {
            throw new DukeException(ERROR_UNDETECTED_AT);
        }

        String subStrContent = command.substring(LENGTH_EVENT, indexOfAt);
        String subStrTime = command.substring(indexOfAt + LENGTH_SEPARATOR);
        if (StringParser.isBlank(subStrContent)) {
            throw new DukeException(ERROR_EMPTY_CONTENT);
        } else if (StringParser.isBlank(subStrTime)) {
            throw new DukeException(ERROR_EMPTY_TIME);
        }

        try {
            return new Event(subStrContent, StringParser.parseTime(subStrTime));
        } catch (DateTimeException e) {
            throw new DukeException(ERROR_INCORRECT_FORMAT);
        }
    }

    /**
     * Checks the validity of this command and build a deadline task.
     *
     * @return Deadline task.
     * @throws DukeException When command argument is invalid.
     */
    private Task convertToDeadline() throws DukeException {

        if (command.length() <= LENGTH_DEADLINE) {
            throw new DukeException(ERROR_EMPTY_ARGUMENT);
        }
        int indexOfBy = command.toLowerCase().indexOf(MARK_BY);
        if (indexOfBy < LENGTH_DEADLINE) {
            throw new DukeException(ERROR_UNDETECTED_BY);
        }

        String subStrContent = command.substring(LENGTH_DEADLINE, indexOfBy);
        String subStrTime = command.substring(indexOfBy + LENGTH_SEPARATOR);
        if (StringParser.isBlank(subStrContent)) {
            throw new DukeException(ERROR_EMPTY_CONTENT);
        } else if (StringParser.isBlank(subStrTime)) {
            throw new DukeException(ERROR_EMPTY_TIME);
        }

        try {
            return new Deadline(subStrContent, StringParser.parseTime(subStrTime));
        } catch (DateTimeException e) {
            throw new DukeException(ERROR_INCORRECT_FORMAT);
        }
    }

    /**
     * Executes and prints command according to its command type.
     *
     * @param list Passes TaskList in case of reading and writing to the list.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public String executeAndPrint(TaskList list) throws DukeException {
        Task task;
        switch (this.cmdType) {
        case Todo:
            task = convertToTodo();
            break;
        case Event:
            task = convertToEvent();
            break;
        case Deadline:
            task = convertToDeadline();
            break;
        default:
            assert false : "This line will never be reached.";
            throw new DukeException("Unexpected value: " + this.cmdType);
        }
        list.addJob(task);
        return "Task added:\n" + task.toString()
                + "Now you have " + list.getSize()
                + (list.getSize() == 1 ? " task in the list.\n" : " tasks in the list.\n");
    }

    @Override
    public String toString() {
        switch (this.cmdType) {
        case Todo:
            return "Test usage: this is a TODO command.";
        case Deadline:
            return "Test usage: this is a DEADLINE command.";
        case Event:
            return "Test usage: this is an EVENT command.";
        default:
            assert false : "This line will never be reached.";
            return "Error: this will never happen.";
        }
    }
}
